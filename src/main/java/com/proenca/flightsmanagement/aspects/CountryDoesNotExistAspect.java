package com.proenca.flightsmanagement.aspects;

import com.proenca.flightsmanagement.exceptions.CountryDoesNotExistsException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.util.logging.Logger;

@Aspect
public class CountryDoesNotExistAspect {
    private Logger logger = Logger.getLogger(CountryDoesNotExistAspect.class.getName());

    @AfterThrowing(
            pointcut = "execution(* com.proenca.flightsmanagement.dao.PassengerDaoImpl.insertPassenger(..))",
            throwing = "ex")
    public void log(CountryDoesNotExistsException ex) {
        logger.severe("Attempt to insert a passenger with an unexistent country: " + ex.getCountryCode());
    }
}
