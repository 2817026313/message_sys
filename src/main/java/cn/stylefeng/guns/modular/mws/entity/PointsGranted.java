package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

/**
 * The number and value of Amazon Points granted with the purchase of an item. This datatype is used only in the Japan marketplace.
 */
@Data
public class PointsGranted {

    @AmazonField(name = "PointsNumber")
    private int pointsNumber;

    @AmazonField(name = "PointsMonetaryValue", type = "object")
    private Money pointsMonetaryValue;
}
