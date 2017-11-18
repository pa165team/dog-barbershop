package cz.muni.fi.pa165.dto.servicerecord;

import javax.validation.constraints.NotNull;
import java.sql.Time;

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
}
