package cz.muni.fi.pa165.dto.servicetype;

import javax.validation.constraints.NotNull;

/**
 * @author Jan Kalfus
 */
public class DescriptionChangeDTO {

    @NotNull
    private Long serviceTypeId;
    private String description;

    public Long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
