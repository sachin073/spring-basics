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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @CustomWired
    private DummyService service;

    @PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee) {
        //processing....
        employeeService.saveDetails(employee);
        service.print(employee);

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @Autowired
    private EmployeeService employeeService;

    @ControllerAdvice(annotations = {RestController.class})
    public class UncaughtExceptionsControllerAdvice {
        @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
        public ResponseEntity handleBindingErrors(MethodArgumentNotValidException e) {
            return new ResponseEntity<>(e.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)), HttpStatus.BAD_REQUEST);
        }
    }
}
