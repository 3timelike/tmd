package org.example.service;

import org.example.pojo.User;
import org.example.pojo.vo.PortalVo;
import org.example.pojo.vo.UserVo;
import org.example.utils.Result;

public interface UserService {

    User Userlogin(String username, String password);

    int updateNewStudent(User user);

    Result findALlUser(PortalVo portalVo);

    int regist(User user);
}
