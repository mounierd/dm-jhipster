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
import polytech.info.gl.domain.Client;
import polytech.info.gl.repository.ClientRepository;
import polytech.info.gl.service.dto.ClientDTO;
import polytech.info.gl.service.mapper.ClientMapper;

/**
 * Integration tests for the {@link ClientResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientResourceIT {

    private static final String DEFAULT_ID_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_ID_CLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME_CLIENT = "Onhekne";
    private static final String UPDATED_FIRSTNAME_CLIENT = "Twsvw";

    private static final String DEFAULT_LASTNAME_CLIENT = "Hr";
    private static final String UPDATED_LASTNAME_CLIENT = "Jro";

    private static final String DEFAULT_EMAIL_CLIENT = "A@P5l7Og\\QtnNkk";
    private static final String UPDATED_EMAIL_CLIENT = "YMYz1.@012n\\FDHu";

    private static final Integer DEFAULT_PHONE_COUNTRY_CODE_CLIENT = 1;
    private static final Integer UPDATED_PHONE_COUNTRY_CODE_CLIENT = 2;

    private static final Integer DEFAULT_PHONE_CLIENT = 1;
    private static final Integer UPDATED_PHONE_CLIENT = 2;

    private static final String DEFAULT_ADDRESS_C = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_C = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/clients";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientMockMvc;

    private Client client;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createEntity(EntityManager em) {
        Client client = new Client()
            .idClient(DEFAULT_ID_CLIENT)
            .firstnameClient(DEFAULT_FIRSTNAME_CLIENT)
            .lastnameClient(DEFAULT_LASTNAME_CLIENT)
            .emailClient(DEFAULT_EMAIL_CLIENT)
            .phoneCountryCodeClient(DEFAULT_PHONE_COUNTRY_CODE_CLIENT)
            .phoneClient(DEFAULT_PHONE_CLIENT)
            .addressC(DEFAULT_ADDRESS_C);
        return client;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createUpdatedEntity(EntityManager em) {
        Client client = new Client()
            .idClient(UPDATED_ID_CLIENT)
            .firstnameClient(UPDATED_FIRSTNAME_CLIENT)
            .lastnameClient(UPDATED_LASTNAME_CLIENT)
            .emailClient(UPDATED_EMAIL_CLIENT)
            .phoneCountryCodeClient(UPDATED_PHONE_COUNTRY_CODE_CLIENT)
            .phoneClient(UPDATED_PHONE_CLIENT)
            .addressC(UPDATED_ADDRESS_C);
        return client;
    }

    @BeforeEach
    public void initTest() {
        client = createEntity(em);
    }

    @Test
    @Transactional
    void createClient() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();
        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);
        restClientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isCreated());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate + 1);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getIdClient()).isEqualTo(DEFAULT_ID_CLIENT);
        assertThat(testClient.getFirstnameClient()).isEqualTo(DEFAULT_FIRSTNAME_CLIENT);
        assertThat(testClient.getLastnameClient()).isEqualTo(DEFAULT_LASTNAME_CLIENT);
        assertThat(testClient.getEmailClient()).isEqualTo(DEFAULT_EMAIL_CLIENT);
        assertThat(testClient.getPhoneCountryCodeClient()).isEqualTo(DEFAULT_PHONE_COUNTRY_CODE_CLIENT);
        assertThat(testClient.getPhoneClient()).isEqualTo(DEFAULT_PHONE_CLIENT);
        assertThat(testClient.getAddressC()).isEqualTo(DEFAULT_ADDRESS_C);
    }

    @Test
    @Transactional
    void createClientWithExistingId() throws Exception {
        // Create the Client with an existing ID
        client.setId(1L);
        ClientDTO clientDTO = clientMapper.toDto(client);

        int databaseSizeBeforeCreate = clientRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkIdClientIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setIdClient(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFirstnameClientIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setFirstnameClient(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLastnameClientIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setLastnameClient(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmailClientIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setEmailClient(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAddressCIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setAddressC(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllClients() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList
        restClientMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(client.getId().intValue())))
            .andExpect(jsonPath("$.[*].idClient").value(hasItem(DEFAULT_ID_CLIENT)))
            .andExpect(jsonPath("$.[*].firstnameClient").value(hasItem(DEFAULT_FIRSTNAME_CLIENT)))
            .andExpect(jsonPath("$.[*].lastnameClient").value(hasItem(DEFAULT_LASTNAME_CLIENT)))
            .andExpect(jsonPath("$.[*].emailClient").value(hasItem(DEFAULT_EMAIL_CLIENT)))
            .andExpect(jsonPath("$.[*].phoneCountryCodeClient").value(hasItem(DEFAULT_PHONE_COUNTRY_CODE_CLIENT)))
            .andExpect(jsonPath("$.[*].phoneClient").value(hasItem(DEFAULT_PHONE_CLIENT)))
            .andExpect(jsonPath("$.[*].addressC").value(hasItem(DEFAULT_ADDRESS_C)));
    }

    @Test
    @Transactional
    void getClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get the client
        restClientMockMvc
            .perform(get(ENTITY_API_URL_ID, client.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(client.getId().intValue()))
            .andExpect(jsonPath("$.idClient").value(DEFAULT_ID_CLIENT))
            .andExpect(jsonPath("$.firstnameClient").value(DEFAULT_FIRSTNAME_CLIENT))
            .andExpect(jsonPath("$.lastnameClient").value(DEFAULT_LASTNAME_CLIENT))
            .andExpect(jsonPath("$.emailClient").value(DEFAULT_EMAIL_CLIENT))
            .andExpect(jsonPath("$.phoneCountryCodeClient").value(DEFAULT_PHONE_COUNTRY_CODE_CLIENT))
            .andExpect(jsonPath("$.phoneClient").value(DEFAULT_PHONE_CLIENT))
            .andExpect(jsonPath("$.addressC").value(DEFAULT_ADDRESS_C));
    }

    @Test
    @Transactional
    void getNonExistingClient() throws Exception {
        // Get the client
        restClientMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Update the client
        Client updatedClient = clientRepository.findById(client.getId()).get();
        // Disconnect from session so that the updates on updatedClient are not directly saved in db
        em.detach(updatedClient);
        updatedClient
            .idClient(UPDATED_ID_CLIENT)
            .firstnameClient(UPDATED_FIRSTNAME_CLIENT)
            .lastnameClient(UPDATED_LASTNAME_CLIENT)
            .emailClient(UPDATED_EMAIL_CLIENT)
            .phoneCountryCodeClient(UPDATED_PHONE_COUNTRY_CODE_CLIENT)
            .phoneClient(UPDATED_PHONE_CLIENT)
            .addressC(UPDATED_ADDRESS_C);
        ClientDTO clientDTO = clientMapper.toDto(updatedClient);

        restClientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientDTO))
            )
            .andExpect(status().isOk());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getIdClient()).isEqualTo(UPDATED_ID_CLIENT);
        assertThat(testClient.getFirstnameClient()).isEqualTo(UPDATED_FIRSTNAME_CLIENT);
        assertThat(testClient.getLastnameClient()).isEqualTo(UPDATED_LASTNAME_CLIENT);
        assertThat(testClient.getEmailClient()).isEqualTo(UPDATED_EMAIL_CLIENT);
        assertThat(testClient.getPhoneCountryCodeClient()).isEqualTo(UPDATED_PHONE_COUNTRY_CODE_CLIENT);
        assertThat(testClient.getPhoneClient()).isEqualTo(UPDATED_PHONE_CLIENT);
        assertThat(testClient.getAddressC()).isEqualTo(UPDATED_ADDRESS_C);
    }

    @Test
    @Transactional
    void putNonExistingClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();
        client.setId(count.incrementAndGet());

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();
        client.setId(count.incrementAndGet());

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();
        client.setId(count.incrementAndGet());

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClientWithPatch() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Update the client using partial update
        Client partialUpdatedClient = new Client();
        partialUpdatedClient.setId(client.getId());

        partialUpdatedClient
            .idClient(UPDATED_ID_CLIENT)
            .emailClient(UPDATED_EMAIL_CLIENT)
            .phoneCountryCodeClient(UPDATED_PHONE_COUNTRY_CODE_CLIENT)
            .phoneClient(UPDATED_PHONE_CLIENT);

        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClient.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClient))
            )
            .andExpect(status().isOk());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getIdClient()).isEqualTo(UPDATED_ID_CLIENT);
        assertThat(testClient.getFirstnameClient()).isEqualTo(DEFAULT_FIRSTNAME_CLIENT);
        assertThat(testClient.getLastnameClient()).isEqualTo(DEFAULT_LASTNAME_CLIENT);
        assertThat(testClient.getEmailClient()).isEqualTo(UPDATED_EMAIL_CLIENT);
        assertThat(testClient.getPhoneCountryCodeClient()).isEqualTo(UPDATED_PHONE_COUNTRY_CODE_CLIENT);
        assertThat(testClient.getPhoneClient()).isEqualTo(UPDATED_PHONE_CLIENT);
        assertThat(testClient.getAddressC()).isEqualTo(DEFAULT_ADDRESS_C);
    }

    @Test
    @Transactional
    void fullUpdateClientWithPatch() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Update the client using partial update
        Client partialUpdatedClient = new Client();
        partialUpdatedClient.setId(client.getId());

        partialUpdatedClient
            .idClient(UPDATED_ID_CLIENT)
            .firstnameClient(UPDATED_FIRSTNAME_CLIENT)
            .lastnameClient(UPDATED_LASTNAME_CLIENT)
            .emailClient(UPDATED_EMAIL_CLIENT)
            .phoneCountryCodeClient(UPDATED_PHONE_COUNTRY_CODE_CLIENT)
            .phoneClient(UPDATED_PHONE_CLIENT)
            .addressC(UPDATED_ADDRESS_C);

        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClient.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClient))
            )
            .andExpect(status().isOk());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getIdClient()).isEqualTo(UPDATED_ID_CLIENT);
        assertThat(testClient.getFirstnameClient()).isEqualTo(UPDATED_FIRSTNAME_CLIENT);
        assertThat(testClient.getLastnameClient()).isEqualTo(UPDATED_LASTNAME_CLIENT);
        assertThat(testClient.getEmailClient()).isEqualTo(UPDATED_EMAIL_CLIENT);
        assertThat(testClient.getPhoneCountryCodeClient()).isEqualTo(UPDATED_PHONE_COUNTRY_CODE_CLIENT);
        assertThat(testClient.getPhoneClient()).isEqualTo(UPDATED_PHONE_CLIENT);
        assertThat(testClient.getAddressC()).isEqualTo(UPDATED_ADDRESS_C);
    }

    @Test
    @Transactional
    void patchNonExistingClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();
        client.setId(count.incrementAndGet());

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clientDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();
        client.setId(count.incrementAndGet());

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();
        client.setId(count.incrementAndGet());

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(clientDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeDelete = clientRepository.findAll().size();

        // Delete the client
        restClientMockMvc
            .perform(delete(ENTITY_API_URL_ID, client.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
