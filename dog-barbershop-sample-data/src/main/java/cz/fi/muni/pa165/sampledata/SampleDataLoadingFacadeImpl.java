package cz.fi.muni.pa165.sampledata;

import cz.muni.fi.pa165.entity.ServiceType;
import cz.muni.fi.pa165.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author Jan Kalfus
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Override
    public void loadData() throws IOException {
        ServiceType serviceType = new ServiceType("Cutting - basic", "Basic cutting for any breed of dog", new BigDecimal("100"));
        serviceTypeService.create(serviceType);
        // TODO: !!!!!!!!!!!!!!!!!!!!!!!!!
        // TODO: do something here!!!
        // TODO: !!!!!!!!!!!!!!!!!!!!!!!!!
    }
}
