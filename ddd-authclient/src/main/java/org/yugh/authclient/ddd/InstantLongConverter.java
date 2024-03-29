package org.yugh.authclient.ddd;

import javax.persistence.AttributeConverter;
import java.time.Instant;

/**
 * //类型转换，例如枚举类型，在存储到数据库时或者在数据库取出来时，不用手动转换
 * 必须实现接口AttributeConverter<X,Y>
 *
 *     ***
 *
 * @author: 余根海
 * @creation: 2019-04-05 22:10
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
public class InstantLongConverter implements AttributeConverter<Instant, Long> {


    @Override
    public Long convertToDatabaseColumn(Instant instant) {
        return instant == null ? null : instant.toEpochMilli();
    }

    @Override
    public Instant convertToEntityAttribute(Long aLong) {
        return aLong == null ? null : Instant.ofEpochMilli(aLong);
    }
}
