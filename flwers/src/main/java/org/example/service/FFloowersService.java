package org.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.FFloowers;
import org.example.pojo.vo.FVo;
import org.example.pojo.vo.PortalVo;
import org.example.utils.Result;

/**
* @author zhang
* @description 针对表【f_floowers】的数据库操作Service
* @createDate 2024-05-23 19:27:46
*/
public interface FFloowersService {
     int addNewFloower(FVo fVo);

     Result findAllFloowers(PortalVo portalVo);

     int updateFloower(FVo fVo);

     int removeFloower(FVo fVo);

     int updateFnum(int f_id);

    int updateNewFloower(FFloowers  fVo);

     Result findFloowersbyid(int fId);
}
