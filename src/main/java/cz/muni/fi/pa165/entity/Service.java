package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * @author Jan Kalfus
 */
@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateProvided;

    @NotNull
    @Column(nullable = false)
    private Time length;

    //TODO: Martin here, this might be what you want for association with dog. Finish pls :)
    /*@NotNull
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;*/

    public Service() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDateProvided() {
        return dateProvided;
    }

    public void setDateProvided(Date dateProvided) {
        this.dateProvided = dateProvided;
    }

    public Time getLength() {
        return length;
    }

    public void setLength(Time length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Service)) return false;

        Service service = (Service) o;

        return getDescription().equals(service.getDescription()) &&
            getPrice().equals(service.getPrice()) &&
            getDateProvided().equals(service.getDateProvided()) &&
            getLength().equals(service.getLength());
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + dateProvided.hashCode();
        result = 31 * result + length.hashCode();
        return result;
    }
}
