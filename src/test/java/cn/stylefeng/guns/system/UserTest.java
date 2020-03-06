package cn.stylefeng.guns.system;

import cn.stylefeng.guns.base.BaseJunit;
import cn.stylefeng.guns.modular.mws.entity.AmazonConfig;
import cn.stylefeng.guns.modular.mws.entity.Marketplace;
import cn.stylefeng.guns.modular.mws.service.SellerService;
import cn.stylefeng.guns.modular.mws.service.impl.SellerServiceImpl;
import cn.stylefeng.guns.modular.system.mapper.UserMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class UserTest extends BaseJunit {

    @Resource
    UserMapper userMapper;

    @Test
    public void userTest() throws Exception {
        SellerService sellerService = new SellerServiceImpl();
        AmazonConfig config = new AmazonConfig("AKIAEXAMPLEFWR4TJ7ZQ","amzn.mws.4ea38b7b-f563-7709-4bae-87aeaEXAMPLE", "A1IMEXAMPLEWRC", "kWcrlEXAMPLEM/LtmEENI/aVmYvHNif5zB+d9+ct");
        config.setEndpointUrl("https://mws.amazonservices.com");
        List<Marketplace> marketplaceList = sellerService.ListMarketplaceParticipations(config);
        System.out.println(marketplaceList);
    }

}
