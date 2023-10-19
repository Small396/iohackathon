package com.kinger.iohackathon.controller;

import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping("/mock")
public class IoHackathonController {
    @GetMapping(value ="/hello/{name}")
    public String view(@PathVariable("name") String name) {
        return "hello world" + name;
    }
}
