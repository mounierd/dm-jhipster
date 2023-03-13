package polytech.info.gl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Driver.
 */
@Entity
@Table(name = "driver")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Column(name = "firstname_driver", length = 30, nullable = false)
    private String firstnameDriver;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Column(name = "lastname_driver", length = 30, nullable = false)
    private String lastnameDriver;

    @Column(name = "phone_country_code_driver")
    private Integer phoneCountryCodeDriver;

    @Column(name = "phone_driver")
    private Integer phoneDriver;

    @OneToMany(mappedBy = "driver")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "client", "cart", "driver" }, allowSetters = true)
    private Set<Command> commands = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "drivers" }, allowSetters = true)
    private Cooperative cooperative;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Driver id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstnameDriver() {
        return this.firstnameDriver;
    }

    public Driver firstnameDriver(String firstnameDriver) {
        this.setFirstnameDriver(firstnameDriver);
        return this;
    }

    public void setFirstnameDriver(String firstnameDriver) {
        this.firstnameDriver = firstnameDriver;
    }

    public String getLastnameDriver() {
        return this.lastnameDriver;
    }

    public Driver lastnameDriver(String lastnameDriver) {
        this.setLastnameDriver(lastnameDriver);
        return this;
    }

    public void setLastnameDriver(String lastnameDriver) {
        this.lastnameDriver = lastnameDriver;
    }

    public Integer getPhoneCountryCodeDriver() {
        return this.phoneCountryCodeDriver;
    }

    public Driver phoneCountryCodeDriver(Integer phoneCountryCodeDriver) {
        this.setPhoneCountryCodeDriver(phoneCountryCodeDriver);
        return this;
    }

    public void setPhoneCountryCodeDriver(Integer phoneCountryCodeDriver) {
        this.phoneCountryCodeDriver = phoneCountryCodeDriver;
    }

    public Integer getPhoneDriver() {
        return this.phoneDriver;
    }

    public Driver phoneDriver(Integer phoneDriver) {
        this.setPhoneDriver(phoneDriver);
        return this;
    }

    public void setPhoneDriver(Integer phoneDriver) {
        this.phoneDriver = phoneDriver;
    }

    public Set<Command> getCommands() {
        return this.commands;
    }

    public void setCommands(Set<Command> commands) {
        if (this.commands != null) {
            this.commands.forEach(i -> i.setDriver(null));
        }
        if (commands != null) {
            commands.forEach(i -> i.setDriver(this));
        }
        this.commands = commands;
    }

    public Driver commands(Set<Command> commands) {
        this.setCommands(commands);
        return this;
    }

    public Driver addCommand(Command command) {
        this.commands.add(command);
        command.setDriver(this);
        return this;
    }

    public Driver removeCommand(Command command) {
        this.commands.remove(command);
        command.setDriver(null);
        return this;
    }

    public Cooperative getCooperative() {
        return this.cooperative;
    }

    public void setCooperative(Cooperative cooperative) {
        this.cooperative = cooperative;
    }

    public Driver cooperative(Cooperative cooperative) {
        this.setCooperative(cooperative);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Driver)) {
            return false;
        }
        return id != null && id.equals(((Driver) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Driver{" +
            "id=" + getId() +
            ", firstnameDriver='" + getFirstnameDriver() + "'" +
            ", lastnameDriver='" + getLastnameDriver() + "'" +
            ", phoneCountryCodeDriver=" + getPhoneCountryCodeDriver() +
            ", phoneDriver=" + getPhoneDriver() +
            "}";
    }
}
