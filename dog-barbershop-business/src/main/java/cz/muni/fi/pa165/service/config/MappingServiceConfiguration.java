package cz.muni.fi.pa165.service.config;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Martin Kuchar 433499
 */

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165")
public class MappingServiceConfiguration {
    @Bean
    public Mapper getMapper(){
        return new DozerBeanMapper();
    }
}
