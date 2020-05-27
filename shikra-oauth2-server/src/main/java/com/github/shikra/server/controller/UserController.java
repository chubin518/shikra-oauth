package com.github.shikra.server.controller;

import com.github.shikra.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/info")
    public ResponseResult info(Authentication authentication) {
        return ResponseResult.success();
    }
}
