package com.featurelogs.models;

import com.featurelogs.annotations.WithinWorkableAge;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Employee {
    Long id;

    @NotNull(message = "cannot be blank")
    String name;

    @WithinWorkableAge(min = 35,max = 54,message = "should be within 35 & 54")
    Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
