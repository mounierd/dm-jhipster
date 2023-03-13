package polytech.info.gl.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link polytech.info.gl.domain.Client} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientDTO implements Serializable {

    private Long id;

    @NotNull
    private String idClient;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String firstnameClient;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String lastnameClient;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\\\.]+)@([a-zA-Z0-9_\\-\\\\.]+)\\\\.([a-zA-Z]{2,5})$")
    private String emailClient;

    private Integer phoneCountryCodeClient;

    private Integer phoneClient;

    @NotNull
    @Size(max = 100)
    private String addressC;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getFirstnameClient() {
        return firstnameClient;
    }

    public void setFirstnameClient(String firstnameClient) {
        this.firstnameClient = firstnameClient;
    }

    public String getLastnameClient() {
        return lastnameClient;
    }

    public void setLastnameClient(String lastnameClient) {
        this.lastnameClient = lastnameClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public Integer getPhoneCountryCodeClient() {
        return phoneCountryCodeClient;
    }

    public void setPhoneCountryCodeClient(Integer phoneCountryCodeClient) {
        this.phoneCountryCodeClient = phoneCountryCodeClient;
    }

    public Integer getPhoneClient() {
        return phoneClient;
    }

    public void setPhoneClient(Integer phoneClient) {
        this.phoneClient = phoneClient;
    }

    public String getAddressC() {
        return addressC;
    }

    public void setAddressC(String addressC) {
        this.addressC = addressC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientDTO)) {
            return false;
        }

        ClientDTO clientDTO = (ClientDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, clientDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientDTO{" +
            "id=" + getId() +
            ", idClient='" + getIdClient() + "'" +
            ", firstnameClient='" + getFirstnameClient() + "'" +
            ", lastnameClient='" + getLastnameClient() + "'" +
            ", emailClient='" + getEmailClient() + "'" +
            ", phoneCountryCodeClient=" + getPhoneCountryCodeClient() +
            ", phoneClient=" + getPhoneClient() +
            ", addressC='" + getAddressC() + "'" +
            "}";
    }
}
