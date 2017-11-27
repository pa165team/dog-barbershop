package cz.muni.fi.pa165.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Jan Kalfus
 */
@Service
public class TimeServiceImpl implements TimeService {

    @Override
    public Date getCurrentTime() {
        return new Date();
    }

}
