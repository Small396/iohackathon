package com.kinger.iohackathon.controller;

import com.kinger.iohackathon.common.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@RestController
@RequestMapping("/mock")
@Api(tags = "操作功能")
public class IoHackathonController {
    @GetMapping(value = "/hello/{name}", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "测试全局异常捕获")
    public ResultInfo view(@ApiParam(value = "新增实体", required = true) @PathVariable("name") String name) throws Exception {
        System.out.println("view=================>>>:" + name);
        if ("test".equals(name)) {
            throw new RuntimeException("test");
        }
        if ("dev".equals(name)) {
            throw new Exception("dev");
        }
        return ResultInfo.ok("hello ", name);
    }
}
