package cn.stylefeng.guns.modular.project.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 项目表
 * @since 2019-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_project")
public class TbProject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 合同额
     */
    @TableField("contract_amount")
    private BigDecimal contractAmount;
    /**
     * 部门id
     */
    @TableField("dept_id")
    private String deptId;
    /**
     * 部门名称
     */
    @TableField("dept_name")
    private String deptName;
    /**
     * 任务内容
     */
    @TableField("pro_desc")
    private String proDesc;
    /**
     * 项目开始时间
     */
    @TableField("begin_time")
    private Date beginTime;
    /**
     * 是否删除
     */
    @TableField("is_del")
    private Integer isDel;


}
