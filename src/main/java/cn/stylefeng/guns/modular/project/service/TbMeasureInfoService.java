package cn.stylefeng.guns.modular.project.service;

import cn.stylefeng.guns.modular.project.entity.TbMeasureInfo;
import cn.stylefeng.guns.modular.project.mapper.TbMeasureInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 项目表
 * @since 2019-10-27
 */
@Service
public class TbMeasureInfoService extends ServiceImpl<TbMeasureInfoMapper, TbMeasureInfo> {

    @Resource
    private TbMeasureInfoMapper measureInfoMapper;
}
