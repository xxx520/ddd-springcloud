package org.yugh.authclient.feign.impl;

import org.springframework.stereotype.Component;
import org.yugh.authclient.feign.TradePaymentService;

/**
 * @author: YuGenHai
 * @name: TradePaymentServiceImpl
 * @creation: 2018/11/6 14:43
 * @notes: 交易订单
 *
 * 错误熔断
 *
 */
@Deprecated
@Component("tradePaymentFailFallbackImpl")
public class TradePaymentFailFallbackImpl implements TradePaymentService {


    /**
     * @creation 2019-06-24 20:23
     *
     * DDD建模设计不允许Feign出现，这是给eureka->gateway->monitor->config微服务架构做备份
     */




  /**  @Override
    public ResultVo createTradePaymentOrder(TradePaymentCreateOrderVo tradePaymentCreateOrderVo) {
        return ResultUtils.fail(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, "进入错误熔断");
    }

    @Override
    public ResultVo createTradePaymentOrderAndPay(TradePaymentOrderVo tradePaymentOrderVo) {
        return ResultUtils.fail(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, "进入错误熔断");
    }

    @Override
    public ResultVo queryBankCard(Integer bindStatus, Integer tobUserId) {
        return ResultUtils.fail(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, "进入错误熔断");
    }

    @Override
    public ResultVo quickValidate(String agreementId, String tradeNo) {
        return ResultUtils.fail(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, "进入错误熔断");
    }

    @Override
    public ResultVo quickVerify(VerifyVo verifyVo) {
        return ResultUtils.fail(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, "进入错误熔断");
    }

    @Override
    public ResultVo quickConfirm(ConfirmVo confirmVo) {
        return ResultUtils.fail(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, "进入错误熔断");
    }
    **/
}
