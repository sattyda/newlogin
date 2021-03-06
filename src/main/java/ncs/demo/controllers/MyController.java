package ncs.demo.controllers;

import ncs.demo.utilities.MyClass;
import ncs.demo.utilities.TestClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    TestClass testClass;

    @RequestMapping( method = RequestMethod.GET , value = "/")
    public String index(){

        testClass.increment();
        return "seq -"+ testClass.seq;
    }
}
