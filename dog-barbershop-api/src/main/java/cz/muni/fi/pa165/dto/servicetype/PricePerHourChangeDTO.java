package cz.muni.fi.pa165.dto.servicetype;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Jan Kalfus
 */
public class PricePerHourChangeDTO {

    @NotNull
    private Long serviceTypeId;

    @NotNull
    @Min(0)
    private BigDecimal pricePerHour;

    public Long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
