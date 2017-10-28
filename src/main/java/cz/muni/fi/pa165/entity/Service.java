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

    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Service)) return false;
        Service service = (Service) o;
        return Objects.equals(getDescription(), service.getDescription()) &&
            Objects.equals(getPrice(), service.getPrice()) &&
            Objects.equals(getDateProvided(), service.getDateProvided()) &&
            Objects.equals(getLength(), service.getLength()) &&
            Objects.equals(dog, service.dog) &&
            Objects.equals(employee, service.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getPrice(), getDateProvided(), getLength(), dog, employee);
    }
}
