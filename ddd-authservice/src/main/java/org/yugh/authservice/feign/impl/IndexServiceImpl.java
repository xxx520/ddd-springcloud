package org.yugh.authservice.feign.impl;

import org.springframework.stereotype.Component;
import org.yugh.authservice.feign.IndexService;

/**
 * //
 *
 * @author: 余根海
 * @creation: 2019-06-24 19:15
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Component("indexService")
public class IndexServiceImpl implements IndexService {

    @Override
    public Object callMsg() {
        return "进入业务熔断";
    }

}
