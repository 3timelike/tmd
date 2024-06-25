package org.example.control;

import jakarta.servlet.http.HttpSession;
import org.example.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainContorller {



    @GetMapping("/signOut")
    public Result<?> layout(HttpSession session) {
        session.removeAttribute("User");
        session.removeAttribute("Identity");
        return Result.ok();
    }


}
