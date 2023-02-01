package com.example.practice.controller;

import com.example.practice.VO.TestVO;
import com.example.practice.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public String getLoginPage(Model model,
                               @RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "exception", required = false) String exception) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/member/login";
    }

    @GetMapping("/api/hello")
    @ResponseBody
    public String testApi() throws Exception {
        log.debug("test log");
        String result = "";
        TestVO testVO = new TestVO();
        testVO.setName("홍길동");
        testVO.setAge(12);
        result = memberService.testMethod(testVO);

        return result;
    }
}