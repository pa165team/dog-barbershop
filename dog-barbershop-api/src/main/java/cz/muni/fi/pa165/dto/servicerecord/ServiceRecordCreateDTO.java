package cz.muni.fi.pa165.dto.servicerecord;

import cz.muni.fi.pa165.ValidationMessages;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Jan Kalfus
 */
public class ServiceRecordCreateDTO {

    @NotNull(message = ValidationMessages.LENGTH)
    @Min(value = 0, message = ValidationMessages.LENGTH)
    private Integer lengthMinutes;

    @NotNull
    private Long dogId;

    @NotNull(message = ValidationMessages.EMPLOYEE_SELECT)
    private Long employeeId;

    @NotNull(message = ValidationMessages.TYPE_SELECT)
    private Long serviceTypeId;

    public Integer getLengthMinutes() {
        return lengthMinutes;
    }

    public void setLengthMinutes(Integer lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
    }

    public Long getDogId() {
        return dogId;
    }

    public void setDogId(Long dogId) {
        this.dogId = dogId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRecordCreateDTO that = (ServiceRecordCreateDTO) o;
        return Objects.equals(getLengthMinutes(), that.getLengthMinutes()) &&
            Objects.equals(getDogId(), that.getDogId()) &&
            Objects.equals(getEmployeeId(), that.getEmployeeId()) &&
            Objects.equals(getServiceTypeId(), that.getServiceTypeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLengthMinutes(), getDogId(), getEmployeeId(), getServiceTypeId());
    }
}
