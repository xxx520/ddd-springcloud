package org.yugh.authclient.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yugh.authclient.domain.CodeEnum;
import org.yugh.authclient.domain.auth.ResponseUserToken;
import org.yugh.authclient.domain.auth.UserDetail;
import org.yugh.authclient.domain.dto.UserDto;
import org.yugh.authclient.entity.auth.RoleEntity;
import org.yugh.authclient.entity.auth.UserEntity;
import org.yugh.authclient.entity.auth.UserRoleEntity;
import org.yugh.authclient.exception.CustomerException;
import org.yugh.authclient.repository.auth.RoleRepository;
import org.yugh.authclient.repository.auth.UserRepository;
import org.yugh.authclient.repository.auth.UserRoleRepository;
import org.yugh.authclient.service.IUserService;
import org.yugh.authclient.utils.JwtUtils;
import org.yugh.authclient.utils.RedisClient;
import org.yugh.authclient.utils.ResultJson;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * //用户校验
 *
 * @author: 余根海
 * @creation: 2019-04-08 16:40
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements IUserService {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 失效时间 40S
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 权限
     */
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 自定义
     */
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;


    @Autowired
    public UserServiceImpl(@Qualifier("BaseDetailsService") UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public ResultJson<String> register(UserDto userDto) {
        List<UserEntity> users = userRepository.findUserByname(userDto.getName());
        if (users.size() > 0) {
            throw new CustomerException(ResultJson.failure(CodeEnum.USER_EXIST));
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String newPassword = encoder.encode(userDto.getPassword());
        UserEntity user = UserEntity.builder().build();
        user.setName(userDto.getName());
        user.setPassword(newPassword);
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
        Optional<RoleEntity> roles = roleRepository.findById(userDto.getRoleId());
        UserRoleEntity userRole = UserRoleEntity.builder().roleId(roles.get().getId()).userId(user.getId()).build();
        userRoleRepository.save(userRole);
        return ResultJson.ok(user.toString());

    }

    @Override
    public ResponseUserToken login(String username, String password) {
        final Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        final String token = jwtUtils.generateAccessToken(userDetail);
        jwtUtils.putToken(username, token);
        redisClient.set(username, token, expiration);
        log.info("====> 当前token,{}", token);
        return new ResponseUserToken(tokenHead + token, userDetail);
    }

    @Override
    public void logout(String token) {
        token = token.substring(tokenHead.length());
        String userName = jwtUtils.getUsernameFromToken(token);
        jwtUtils.deleteToken(userName);
    }

    @Override
    public UserDetail getUserByToken(String token) {
        token = token.substring(tokenHead.length());
        return jwtUtils.getUserFromToken(token);
    }

    @Override
    public ResponseUserToken refresh(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        String username = jwtUtils.getUsernameFromToken(token);
        UserDetail userDetail = (UserDetail) userDetailsService.loadUserByUsername(username);
        if (jwtUtils.canTokenBeRefreshed(token, userDetail.getLastPasswordResetDate())) {
            token = jwtUtils.refreshToken(token);
            return new ResponseUserToken(token, userDetail);
        }
        return null;
    }


    /**
     * 该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码
     * 如果正确，则存储该用户名密码到“security 的 context中”
     *
     * @param username
     * @param password
     * @return
     */
    private Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new CustomerException(ResultJson.failure(CodeEnum.LOGIN_ERROR, e.getMessage()));
        }
    }
}
