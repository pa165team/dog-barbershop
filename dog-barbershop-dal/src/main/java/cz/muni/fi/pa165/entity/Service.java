package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 * @author Jan Kalfus
 */
@Entity
public class Service {

    @Id
    @Column(name = "service_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private BigDecimal actualPrice;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateProvided;

    @NotNull
    @Column(nullable = false)
    private Time length;

    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "serviceType_id")
    private ServiceType serviceType;

    public Service() {
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

    public Time getLength() {
        return length;
    }

    public void setLength(Time length) {
        this.length = length;
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
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return Objects.equals(getActualPrice(), service.getActualPrice()) &&
            Objects.equals(getDateProvided(), service.getDateProvided()) &&
            Objects.equals(getLength(), service.getLength()) &&
            Objects.equals(getDog(), service.getDog()) &&
            Objects.equals(getEmployee(), service.getEmployee()) &&
            Objects.equals(getServiceType(), service.getServiceType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActualPrice(), getDateProvided(), getLength(), getDog(), getEmployee(), getServiceType());
    }
}
