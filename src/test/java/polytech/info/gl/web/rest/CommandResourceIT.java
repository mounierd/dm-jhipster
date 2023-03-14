package polytech.info.gl.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import polytech.info.gl.IntegrationTest;
import polytech.info.gl.domain.Command;
import polytech.info.gl.repository.CommandRepository;
import polytech.info.gl.service.dto.CommandDTO;
import polytech.info.gl.service.mapper.CommandMapper;

/**
 * Integration tests for the {@link CommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CommandResourceIT {

    private static final String DEFAULT_ADDRESS_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_CLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_DATE_CLIENT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private CommandMapper commandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommandMockMvc;

    private Command command;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Command createEntity(EntityManager em) {
        Command command = new Command().addressClient(DEFAULT_ADDRESS_CLIENT).dateClient(DEFAULT_DATE_CLIENT);
        return command;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Command createUpdatedEntity(EntityManager em) {
        Command command = new Command().addressClient(UPDATED_ADDRESS_CLIENT).dateClient(UPDATED_DATE_CLIENT);
        return command;
    }

    @BeforeEach
    public void initTest() {
        command = createEntity(em);
    }

    @Test
    @Transactional
    void createCommand() throws Exception {
        int databaseSizeBeforeCreate = commandRepository.findAll().size();
        // Create the Command
        CommandDTO commandDTO = commandMapper.toDto(command);
        restCommandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(commandDTO)))
            .andExpect(status().isCreated());

        // Validate the Command in the database
        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeCreate + 1);
        Command testCommand = commandList.get(commandList.size() - 1);
        assertThat(testCommand.getAddressClient()).isEqualTo(DEFAULT_ADDRESS_CLIENT);
        assertThat(testCommand.getDateClient()).isEqualTo(DEFAULT_DATE_CLIENT);
    }

    @Test
    @Transactional
    void createCommandWithExistingId() throws Exception {
        // Create the Command with an existing ID
        command.setId(1L);
        CommandDTO commandDTO = commandMapper.toDto(command);

        int databaseSizeBeforeCreate = commandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(commandDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Command in the database
        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkAddressClientIsRequired() throws Exception {
        int databaseSizeBeforeTest = commandRepository.findAll().size();
        // set the field null
        command.setAddressClient(null);

        // Create the Command, which fails.
        CommandDTO commandDTO = commandMapper.toDto(command);

        restCommandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(commandDTO)))
            .andExpect(status().isBadRequest());

        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateClientIsRequired() throws Exception {
        int databaseSizeBeforeTest = commandRepository.findAll().size();
        // set the field null
        command.setDateClient(null);

        // Create the Command, which fails.
        CommandDTO commandDTO = commandMapper.toDto(command);

        restCommandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(commandDTO)))
            .andExpect(status().isBadRequest());

        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCommands() throws Exception {
        // Initialize the database
        commandRepository.saveAndFlush(command);

        // Get all the commandList
        restCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(command.getId().intValue())))
            .andExpect(jsonPath("$.[*].addressClient").value(hasItem(DEFAULT_ADDRESS_CLIENT)))
            .andExpect(jsonPath("$.[*].dateClient").value(hasItem(DEFAULT_DATE_CLIENT)));
    }

    @Test
    @Transactional
    void getCommand() throws Exception {
        // Initialize the database
        commandRepository.saveAndFlush(command);

        // Get the command
        restCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, command.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(command.getId().intValue()))
            .andExpect(jsonPath("$.addressClient").value(DEFAULT_ADDRESS_CLIENT))
            .andExpect(jsonPath("$.dateClient").value(DEFAULT_DATE_CLIENT));
    }

    @Test
    @Transactional
    void getNonExistingCommand() throws Exception {
        // Get the command
        restCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCommand() throws Exception {
        // Initialize the database
        commandRepository.saveAndFlush(command);

        int databaseSizeBeforeUpdate = commandRepository.findAll().size();

        // Update the command
        Command updatedCommand = commandRepository.findById(command.getId()).get();
        // Disconnect from session so that the updates on updatedCommand are not directly saved in db
        em.detach(updatedCommand);
        updatedCommand.addressClient(UPDATED_ADDRESS_CLIENT).dateClient(UPDATED_DATE_CLIENT);
        CommandDTO commandDTO = commandMapper.toDto(updatedCommand);

        restCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, commandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(commandDTO))
            )
            .andExpect(status().isOk());

        // Validate the Command in the database
        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeUpdate);
        Command testCommand = commandList.get(commandList.size() - 1);
        assertThat(testCommand.getAddressClient()).isEqualTo(UPDATED_ADDRESS_CLIENT);
        assertThat(testCommand.getDateClient()).isEqualTo(UPDATED_DATE_CLIENT);
    }

    @Test
    @Transactional
    void putNonExistingCommand() throws Exception {
        int databaseSizeBeforeUpdate = commandRepository.findAll().size();
        command.setId(count.incrementAndGet());

        // Create the Command
        CommandDTO commandDTO = commandMapper.toDto(command);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, commandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(commandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Command in the database
        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCommand() throws Exception {
        int databaseSizeBeforeUpdate = commandRepository.findAll().size();
        command.setId(count.incrementAndGet());

        // Create the Command
        CommandDTO commandDTO = commandMapper.toDto(command);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(commandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Command in the database
        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCommand() throws Exception {
        int databaseSizeBeforeUpdate = commandRepository.findAll().size();
        command.setId(count.incrementAndGet());

        // Create the Command
        CommandDTO commandDTO = commandMapper.toDto(command);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommandMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(commandDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Command in the database
        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCommandWithPatch() throws Exception {
        // Initialize the database
        commandRepository.saveAndFlush(command);

        int databaseSizeBeforeUpdate = commandRepository.findAll().size();

        // Update the command using partial update
        Command partialUpdatedCommand = new Command();
        partialUpdatedCommand.setId(command.getId());

        partialUpdatedCommand.addressClient(UPDATED_ADDRESS_CLIENT).dateClient(UPDATED_DATE_CLIENT);

        restCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCommand))
            )
            .andExpect(status().isOk());

        // Validate the Command in the database
        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeUpdate);
        Command testCommand = commandList.get(commandList.size() - 1);
        assertThat(testCommand.getAddressClient()).isEqualTo(UPDATED_ADDRESS_CLIENT);
        assertThat(testCommand.getDateClient()).isEqualTo(UPDATED_DATE_CLIENT);
    }

    @Test
    @Transactional
    void fullUpdateCommandWithPatch() throws Exception {
        // Initialize the database
        commandRepository.saveAndFlush(command);

        int databaseSizeBeforeUpdate = commandRepository.findAll().size();

        // Update the command using partial update
        Command partialUpdatedCommand = new Command();
        partialUpdatedCommand.setId(command.getId());

        partialUpdatedCommand.addressClient(UPDATED_ADDRESS_CLIENT).dateClient(UPDATED_DATE_CLIENT);

        restCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCommand))
            )
            .andExpect(status().isOk());

        // Validate the Command in the database
        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeUpdate);
        Command testCommand = commandList.get(commandList.size() - 1);
        assertThat(testCommand.getAddressClient()).isEqualTo(UPDATED_ADDRESS_CLIENT);
        assertThat(testCommand.getDateClient()).isEqualTo(UPDATED_DATE_CLIENT);
    }

    @Test
    @Transactional
    void patchNonExistingCommand() throws Exception {
        int databaseSizeBeforeUpdate = commandRepository.findAll().size();
        command.setId(count.incrementAndGet());

        // Create the Command
        CommandDTO commandDTO = commandMapper.toDto(command);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, commandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(commandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Command in the database
        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCommand() throws Exception {
        int databaseSizeBeforeUpdate = commandRepository.findAll().size();
        command.setId(count.incrementAndGet());

        // Create the Command
        CommandDTO commandDTO = commandMapper.toDto(command);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(commandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Command in the database
        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCommand() throws Exception {
        int databaseSizeBeforeUpdate = commandRepository.findAll().size();
        command.setId(count.incrementAndGet());

        // Create the Command
        CommandDTO commandDTO = commandMapper.toDto(command);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommandMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(commandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Command in the database
        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCommand() throws Exception {
        // Initialize the database
        commandRepository.saveAndFlush(command);

        int databaseSizeBeforeDelete = commandRepository.findAll().size();

        // Delete the command
        restCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, command.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Command> commandList = commandRepository.findAll();
        assertThat(commandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
