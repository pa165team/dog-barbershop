package cz.muni.fi.pa165.service;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author Martin Kuchar 433499
 */

public interface BeanMappingService {
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
