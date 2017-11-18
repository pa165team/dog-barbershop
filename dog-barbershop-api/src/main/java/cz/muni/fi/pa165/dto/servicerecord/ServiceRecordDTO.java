package cz.muni.fi.pa165.dto.servicerecord;

import cz.muni.fi.pa165.dto.DogDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.servicetype.ServiceTypeDTO;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 * @author Jan Kalfus
 */
public class ServiceRecordDTO {
    private Long id;
    private BigDecimal actualPrice;
    private Date dateProvided;
    private Time length;
    private DogDTO dog;
    private EmployeeDTO employee;
    private ServiceTypeDTO serviceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DogDTO getDog() {
        return dog;
    }

    public void setDog(DogDTO dog) {
        this.dog = dog;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public ServiceTypeDTO getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceTypeDTO serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRecordDTO that = (ServiceRecordDTO) o;
        return Objects.equals(getActualPrice(), that.getActualPrice()) &&
            Objects.equals(getDateProvided(), that.getDateProvided()) &&
            Objects.equals(getLength(), that.getLength()) &&
            Objects.equals(getDog(), that.getDog()) &&
            Objects.equals(getEmployee(), that.getEmployee()) &&
            Objects.equals(getServiceType(), that.getServiceType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActualPrice(), getDateProvided(), getLength(), getDog(), getEmployee(), getServiceType());
    }
}
