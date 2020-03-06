package cn.stylefeng.guns.modular.mws.service;

import cn.stylefeng.guns.modular.mws.entity.AmazonConfig;
import cn.stylefeng.guns.modular.mws.entity.Marketplace;

import java.util.List;

/**
 * The Amazon MWS Sellers API section of the Amazon Marketplace Web Service (Amazon MWS) API lets sellers retrieve information about
 * their seller account, such as the marketplaces they participate in. Along with listing the marketplaces that a seller can sell in,
 * the API also provides additional information about the marketplace such as the default language and the default currency.
 * The API also provides seller-specific information such as whether the seller has suspended listings in that marketplace.
 */
public interface SellerService {
    /**
     *  Returns a list of marketplaces that the seller submitting the request can sell in,
     *  and a list of participations that include seller-specific information in that marketplace.
     * @param config
     * @return
     * @throws Exception
     */
    List<Marketplace> ListMarketplaceParticipations(AmazonConfig config) throws Exception;

    List<Marketplace> ListMarketplaceParticipationsByNextToken(AmazonConfig config, String NextToken) throws Exception;
}
