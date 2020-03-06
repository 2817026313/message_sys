package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

/**
 * The number of Amazon Points offered with the purchase of an item. The Amazon Points program is only available in Japan.
 */
@Data
public class Points {

    @AmazonField(name = "PointsNumber", type = "int")
    private int pointsNumber;

    @AmazonField(name = "PointsMonetaryValue", type = "object")
    private Money pointsMonetaryValue;
}
