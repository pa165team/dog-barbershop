package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 * @author Jan Kalfus
 */
@Entity
public class ServiceType {
    @Id
    @Column(name = "serviceType_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private BigDecimal pricePerHour;

    @OneToMany(mappedBy = "serviceType")
    private Set<ServiceRecord> serviceRecords;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceType)) return false;
        ServiceType that = (ServiceType) o;
        return Objects.equals(getName(), that.getName()) &&
            Objects.equals(getDescription(), that.getDescription()) &&
            Objects.equals(getPricePerHour(), that.getPricePerHour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getPricePerHour());
    }
}
