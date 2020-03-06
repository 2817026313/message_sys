package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

@Data
public class PaymentMethodDetail {
    /**
     * 	A payment method for the order. For example, GiftCertificate or CreditCard.
     */
    @AmazonField(name = "PaymentMethodDetail")
    private String paymentMethodDetail;
}
