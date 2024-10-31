package com.wondrous.board.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.annotation.Target;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainMethod() {
        return "main";
    }
}
