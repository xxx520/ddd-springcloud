package org.yugh.authclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yugh.authclient.utils.RedisClient;
import org.yugh.authclient.utils.SnowFlakeUtil;

/**
 * // 测试redis setNx
 *
 * @author: 余根海
 * @creation: 2019-05-24 11:57
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {


    protected static final String STR = "order_";


    private final RedisClient redisClient;

    @Autowired
    protected RedisController(RedisClient redisClient) {
        this.redisClient = redisClient;
    }


    @GetMapping("lockCar")
    public Object createOrderLockCar(String carId) {

        /**
         * 原理是前端传来 车辆ID
         * 创建订单先调车辆接口把订单号写到车辆字段里
         * 然后把carId_ 车辆Id + 生成订单号
         * 创建订单表一条数据
         *
         * 第二个用户传来车辆ID
         * 先判断上一个carId_ 车辆Id setIfAbsent 是否存在和过期，如果存在提示车辆被订了，如果不存在就调用车辆接口
         * 看车辆表订单字段是否也被删除，如果没有，则提示车辆被订。
         *
         * 如果缓存和表里车辆都没有了，就可以继续锁车创建订单
         */
        String orderId = SnowFlakeUtil.nextWaterFlow().toString();

        boolean setCarId = redisClient.setNx("carId_" + carId, orderId, 20L);
        System.out.println(setCarId);


        return setCarId;
    }


}
