package org.yugh.authservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.yugh.authservice.feign.impl.IndexServiceImpl;

/**
 * //被动调用
 *
 * @author: 余根海
 * @creation: 2019-06-24 14:58
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@FeignClient(value = "auth-client", fallback = IndexServiceImpl.class)
public interface IndexService {

    @GetMapping("/log/testUser")
    Object callMsg();


}
