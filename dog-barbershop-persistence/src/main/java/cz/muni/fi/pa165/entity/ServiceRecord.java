package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author Jan Kalfus
 */
@Entity
public class ServiceRecord {

    @Id
    @Column(name = "serviceRecord_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private BigDecimal actualPrice;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateProvided;

    @Column(nullable = false)
    private Integer lengthMinutes;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "dog_id")
    private Dog dog;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "serviceType_id")
    private ServiceType serviceType;

    public ServiceRecord() {
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Date getDateProvided() {
        return dateProvided;
    }

    public void setDateProvided(Date dateProvided) {
        this.dateProvided = dateProvided;
    }

    public Integer getLengthMinutes() {
        return lengthMinutes;
    }

    public void setLengthMinutes(Integer lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceRecord)) return false;
        ServiceRecord serviceRecord = (ServiceRecord) o;
        return Objects.equals(getActualPrice(), serviceRecord.getActualPrice()) &&
            Objects.equals(getDateProvided(), serviceRecord.getDateProvided()) &&
            Objects.equals(getLengthMinutes(), serviceRecord.getLengthMinutes()) &&
            Objects.equals(getDog(), serviceRecord.getDog()) &&
            Objects.equals(getEmployee(), serviceRecord.getEmployee()) &&
            Objects.equals(getServiceType(), serviceRecord.getServiceType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActualPrice(), getDateProvided(), getLengthMinutes(), getDog(), getEmployee(), getServiceType());
    }
}
