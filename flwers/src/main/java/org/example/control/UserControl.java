package org.example.control;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import jakarta.servlet.http.HttpSession;
import org.example.pojo.FFloowers;
import org.example.pojo.OrderBack;
import org.example.pojo.OrderFloower;
import org.example.pojo.User;
import org.example.pojo.vo.FVo;
import org.example.pojo.vo.PortalVo;
import org.example.pojo.vo.UserVo;
import org.example.service.*;
import org.example.utils.RedisCache;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/user")
public class UserControl {

@Autowired
private UserService userservice;
@Autowired
private SSignService signService;
@Autowired
private     FFloowersService flservice;
@Autowired
private OrderFloowersService orderFloowersService;
@Autowired
private OrderBackService backService;

@Autowired
private RedisCache  redisUtil;
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/user/login")
    public Result<?> login(@RequestBody UserVo user, HttpSession session) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        // 获取redis中的验证码

        String redisCode = redisUtil.getCacheObject(user.getKey());
        if(redisCode == null){
            return Result.error("请生成一个验证码");
        }
        // 判断验证
        if (user.getVercode() == null | !redisCode.equals(user.getVercode().trim().toLowerCase())) {
            return Result.error("验证码不正确");
        }
        User o = userservice.Userlogin(user.getUsername(), user.getPassword());
        if (o != null) {
            if(o.getIdentify() != 0) return Result.error("这可能是管理员账号");
            System.out.println(o.getName());
            //存入session
            session.setAttribute("Identity", "user");
            session.setAttribute("User", o);
            return Result.ok(o);
        } else {
            return Result.error("账号吗或密码错误");
        }
    }
    @PostMapping("/manager/login")
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
    @GetMapping("/main/signOut")
    public Result<?> layout(HttpSession session) {
        session.removeAttribute("User");
        session.removeAttribute("Identity");
        return Result.ok();
    }
    @PutMapping("/user/update")
    public Result<?> update(@RequestBody User user) {
        int i = userservice.updateNewStudent(user);
        if (i == 1) {
            return Result.ok();
        } else {
            return Result.error("更新失败");
        }
    }
    @PostMapping("/user/signup")
    public  Result<?> signup(@RequestBody UserVo user){
        System.out.println(user.getUsername());
        System.out.println(user.getName());
        int i = signService.signup(user.getUsername(),user.getName());
        if (i == 1) {
            return Result.ok(true);
        } else {
            return Result.error("签到失败");
        }
    }
    @PostMapping("/findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result = userservice.findALlUser(portalVo);
        return result;
    }
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

    @PostMapping("/user/findAllFloowers")
    public  Result findAllFloowers(@RequestBody PortalVo portalVo){
        Result result = flservice.findAllFloowers(portalVo);
        return result;

    }
    @PutMapping("/user/addNewFloower")
    public Result<?> addNewFloower(@RequestBody FVo fVo) {
        int i = flservice.addNewFloower(fVo);
        if (i == 1) {
            return Result.ok();
        } else {
            return Result.error("添加失败");
        }
    }
    @PostMapping("/user/updateFloower")
    public Result<?> updateFloower(@RequestBody FVo fVo) {
        System.out.println(fVo);
        int i = flservice.updateFloower(fVo);
        if (i == 1) {
            return Result.ok();
        } else {
            return Result.error("修改失败");
        }
    }
    @PostMapping("/user/removeFloower")
    public Result<?> removeFloower(@RequestBody FVo fVo) {
        System.out.println(fVo);
        int i = flservice.removeFloower(fVo);
        if (i == 1) {
            return Result.ok();
        } else {
            return Result.error("删除失败");
        }
    }
    @PostMapping("/regist")
    public Result<?> removeFloower(@RequestBody User user,HttpSession session) {
        System.out.println(user);
        int i = userservice.regist(user);
        if (i == 1) {
            User o = userservice.Userlogin(user.getUsername(), user.getPassword());
            System.out.println(o);
            //存入session
            session.setAttribute("Identity", "user");
            session.setAttribute("User", o);
            return Result.ok(o);
        } else {
            return Result.error("注册失败");
        }
    }
    @PostMapping("/buyFloower")
    public Result<?> buyFloower(@RequestBody OrderFloower order) {
        int i = orderFloowersService.buyFloower(order);
        if (i == 1) {
            //库存-1
            int f_id = order.getF_id();
            int j = flservice.updateFnum(f_id);
              if(j == 1)
            return Result.ok();
              else return Result.error("购买失败");
        } else {
            return Result.error("购买失败");
        }
    }
    @PostMapping("/findOrderPage")
    public Result<?> findOrderPage(@RequestBody PortalVo portalVo) {
        Result result = orderFloowersService.findALlOrder(portalVo);
        return result;
    }

    @PostMapping("/updateOrder")
    public Result<?> updateOrder(@RequestBody OrderFloower order) {

        System.out.println(order);
        int i = orderFloowersService.updateOrder(order);
        if (i == 1) {
            return Result.ok();
        } else {
            return Result.error("修改失败");
        }
    }
    @PostMapping("/removeOrder")
    public Result<?> removeOrder(@RequestBody OrderBack order) {

        System.out.println(order);
        int i = orderFloowersService.removeOrder(order);
        if (i == 1) {
            System.out.println("删除成功");
            int j = backService.removeOrder(order);
            if(j == 1){
                return Result.ok();
            }else return Result.error("删除失败");

        } else {
            return Result.error("删除失败");
        }
    }

}
