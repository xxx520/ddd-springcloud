package org.yugh.authclient.service;

import org.yugh.authclient.domain.auth.ResponseUserToken;
import org.yugh.authclient.domain.auth.UserDetail;
import org.yugh.authclient.domain.dto.UserDto;
import org.yugh.authclient.utils.ResultJson;

/**
 * //用户鉴权业务层
 *
 * @author: 余根海
 * @creation: 2019-04-08 16:29
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
public interface IUserService {

    /**
     * 用户注册
     *
     * @param userDto
     * @return
     */
    ResultJson<String> register(UserDto userDto);


    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    ResponseUserToken login(String username, String password);


    /**
     * 登出
     *
     * @param token
     */
    void logout(String token);


    /**
     * 根据Token获取用户信息
     *
     * @param token
     * @return
     */
    UserDetail getUserByToken(String token);


    /**
     * 刷新Token
     *
     * @param oldToken
     * @return
     */
    ResponseUserToken refresh(String oldToken);
}
