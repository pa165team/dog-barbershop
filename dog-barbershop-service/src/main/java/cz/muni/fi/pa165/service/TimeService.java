package cz.muni.fi.pa165.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Simple service that should be used instead of directly creating new Date(), very useful for mocking
 */

@Service
public interface TimeService {
    Date getCurrentTime();
}
