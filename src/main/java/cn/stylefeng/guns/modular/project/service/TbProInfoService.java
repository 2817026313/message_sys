package cn.stylefeng.guns.modular.project.service;

import cn.stylefeng.guns.modular.project.entity.TbProInfo;
import cn.stylefeng.guns.modular.project.mapper.TbProInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 项目产值完成情况 服务实现类
 * </p>
 *
 * @author 项目表
 * @since 2019-10-27
 */
@Service
public class TbProInfoService extends ServiceImpl<TbProInfoMapper, TbProInfo> {

    @Resource
    private TbProInfoMapper proInfoMapper;
}
