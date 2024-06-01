package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.SSign;
import org.example.pojo.vo.PortalVo;
import org.example.utils.Result;

import java.util.List;

/**
* @author zhang
* @description 针对表【s_sign】的数据库操作Service
* @createDate 2024-05-21 13:29:20
*/
public interface SSignService  {
    int signup(String username,String name);

    Result findSignUpPage(PortalVo portalVo);

    Result findNoSignUpPage(PortalVo portalVo);
}
