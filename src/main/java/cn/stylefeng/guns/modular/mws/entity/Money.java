package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

/**
 * Money
 * Currency type and amount.
 */
@Data
public class Money {
    /**
     * 	Three-digit currency code.
     */
    @AmazonField(name = "CurrencyCode")
    private String currencyCode;
    /**
     * The currency amount.
     */
    @AmazonField(name = "Amount")
    private String amount;

}
