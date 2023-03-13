package polytech.info.gl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Command.
 */
@Entity
@Table(name = "command")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Command implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "address_client", nullable = false)
    private String addressClient;

    @NotNull
    @Column(name = "date_client", nullable = false)
    private String dateClient;

    @ManyToOne
    @JsonIgnoreProperties(value = { "carts", "commands" }, allowSetters = true)
    private Client client;

    @JsonIgnoreProperties(value = { "command", "client", "shop" }, allowSetters = true)
    @OneToOne(mappedBy = "command")
    private Cart cart;

    @ManyToOne
    @JsonIgnoreProperties(value = { "commands", "cooperative" }, allowSetters = true)
    private Driver driver;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Command id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressClient() {
        return this.addressClient;
    }

    public Command addressClient(String addressClient) {
        this.setAddressClient(addressClient);
        return this;
    }

    public void setAddressClient(String addressClient) {
        this.addressClient = addressClient;
    }

    public String getDateClient() {
        return this.dateClient;
    }

    public Command dateClient(String dateClient) {
        this.setDateClient(dateClient);
        return this;
    }

    public void setDateClient(String dateClient) {
        this.dateClient = dateClient;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Command client(Client client) {
        this.setClient(client);
        return this;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        if (this.cart != null) {
            this.cart.setCommand(null);
        }
        if (cart != null) {
            cart.setCommand(this);
        }
        this.cart = cart;
    }

    public Command cart(Cart cart) {
        this.setCart(cart);
        return this;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Command driver(Driver driver) {
        this.setDriver(driver);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Command)) {
            return false;
        }
        return id != null && id.equals(((Command) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Command{" +
            "id=" + getId() +
            ", addressClient='" + getAddressClient() + "'" +
            ", dateClient='" + getDateClient() + "'" +
            "}";
    }
}
