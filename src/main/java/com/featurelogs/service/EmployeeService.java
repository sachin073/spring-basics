package com.featurelogs.service;

import com.featurelogs.annotations.MethodProfiler;
import com.featurelogs.models.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    public EmployeeService() {
    }

    @MethodProfiler
    public void saveDetails(Employee employee) {

        try {
            Thread.sleep(2000);
            System.out.println("saved!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}