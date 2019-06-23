package org.yugh.authclient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yugh.authclient.domain.auth.ResponseUserToken;
import org.yugh.authclient.utils.ResultJson;

/**
 * //
 *
 * @author: 余根海
 * @creation: 2019-04-09 17:27
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@RestController
@Api(description = "商品数据源")
@RequestMapping("/goods/v1")
public class GoodsController {




    @PostMapping(value = "/queryGoods")
    @ApiOperation(value = "查询商品", notes = "查询商品")
    public ResultJson<ResponseUserToken> queryGoods() {
        //final ResponseUserToken response = userService.login(userDto.getName(), userDto.getPassword());


        return ResultJson.ok("");
    }

}
