package com.satvik.restful_web_services.controller;


import com.satvik.restful_web_services.helloWorld.HelloWorldBean;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    @GetMapping("hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("hello-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World!");
    }
//path variable example
    @GetMapping("hello-bean/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello, %s!", name));
    }



}
