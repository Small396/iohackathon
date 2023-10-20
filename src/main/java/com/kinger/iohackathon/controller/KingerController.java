package com.kinger.iohackathon.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kinger")
public class KingerController {
    @RequestMapping(value = "/kinger", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public String getUrl() {
        return "/tinger/kinger/";
    }
}
