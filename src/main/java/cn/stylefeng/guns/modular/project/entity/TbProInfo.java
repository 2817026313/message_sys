package cn.stylefeng.guns.modular.project.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目产值完成情况
 * </p>
 *
 * @author 项目表
 * @since 2019-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_pro_info")
public class TbProInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
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
     * 统计年份
     */
    private Integer year;
    /**
     * 第几季度
     */
    @TableField("quarter_index")
    private Integer quarterIndex;
    /**
     * 项目id
     */
    @TableField("project_id")
    private String projectId;
    /**
     * 项目名称
     */
    @TableField("project_name")
    private String projectName;
    /**
     * 合同额
     */
    @TableField("contract_amount")
    private BigDecimal contractAmount;
    /**
     * 年计划产值
     */
    @TableField("year_plan")
    private BigDecimal yearPlan;
    /**
     * 季度计划产值
     */
    @TableField("quarter_plan")
    private BigDecimal quarterPlan;
    /**
     * 季度实际产值
     */
    @TableField("quarten_real")
    private String quartenReal;
    /**
     * 季度完成额
     */
    @TableField("quarten_finail")
    private BigDecimal quartenFinail;
    /**
     * 年累计完成值
     */
    @TableField("year_add_finail")
    private BigDecimal yearAddFinail;
    /**
     * 年累计完成营业额
     */
    @TableField("year_add_business")
    private BigDecimal yearAddBusiness;
    /**
     * 开工累计产值
     */
    @TableField("do_add_finail")
    private BigDecimal doAddFinail;
    /**
     * 开工累计完成营业额
     */
    @TableField("do_add_business")
    private BigDecimal doAddBusiness;
    /**
     * 创建人id
     */
    @TableField("create_id")
    private Integer createId;
    /**
     * 创建人名称
     */
    @TableField("create_name")
    private String createName;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


}
