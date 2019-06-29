package org.yugh.authclient.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yugh.authclient.constants.Constant;
import org.yugh.authclient.feign.TradePaymentService;

import javax.annotation.Resource;

/**
 * @author: YuGenHai
 * @name: TradePaymentServiceImpl
 * @creation: 2018/11/6 14:43
 * @notes: 交易订单断路工厂
 *
 * 断路工厂
 *
 */
@Slf4j
@Component
@Deprecated
public class TradePaymentFallbackFactory implements FallbackFactory<TradePaymentService> {


    /**
     * @creation 2019-06-24 20:23
     *
     * DDD建模设计不允许Feign出现，这是给eureka->gateway->monitor->config微服务架构做备份
     */



    @Resource(name = "tradePaymentTimeOutFallbackImpl")
    private TradePaymentService tradePaymentTimeOutFallbackService;
    @Resource(name = "tradePaymentFailFallbackImpl")
    private TradePaymentService tradePaymentFailFallService;


    @Override
    public TradePaymentService create(Throwable throwable) {
        log.error("Feign TradePaymentService 进入熔断工厂错误 = {}", throwable.getMessage());
        if(null != throwable.getMessage() && throwable.getMessage().contains(Constant.TIMEOUT)){
            return tradePaymentTimeOutFallbackService;
        }else {
            return tradePaymentFailFallService;
        }
    }
}
