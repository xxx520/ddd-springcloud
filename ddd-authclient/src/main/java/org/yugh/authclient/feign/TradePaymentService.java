package org.yugh.authclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.yugh.authclient.fallback.TradePaymentFallbackFactory;

/**
 * @author: YuGenHai
 * @name: TradePaymentService
 * @creation: 2018/11/6 14:39
 * @notes: 支付交易
 */
@Deprecated
@FeignClient(name = "trade-payment", fallbackFactory = TradePaymentFallbackFactory.class)
public interface TradePaymentService {


    /**
     * @creation 2019-06-24 20:23
     *
     * DDD建模设计不允许Feign出现，这是给eureka->gateway->monitor->config微服务架构做准备
     */


    /**
     * 创建交易单
     * @param tradePaymentCreateOrderVo
     * @return
     */
    //@PostMapping("/api/trade/payment/v1/order/create")
    //ResultVo createTradePaymentOrder(TradePaymentCreateOrderVo tradePaymentCreateOrderVo);

    /**
     * 支付订单
     * @param tradePaymentOrderVo
     * @return
     */
    //@PostMapping("/api/trade/payment/v1/pay/appPayOrder")
    //ResultVo createTradePaymentOrderAndPay(TradePaymentOrderVo tradePaymentOrderVo);

    /**
     * 商户绑卡查询
     * @param bindStatus
     * @param tobUserId
     * @return
     */
    //@GetMapping("/api/trade/payment/v1/query/bankCard")
    //ResultVo queryBankCard(@RequestParam("bindStatus") Integer bindStatus, @RequestParam("tobUserId") Integer tobUserId);

    /**
     快捷支付--获取验证要素
     * @param agreementId
     * @param tradeNo
     * @return
     */
    //@PostMapping("/api/trade/payment/v1/pay/quickVilidate")
    //ResultVo quickValidate(@RequestParam("agreementId") String agreementId, @RequestParam("tradeNo") String tradeNo);

    /**
     * 快捷支付--下发验证码
     * @param verifyVo
     * @return
     */
    //@PostMapping("/api/trade/payment/v1/pay/quickVerify")
    //ResultVo quickVerify(VerifyVo verifyVo);

    /**
     * 快捷支付--支付确认
     * @param confirmVo
     * @return
     */
    //@PostMapping("/api/trade/payment/v1/pay/quickConfirm")
    //ResultVo quickConfirm(ConfirmVo confirmVo);


}
