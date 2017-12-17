package cz.muni.fi.pa165.mvc.config;
import cz.muni.fi.pa165.utils.Address;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Daniel Mudrik (433655)
 */


public class StringToAddressConverter implements Converter<String, Address> {
    
    public StringToAddressConverter () {
    }

    @Override
    public Address convert (String address) {
        Address newAddress;
        try {
            newAddress = new Address();
            String[] commaSplit = address.split(",");
            String[] spaceSplit;
            if (commaSplit.length == 2) {
                spaceSplit = commaSplit[0].split(" ");
                if (spaceSplit.length == 2) {
                    newAddress.setStreet(spaceSplit[0]);
                    newAddress.setNumber(Integer.parseInt(spaceSplit[1])); //can throw NumberFormatException
                    newAddress.setCity(commaSplit[1]);
                    return newAddress;
                } else {
                    throw new IllegalArgumentException("Street and house no. must be separated by a space.");
                }
            } else {
                throw new IllegalArgumentException("Wrong address format! Correct address format: [Street] [House No.], [City]");
            }
            
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
