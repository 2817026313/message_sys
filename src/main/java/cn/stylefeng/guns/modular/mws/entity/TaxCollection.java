package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

@Data
public class TaxCollection {
    @AmazonField(name = "Model")
    private String model;

    @AmazonField(name = "ResponsibleParty")
    private String responsibleParty;
}
