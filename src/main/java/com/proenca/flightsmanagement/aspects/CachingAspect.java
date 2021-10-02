package com.proenca.flightsmanagement.aspects;

import com.proenca.flightsmanagement.dao.PassengerDao;
import com.proenca.flightsmanagement.dao.PassengerDaoImpl;
import com.proenca.flightsmanagement.domain.Passenger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Order(3)
@Aspect
public class CachingAspect {

    @Around("execution(* com.proenca.flightsmanagement.dao.PassengerDaoImpl.getPassenger(..))")
    public void cachePassenger(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Object[] methodArgs = thisJoinPoint.getArgs();
        Passenger result = (Passenger) thisJoinPoint.proceed();

        int id = (Integer) methodArgs[0];
        if (!PassengerDaoImpl.getPassengerMap().containsKey(id)) {
            PassengerDaoImpl.getPassengerMap().put(id, result);
        }
    }
}
