/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        try {
            Address newAddress = new Address();
            String[] commaSplit = address.split(",");
            String[] spaceSplit;
            if (commaSplit.length == 2) {
                spaceSplit = commaSplit[0].split(" ");
                if (spaceSplit.length == 2) {
                    newAddress.setStreet(spaceSplit[0]);
                    newAddress.setNumber(Integer.parseInt(spaceSplit[1])); //can throw NumberFormatException
                    newAddress.setCity(commaSplit[1]);
                    
                }
            }
            return newAddress;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
