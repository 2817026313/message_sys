package cn.stylefeng.guns.modular.mws.service.impl;

import cn.stylefeng.guns.modular.mws.entity.AmazonConfig;
import cn.stylefeng.guns.modular.mws.entity.Marketplace;
import cn.stylefeng.guns.modular.mws.service.SellerService;
import cn.stylefeng.guns.modular.mws.utils.AmazonUntil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerServiceImpl implements SellerService {

    /***post请求后缀*/
    private static String SUFFIX_POST_URL = "/Sellers/2011-07-01";

    private static String VERSION = "2011-07-01";

    @Override
    public List<Marketplace> ListMarketplaceParticipations(AmazonConfig config) throws Exception {
        JSONObject jsonObject = sellerDoPost(config, new HashMap<String, String>(), "ListMarketplaceParticipations");
        List<Marketplace> marketplaces = new ArrayList<>();
        Map<String, Object> sellerResponseMap = getSellerResponseMapByJSONObject(jsonObject);
        List<Marketplace> marketplaceList = getListMarketplaceParticipationsByNextToken(config, sellerResponseMap);
        if (marketplaceList != null){
            marketplaces.addAll(marketplaceList);
        }
        return marketplaces;
    }

    @Override
    public List<Marketplace> ListMarketplaceParticipationsByNextToken(AmazonConfig config, String nextToken) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        List<Marketplace> marketplaces = new ArrayList<>();
        parameters.put("NextToken", nextToken);
        JSONObject jsonObject = sellerDoPost(config, parameters, "ListMarketplaceParticipationsByNextToken");
        Map<String, Object> sellerResponseMap = getSellerResponseMapByJSONObject(jsonObject);
        List<Marketplace> marketplaceList = getListMarketplaceParticipationsByNextToken(config, sellerResponseMap);
        if (marketplaceList != null){
            marketplaces.addAll(marketplaceList);
        }
        return marketplaces;
    }


    private Map<String, Object> getSellerResponseMapByJSONObject(JSONObject jsonObject){
        Map<String, Object> map = new HashMap<>();
        String nextToken = null;
        List<Marketplace> marketplaces = new ArrayList<>();
        if (jsonObject != null){
            JSONObject listMarketplaceParticipationsResponse = jsonObject.getJSONObject("ListMarketplaceParticipationsResponse");
            if (listMarketplaceParticipationsResponse != null){
                JSONObject listMarketplaceParticipationsResult = listMarketplaceParticipationsResponse.getJSONObject("ListMarketplaceParticipationsResult");
                if (listMarketplaceParticipationsResult != null){
                    nextToken = listMarketplaceParticipationsResult.get("NextToken").toString();
                    JSONObject listMarketplaces = listMarketplaceParticipationsResult.getJSONObject("ListMarketplaces");
                    if (listMarketplaces != null){
                        marketplaces = AmazonUntil.jsonToArray(listMarketplaces, "Marketplace", Marketplace.class);
                    }
                }
            }
        }
        map.put("NextToken", nextToken);
        map.put("marketplaces", marketplaces);
        return map;
    }

    private List<Marketplace> getListMarketplaceParticipationsByNextToken(AmazonConfig config, Map<String, Object> sellerResponseMap) throws Exception {
        List<Marketplace> marketplaces = null;
        String nextToken = sellerResponseMap.get("NextToken").toString();
        marketplaces = (List<Marketplace>)sellerResponseMap.get("marketplaces");
        if (StringUtils.isNotBlank(nextToken)){
            List<Marketplace> marketplaceList = ListMarketplaceParticipationsByNextToken(config, nextToken);
            if (marketplaceList != null && !marketplaceList.isEmpty()){
                if (marketplaces != null && !marketplaces.isEmpty()){
                    marketplaces.addAll(marketplaceList);
                }else {
                    marketplaces = marketplaceList;
                }
            }
        }
        return marketplaces;
    }


    private JSONObject sellerDoPost(AmazonConfig config, HashMap<String, String> parameters, String active) throws Exception {
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive(active);
        return  AmazonUntil.doPost(parameters, config);
    }
}
