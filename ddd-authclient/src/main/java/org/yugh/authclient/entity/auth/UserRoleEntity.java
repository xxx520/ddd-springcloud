package org.yugh.authclient.entity.auth;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Builder;
import lombok.Data;
import org.yugh.authclient.ddd.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * // 用户角色 对应表
 *
 * @author: 余根海
 * @creation: 2019-04-06 10:47
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Table(name = "sys_user_role")
@Entity
@QueryEntity
@Data
@Builder
public class UserRoleEntity extends AbstractEntity {


    @Column(name = "user_id")
    private Long userId;


    @Column(name = "role_id")
    private Long roleId;

}
