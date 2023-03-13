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
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "id_client", nullable = false)
    private String idClient;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Column(name = "firstname_client", length = 30, nullable = false)
    private String firstnameClient;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Column(name = "lastname_client", length = 30, nullable = false)
    private String lastnameClient;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\\\.]+)@([a-zA-Z0-9_\\-\\\\.]+)\\\\.([a-zA-Z]{2,5})$")
    @Column(name = "email_client", length = 50, nullable = false)
    private String emailClient;

    @Column(name = "phone_country_code_client")
    private Integer phoneCountryCodeClient;

    @Column(name = "phone_client")
    private Integer phoneClient;

    @NotNull
    @Size(max = 100)
    @Column(name = "address_c", length = 100, nullable = false)
    private String addressC;

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "command", "client", "shop" }, allowSetters = true)
    private Set<Cart> carts = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "client", "cart", "driver" }, allowSetters = true)
    private Set<Command> commands = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Client id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdClient() {
        return this.idClient;
    }

    public Client idClient(String idClient) {
        this.setIdClient(idClient);
        return this;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getFirstnameClient() {
        return this.firstnameClient;
    }

    public Client firstnameClient(String firstnameClient) {
        this.setFirstnameClient(firstnameClient);
        return this;
    }

    public void setFirstnameClient(String firstnameClient) {
        this.firstnameClient = firstnameClient;
    }

    public String getLastnameClient() {
        return this.lastnameClient;
    }

    public Client lastnameClient(String lastnameClient) {
        this.setLastnameClient(lastnameClient);
        return this;
    }

    public void setLastnameClient(String lastnameClient) {
        this.lastnameClient = lastnameClient;
    }

    public String getEmailClient() {
        return this.emailClient;
    }

    public Client emailClient(String emailClient) {
        this.setEmailClient(emailClient);
        return this;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public Integer getPhoneCountryCodeClient() {
        return this.phoneCountryCodeClient;
    }

    public Client phoneCountryCodeClient(Integer phoneCountryCodeClient) {
        this.setPhoneCountryCodeClient(phoneCountryCodeClient);
        return this;
    }

    public void setPhoneCountryCodeClient(Integer phoneCountryCodeClient) {
        this.phoneCountryCodeClient = phoneCountryCodeClient;
    }

    public Integer getPhoneClient() {
        return this.phoneClient;
    }

    public Client phoneClient(Integer phoneClient) {
        this.setPhoneClient(phoneClient);
        return this;
    }

    public void setPhoneClient(Integer phoneClient) {
        this.phoneClient = phoneClient;
    }

    public String getAddressC() {
        return this.addressC;
    }

    public Client addressC(String addressC) {
        this.setAddressC(addressC);
        return this;
    }

    public void setAddressC(String addressC) {
        this.addressC = addressC;
    }

    public Set<Cart> getCarts() {
        return this.carts;
    }

    public void setCarts(Set<Cart> carts) {
        if (this.carts != null) {
            this.carts.forEach(i -> i.setClient(null));
        }
        if (carts != null) {
            carts.forEach(i -> i.setClient(this));
        }
        this.carts = carts;
    }

    public Client carts(Set<Cart> carts) {
        this.setCarts(carts);
        return this;
    }

    public Client addCart(Cart cart) {
        this.carts.add(cart);
        cart.setClient(this);
        return this;
    }

    public Client removeCart(Cart cart) {
        this.carts.remove(cart);
        cart.setClient(null);
        return this;
    }

    public Set<Command> getCommands() {
        return this.commands;
    }

    public void setCommands(Set<Command> commands) {
        if (this.commands != null) {
            this.commands.forEach(i -> i.setClient(null));
        }
        if (commands != null) {
            commands.forEach(i -> i.setClient(this));
        }
        this.commands = commands;
    }

    public Client commands(Set<Command> commands) {
        this.setCommands(commands);
        return this;
    }

    public Client addCommand(Command command) {
        this.commands.add(command);
        command.setClient(this);
        return this;
    }

    public Client removeCommand(Command command) {
        this.commands.remove(command);
        command.setClient(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
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
