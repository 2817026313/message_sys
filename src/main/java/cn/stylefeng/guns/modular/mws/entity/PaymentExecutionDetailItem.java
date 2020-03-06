package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

@Data
public class PaymentExecutionDetailItem {

    @AmazonField(name = "Payment", type = "object")
    private Money payment;

    @AmazonField(name = "PaymentMethod", type = "string")
    private String paymentMethod;
}
