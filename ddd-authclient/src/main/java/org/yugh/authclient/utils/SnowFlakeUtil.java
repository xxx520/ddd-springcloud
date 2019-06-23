package org.yugh.authclient.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * //雪花算法生成分布式ID
 *
 * @author: 余根海
 * @creation: 2019-05-26 19:20
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Slf4j
public class SnowFlakeUtil {


    static final Snowflake SNOWFLAKE = IdUtil.createSnowflake(1, 1);

    /**
     * 偏移时间
     * @return
     */
    public static Long nextWaterFlow() {
        return SNOWFLAKE.nextId();
    }


    public static void main(String[] args) {

        nextWaterFlow();
    }
}
