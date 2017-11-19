package cz.muni.fi.pa165.dto.servicetype;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Jan Kalfus
 */
public class ServiceTypeCreateDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    private String description;

    @NotNull
    @Min(0)
    private BigDecimal pricePerHour;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        ServiceTypeCreateDTO that = (ServiceTypeCreateDTO) o;
        return Objects.equals(getName(), that.getName()) &&
            Objects.equals(getDescription(), that.getDescription()) &&
            Objects.equals(getPricePerHour(), that.getPricePerHour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getPricePerHour());
    }
}
