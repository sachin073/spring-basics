package com.featurelogs.controllers;

import com.featurelogs.annotations.CustomWired;
import com.featurelogs.models.Employee;
import com.featurelogs.service.DummyService;
import com.featurelogs.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService ;

    @CustomWired
    private DummyService service;

    @PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee) {
        //processing....
        employeeService.saveDetails(employee);
        service.print(employee);


        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }


    @ControllerAdvice(annotations = {RestController.class})
    public class UncaughtExceptionsControllerAdvice {
        @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
        public ResponseEntity handleBindingErrors(MethodArgumentNotValidException e) {
            System.out.println("asc");
            Map<String, String> errors = new HashMap<>();
            e.getBindingResult().getAllErrors().stream().forEach(error -> error.getDefaultMessage());
            //return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            List<String> ssx = e.getBindingResult().getAllErrors().stream().map(ex -> ex.getDefaultMessage()).collect(Collectors.toList());
            return new ResponseEntity<>("not valid due to validation error: " + e.getBindingResult().getAllErrors().stream().map(ex -> ex.getDefaultMessage()).collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
}
