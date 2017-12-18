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
            String[] streetSplit;
            if (commaSplit.length == 2) {
                streetSplit = commaSplit[0].split(" ");
                if (streetSplit.length >= 2) {
                    newAddress.setNumber(Integer.parseInt(streetSplit[streetSplit.length-1])); //can throw NumberFormatException
                    StringBuilder streetName = new StringBuilder(streetSplit[0]);
                    for (int i = 1; i < streetSplit.length-1; i++) {
                        streetName.append(" ").append(streetSplit[i]);
                    }
                    newAddress.setStreet(streetName.toString());
                    newAddress.setCity(commaSplit[1]);
                    return newAddress;
                } else {
                    //throw new IllegalArgumentException("Street and house no. must be separated by a space.");
                    return null;
                }
            } else {
                //throw new IllegalArgumentException("Wrong address format! Correct address format: [Street] [House No.], [City]");
                return null;
            }
            
        } catch (Exception e) {
            return null;
            //throw new IllegalArgumentException(e);
        }
    }
}
