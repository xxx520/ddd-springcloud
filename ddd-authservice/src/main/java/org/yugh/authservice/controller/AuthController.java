package org.yugh.authservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yugh.authservice.feign.IndexService;

/**
 * //
 *
 * @author: 余根海
 * @creation: 2019-06-24 14:53
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@RestController
@Slf4j
@RequestMapping("test")
public class AuthController {

    private final IndexService indexService;

    @Autowired
    public AuthController(IndexService indexService){
        this.indexService = indexService;
    }


    @GetMapping("index1")
    public Object test(){

        //Object obj = indexService.callMsg();
        log.info("===========> testController= {}", "okkk");

        return "hit";
    }
}
