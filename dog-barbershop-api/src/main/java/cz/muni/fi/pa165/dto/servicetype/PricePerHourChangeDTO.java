package cz.muni.fi.pa165.dto.servicetype;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricePerHourChangeDTO that = (PricePerHourChangeDTO) o;
        return Objects.equals(getPricePerHour(), that.getPricePerHour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPricePerHour());
    }
}
