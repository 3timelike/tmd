package org.example.control;


import org.example.pojo.OrderBack;
import org.example.pojo.OrderFloower;
import org.example.pojo.vo.PortalVo;
import org.example.service.OrderBackService;
import org.example.service.OrderFloowersService;
import org.example.service.SSignService;
import org.example.service.UserService;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    @Autowired
    UserService userservice;

    @PostMapping("/findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result = userservice.findALlUser(portalVo);
        return result;
    }
    @Autowired
    SSignService signService;
    @PostMapping("/findSignUpPage")
    public Result findSignUpPage(@RequestBody PortalVo portalVo){
        Result result = signService.findSignUpPage(portalVo);
        return result;
    }
    @PostMapping("/findNoSignUpPage")
    public  Result findNosignUpPage(@RequestBody PortalVo portalVo){
        Result result = signService.findNoSignUpPage(portalVo);
        return result;
    }

}
