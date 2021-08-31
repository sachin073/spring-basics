package com.featurelogs.service;

import org.springframework.stereotype.Service;

@Service
public class DummyService {

    public DummyService(){

    }

    public void print(Object object){
        System.out.println("Printed by DummyService : "+object);
    }
}
