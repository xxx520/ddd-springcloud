package org.yugh.authclient.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.yugh.authclient.domain.auth.UserDetail;
import org.yugh.authclient.entity.auth.RoleEntity;
import org.yugh.authclient.entity.auth.UserEntity;
import org.yugh.authclient.repository.auth.RoleRepository;
import org.yugh.authclient.repository.auth.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * //验证用户
 *
 * @author: 余根海
 * @creation: 2019-04-07 19:25
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Component("BaseDetailsService")
public class BaseDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public BaseDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDetail loadUserByUsername(String name) throws UsernameNotFoundException {
        List<UserEntity> userDetails =  userRepository.findUserByname(name);
        if (CollectionUtils.isEmpty(userDetails)) {
            throw new UsernameNotFoundException(String.format("No userDetail found with username '%s'.", name));
        }
        UserDetail userDetail = new UserDetail();
        userDetail.setId(userDetails.get(0).getId());
        userDetail.setPassword(userDetails.get(0).getPassword());
        userDetail.setUsername(userDetails.get(0).getName());
        Optional<RoleEntity> role = roleRepository.findById(1L);
        userDetail.setRole(role.get());
        return userDetail;
    }
}
