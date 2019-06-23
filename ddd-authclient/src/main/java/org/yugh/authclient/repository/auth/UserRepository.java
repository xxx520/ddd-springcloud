package org.yugh.authclient.repository.auth;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.yugh.authclient.ddd.BaseRepository;
import org.yugh.authclient.entity.auth.QUserEntity;
import org.yugh.authclient.entity.auth.UserEntity;

import java.util.List;

/**
 * // 用户
 *
 * @author: 余根海
 * @creation: 2019-04-05 21:45
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
public interface UserRepository extends BaseRepository<UserEntity, Long>, QuerydslPredicateExecutor<QUserEntity> {

    List<UserEntity> findUserByname(String name);


}
