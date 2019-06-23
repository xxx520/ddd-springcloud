package org.yugh.authclient.repository.auth;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.yugh.authclient.ddd.BaseRepository;
import org.yugh.authclient.entity.auth.QUserRoleEntity;
import org.yugh.authclient.entity.auth.UserRoleEntity;

/**
 * //关联表
 *
 * @author: 余根海
 * @creation: 2019-04-06 10:54
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
public interface UserRoleRepository extends BaseRepository<UserRoleEntity, Long>, QuerydslPredicateExecutor<QUserRoleEntity> {


}
