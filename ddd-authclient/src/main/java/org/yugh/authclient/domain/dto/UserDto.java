package org.yugh.authclient.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * //
 *
 * @author: 余根海
 * @creation: 2019-04-06 09:29
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Data
public class UserDto implements Serializable {


    private static final long serialVersionUID = 1585827095825709391L;

    @ApiModelProperty(value = "用户名", required = true)
    @Size(min = 3, max = 20)
    private String name;

    //@Phone
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;


    @ApiModelProperty(value = "密码", required = true)
    @Size(min = 5, max = 20)
    private String password;

    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;


}
