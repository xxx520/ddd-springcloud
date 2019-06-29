package org.yugh.authclient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.yugh.authclient.domain.CodeEnum;
import org.yugh.authclient.domain.auth.ResponseUserToken;
import org.yugh.authclient.domain.auth.UserDetail;
import org.yugh.authclient.domain.dto.UserDto;
import org.yugh.authclient.service.IUserService;
import org.yugh.authclient.utils.ResultJson;

import javax.servlet.http.HttpServletRequest;

/**
 * //用户相关
 *
 * @author: 余根海
 * @creation: 2019-04-08 17:38
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@RestController
@Api(description = "用户验证数据源")
@RequestMapping("/api/v1")
public class AuthController {


    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private IUserService userService;


    /**
     * 登录
     * @param userDto
     * @return
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token")
    public ResultJson<ResponseUserToken> login( @RequestBody UserDto userDto) {
        final ResponseUserToken response = userService.login(userDto.getName(), userDto.getPassword());
        return ResultJson.ok(response);
    }


    /**
     * 退出
     * @param request
     * @return
     */
    @GetMapping(value = "/logout")
    @ApiOperation(value = "登出", notes = "退出登陆")
    //@ApiImplicitParams({@ApiImplicitParam(name = "授权", value = "授权 token", required = true, dataType = "string", paramType = "header")})
    public ResultJson logout(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(CodeEnum.UNAUTHORIZED);
        }
        userService.logout(token);
        return ResultJson.ok();
    }


    /**
     * 根据token获取用户信息
     * @param request
     * @return
     */
    @GetMapping(value = "/user")
    @ApiOperation(value = "根据token获取用户信息", notes = "根据token获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "授权", value = "授权 token", required = true, dataType = "string", paramType = "header")})
    public ResultJson getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(CodeEnum.UNAUTHORIZED);
        }
        UserDetail userDetail = userService.getUserByToken(token);
        return ResultJson.ok(userDetail);
    }


    /**
     * 用户注册
     * @param userDto
     * @return
     */
    @PostMapping(value = "/sign")
    @ApiOperation(value = "用户注册")
    public ResultJson sign(@RequestBody UserDto userDto) {
        if (StringUtils.isAnyBlank(userDto.getName(), userDto.getPassword())) {
            return ResultJson.failure(CodeEnum.BAD_REQUEST);
        }
        return ResultJson.ok(userService.register(userDto));
    }


    @GetMapping(value = "refresh")
    @ApiOperation(value = "刷新token")
    public ResultJson refreshAndGetAuthenticationToken(
            HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        ResponseUserToken response = userService.refresh(token);
        if (response == null) {
            return ResultJson.failure(CodeEnum.BAD_REQUEST, "token无效");
        } else {
            return ResultJson.ok(response);
        }
    }


}
