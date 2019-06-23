package org.yugh.authclient.entity.auth;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Builder;
import lombok.Data;
import org.yugh.authclient.ddd.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * //角色表  | 用户 和 管理员
 *
 * @author: 余根海
 * @creation: 2019-04-05 22:14
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Data
@Table(name = "sys_role")
@Entity
@Builder
@QueryEntity
public class RoleEntity extends AbstractEntity {


    @Column(name = "name")
    private String name;

    @Column(name = "name_zh")
    private String nameZh;


    public RoleEntity(){

    }

    public RoleEntity(String name, String nameZh){
        this.name = name;
        this.nameZh = nameZh;
    }

    public RoleEntity(Long id){
        super();
        this.setId(id);
    }

}
