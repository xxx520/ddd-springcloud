package org.yugh.authclient.repository.auth;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.yugh.authclient.ddd.BaseRepository;
import org.yugh.authclient.entity.auth.QRoleEntity;
import org.yugh.authclient.entity.auth.RoleEntity;

/**
 * //角色
 *
 * @author: 余根海
 * @creation: 2019-04-06 11:11
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
public interface RoleRepository extends BaseRepository<RoleEntity, Long>, QuerydslPredicateExecutor<QRoleEntity> {

}
