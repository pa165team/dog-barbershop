package cz.muni.fi.pa165.dto.servicerecord;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Objects;

/**
 * @author Jan Kalfus
 */
public class ServiceRecordCreateDTO {

    @NotNull
    private Time length;

    @NotNull
    private Long dogId;

    @NotNull
    private Long employeeId;

    @NotNull
    private Long serviceTypeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRecordCreateDTO that = (ServiceRecordCreateDTO) o;
        return Objects.equals(length, that.length) &&
            Objects.equals(dogId, that.dogId) &&
            Objects.equals(employeeId, that.employeeId) &&
            Objects.equals(serviceTypeId, that.serviceTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, dogId, employeeId, serviceTypeId);
    }
}
