package polytech.info.gl.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link polytech.info.gl.domain.Driver} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DriverDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String firstnameDriver;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String lastnameDriver;

    private Integer phoneCountryCodeDriver;

    private Integer phoneDriver;

    private CooperativeDTO cooperative;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstnameDriver() {
        return firstnameDriver;
    }

    public void setFirstnameDriver(String firstnameDriver) {
        this.firstnameDriver = firstnameDriver;
    }

    public String getLastnameDriver() {
        return lastnameDriver;
    }

    public void setLastnameDriver(String lastnameDriver) {
        this.lastnameDriver = lastnameDriver;
    }

    public Integer getPhoneCountryCodeDriver() {
        return phoneCountryCodeDriver;
    }

    public void setPhoneCountryCodeDriver(Integer phoneCountryCodeDriver) {
        this.phoneCountryCodeDriver = phoneCountryCodeDriver;
    }

    public Integer getPhoneDriver() {
        return phoneDriver;
    }

    public void setPhoneDriver(Integer phoneDriver) {
        this.phoneDriver = phoneDriver;
    }

    public CooperativeDTO getCooperative() {
        return cooperative;
    }

    public void setCooperative(CooperativeDTO cooperative) {
        this.cooperative = cooperative;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DriverDTO)) {
            return false;
        }

        DriverDTO driverDTO = (DriverDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, driverDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DriverDTO{" +
            "id=" + getId() +
            ", firstnameDriver='" + getFirstnameDriver() + "'" +
            ", lastnameDriver='" + getLastnameDriver() + "'" +
            ", phoneCountryCodeDriver=" + getPhoneCountryCodeDriver() +
            ", phoneDriver=" + getPhoneDriver() +
            ", cooperative=" + getCooperative() +
            "}";
    }
}
