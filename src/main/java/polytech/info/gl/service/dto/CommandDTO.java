package polytech.info.gl.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link polytech.info.gl.domain.Command} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CommandDTO implements Serializable {

    private Long id;

    @NotNull
    private String addressClient;

    @NotNull
    private String dateClient;

    private ClientDTO client;

    private DriverDTO driver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressClient() {
        return addressClient;
    }

    public void setAddressClient(String addressClient) {
        this.addressClient = addressClient;
    }

    public String getDateClient() {
        return dateClient;
    }

    public void setDateClient(String dateClient) {
        this.dateClient = dateClient;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public DriverDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverDTO driver) {
        this.driver = driver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandDTO)) {
            return false;
        }

        CommandDTO commandDTO = (CommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, commandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommandDTO{" +
            "id=" + getId() +
            ", addressClient='" + getAddressClient() + "'" +
            ", dateClient='" + getDateClient() + "'" +
            ", client=" + getClient() +
            ", driver=" + getDriver() +
            "}";
    }
}
