package com.desmond.controller;

import com.desmond.common.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    private Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping("/admin")
    @PreAuthorize("@auth.hasAuthority('system:admin')")
    public ResponseResult<String> testAdmin(){
        Authentication auth = getAuth();
        return new ResponseResult<>(HttpStatus.OK.value(), "欢迎 " + auth.getName() + ", 您的权限是 " + auth.getAuthorities());
    }

    @RequestMapping("/rider")
    @PreAuthorize("hasAnyAuthority('system:rider', 'system:admin')")
    public ResponseResult<String> testRider(){
        Authentication auth = getAuth();
        return new ResponseResult<>(HttpStatus.OK.value(), "欢迎 " + auth.getName() + ", 您的权限是 " + auth.getAuthorities());
    }

    @RequestMapping("/merchant")
    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    public ResponseResult<String> testMerchant(){
        Authentication auth = getAuth();
        return new ResponseResult<>(HttpStatus.OK.value(), "欢迎 " + auth.getName() + ", 您的权限是 " + auth.getAuthorities());
    }

    @RequestMapping("/user")
    public ResponseResult<String> testUser(){
        Authentication auth = getAuth();
        return new ResponseResult<>(HttpStatus.OK.value(), "欢迎 " + auth.getName() + ", 您的权限是 " + auth.getAuthorities());
    }
}
