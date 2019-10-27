package cn.stylefeng.guns.modular.project.entity;

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
 * 
 * </p>
 *
 * @author 项目表
 * @since 2019-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_measure_info")
public class TbMeasureInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 季度
     */
    @TableField("quarter_index")
    private Integer quarterIndex;
    /**
     * 第一月
     */
    @TableField("month_1")
    private String month1;
    /**
     * 第二月
     */
    @TableField("month_2")
    private String month2;
    /**
     * 第三月
     */
    @TableField("month_3")
    private String month3;
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
     * 每月计量
     */
    @TableField("month_measure")
    private String monthMeasure;
    /**
     * 季度计量
     */
    @TableField("quarter_measure")
    private String quarterMeasure;
    /**
     * 当年计量
     */
    @TableField("year_measure")
    private String yearMeasure;
    /**
     * 开工累计计量
     */
    @TableField("do_add_measure")
    private String doAddMeasure;
    /**
     * 季度工程回款额
     */
    @TableField("quarter_return_money")
    private String quarterReturnMoney;
    /**
     * 年代工程回款额
     */
    @TableField("year_return_money")
    private String yearReturnMoney;
    /**
     * 开工累计回款
     */
    @TableField("do_return_money")
    private String doReturnMoney;
    /**
     * 创建人id
     */
    @TableField("create_id")
    private String createId;
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
