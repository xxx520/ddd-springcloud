package org.yugh.authclient.entity.goods;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Builder;
import lombok.Data;
import org.yugh.authclient.ddd.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * //
 *
 * @author: 余根海
 * @creation: 2019-04-09 18:25
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Data
@Table(name = "goods_dict")
@Entity
@Builder
@QueryEntity
public class GoodsEntity extends AbstractEntity {

    //private String
    //FIXME 代码截止到此
}
