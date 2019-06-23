package org.yugh.authclient.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * //调度任务
 *
 * @author: 余根海
 * @creation: 2019-06-20 14:37
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Component
@Slf4j
public class ScheduledTask {

    private static AtomicInteger integer = new AtomicInteger();

    /**
     * 调度任务
     * fixedRate = 毫秒
     */
    @Scheduled(fixedRate = 3000)
    private void schedule(){
        log.info("=============> running task count:", integer.incrementAndGet());
    }

    public static void main(String[] args) {

        System.out.println(integer.incrementAndGet());
    }
}
