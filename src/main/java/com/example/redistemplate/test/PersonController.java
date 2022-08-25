package com.example.redistemplate.test;

import com.example.redistemplate.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Autowired
    private Person person;

    @RequestMapping("/person")
    public Person testPersonYml() {
        return person;
    }

}
