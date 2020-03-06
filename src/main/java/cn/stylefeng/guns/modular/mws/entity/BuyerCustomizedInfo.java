package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

@Data
public class BuyerCustomizedInfo {

    @AmazonField(name = "CustomizedURL", type = "string")
    private String customizedURL;
}
