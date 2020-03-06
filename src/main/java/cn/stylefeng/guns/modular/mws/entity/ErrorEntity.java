package cn.stylefeng.guns.modular.mws.entity;

import lombok.Data;

@Data
public class ErrorEntity {

    private String type;

    private String code;

    private String message;

    private String detail;

}
