package org.example.control;

import jakarta.servlet.http.HttpSession;
import org.example.pojo.OrderBack;
import org.example.pojo.OrderFloower;
import org.example.pojo.User;
import org.example.pojo.vo.FVo;
import org.example.pojo.vo.UserVo;
import org.example.service.FFloowersService;
import org.example.service.OrderBackService;
import org.example.service.OrderFloowersService;
import org.example.service.UserService;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    UserService userservice;
    @PostMapping("/login")
    public Result<?> loginmanager(@RequestBody UserVo user, HttpSession session) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        User o = userservice.Userlogin(user.getUsername(), user.getPassword());
        if (o != null) {
            if(o.getIdentify() != 1) return Result.error("这可能是其他人员账号");
            System.out.println(o);
            //存入session
            session.setAttribute("Identity", "manager");
            session.setAttribute("User", o);
            return Result.ok(o);
        } else {
            return Result.error("账号吗或密码错误");
        }
    }
    @Autowired
    FFloowersService flservice;
    @PutMapping("/addNewFloower")
    public Result<?> addNewFloower(@RequestBody FVo fVo) {
        int i = flservice.addNewFloower(fVo);
        if (i == 1) {
            return Result.ok();
        } else {
            return Result.error("添加失败");
        }
    }
    @PostMapping("/updateFloower")
    public Result<?> updateFloower(@RequestBody FVo fVo) {
        System.out.println(fVo);
        int i = flservice.updateFloower(fVo);
        if (i == 1) {
            return Result.ok();
        } else {
            return Result.error("修改失败");
        }
    }
    @PostMapping("/removeFloower")
    public Result<?> removeFloower(@RequestBody FVo fVo) {
        System.out.println(fVo);
        int i = flservice.removeFloower(fVo);
        if (i == 1) {
            return Result.ok();
        } else {
            return Result.error("删除失败");
        }
    }

}
