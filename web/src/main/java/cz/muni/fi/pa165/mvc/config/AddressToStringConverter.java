package cz.muni.fi.pa165.mvc.config;
import cz.muni.fi.pa165.utils.Address;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Daniel Mudrik (433655)
 */


public class AddressToStringConverter implements Converter<Address, String> {
    
    public AddressToStringConverter () {
    }

    @Override
    public String convert (Address address) {
        return address.getStreet() + " " + address.getNumber() + ", " + address.getCity();
    }
}
