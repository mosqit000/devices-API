package com.example.devicesapi.aspect;

import com.example.devicesapi.repository.DeviceRepository;
import com.example.devicesapi.utility.CustomException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ICheckableAspect {


    @Autowired
    DeviceRepository deviceRepository;

    @Around("@annotation(com.example.devicesapi.annotation.ICheckable)")
    public Object checkIfIdExists(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        //System.out.println("argument size: "+ args.length);
        long id = (long)args[0];
        if(deviceRepository.findById(id).isEmpty()){
            throw new CustomException(joinPoint.getSignature().getName()," device not found from aspect");
        }
       return joinPoint.proceed();

    }

    @Around("execution(* com.example.devicesapi.controller.*.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time of " + joinPoint.getSignature().getName() + " : " + (endTime - startTime) + " ms");
        return result;
    }

}
