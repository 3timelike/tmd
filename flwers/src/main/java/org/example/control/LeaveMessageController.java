package org.example.control;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.example.pojo.Message;
import org.example.pojo.vo.MessageVo;
import org.example.service.LeaveMessageService;
import org.example.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: MessageController
 * Package: org.example.control
 * Description:
 *
 * @Author
 * @Create 2024/6/29 9:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/leaveMessage")
public class LeaveMessageController {
    @Resource
    private LeaveMessageService leaveMessageService;


    @GetMapping("{currentPage}/{pageSize}")
    public Result getAllByPage(@PathVariable int currentPage, @PathVariable int pageSize){
//        IPage<MessageVo> page = leaveMessageService.getPage(currentPage, pageSize);
//        if (currentPage>page.getPages()){
//            page=leaveMessageService.getPage((int) page.getPages(), pageSize);
//        }
        List<MessageVo> messages = leaveMessageService.getAll();
        return Result.ok(messages);
    }

    @PostMapping
    public Result insertMessage(@RequestBody Message message){
        int i = leaveMessageService.insertMessage(message);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }
    @DeleteMapping("{id}")
    public Result deleteMessage(@PathVariable Integer id){
       int i = leaveMessageService.deleteMessage(id);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }
}
