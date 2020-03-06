package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

/**
 * A list of payment methods for the order.
 */
@Data
public class ProductInfo {
    @AmazonField(name = "NumberOfItems")
    private int numberOfItems;
}
