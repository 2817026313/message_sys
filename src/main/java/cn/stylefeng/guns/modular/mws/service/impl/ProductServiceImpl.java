package cn.stylefeng.guns.modular.mws.service.impl;

import cn.stylefeng.guns.modular.mws.entity.AmazonConfig;
import cn.stylefeng.guns.modular.mws.entity.FeesEstimateRequest;
import cn.stylefeng.guns.modular.mws.service.ProductService;
import cn.stylefeng.guns.modular.mws.utils.AmazonUntil;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    /***post请求后缀*/
    private static String SUFFIX_POST_URL = "/Products/2011-10-01";

    private static String VERSION = "2011-10-01";


    @Override
    public JSONObject listMatchingProducts(AmazonConfig config, String marketplaceId, String query, String queryContextId) throws Exception {
        String active = "ListMatchingProducts";
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("MarketplaceId", AmazonUntil.urlEncode(marketplaceId));
        parameters.put("Query", AmazonUntil.urlEncode(query));
        parameters.put("QueryContextId", AmazonUntil.urlEncode(queryContextId));
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive(active);
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getMatchingProduct(AmazonConfig config, String marketplaceId, List<String> asinList) throws Exception {
        String active = "GetMatchingProduct";
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("MarketplaceId", AmazonUntil.urlEncode(marketplaceId));
        for (int i = 0; i < asinList.size(); i++) {
            String asin = asinList.get(i);
            parameters.put("ASINList.ASIN." + (i + 1), AmazonUntil.urlEncode(asin));
        }
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive(active);
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getMatchingProductForId(AmazonConfig config, String marketplaceId, String idType, List<String> idList) throws Exception {
        String active = "GetMatchingProductForId";
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("MarketplaceId", AmazonUntil.urlEncode(marketplaceId));
        for (int i = 0; i < idList.size(); i++) {
            String id = idList.get(i);
            parameters.put("IdList.Id." + (i + 1), AmazonUntil.urlEncode(id));
        }
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive(active);
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getServiceStatus(AmazonConfig config) throws Exception {
        String active = "GetServiceStatus";
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive(active);
        return AmazonUntil.doPost(new HashMap<String, String>(), config);
    }

    @Override
    public JSONObject getCompetitivePricingForASIN(AmazonConfig config, String marketplaceId, List<String> asinList) throws Exception {
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("MarketplaceId", AmazonUntil.urlEncode(marketplaceId));
        for (int i = 0; i < asinList.size(); i++) {
            String id = asinList.get(i);
            parameters.put("ASINList.ASIN." + (i + 1), AmazonUntil.urlEncode(id));
        }
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetCompetitivePricingForASIN");
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getLowestOfferListingsForSKU(AmazonConfig config, String marketplaceId, List<String> sellerSKUList, String itemCondition) throws Exception {
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("MarketplaceId", AmazonUntil.urlEncode(marketplaceId));
        parameters.put("ItemCondition", AmazonUntil.urlEncode(itemCondition));
        for (int i = 0; i < sellerSKUList.size(); i++) {
            String id = sellerSKUList.get(i);
            parameters.put("SellerSKUList.SellerSKU." + (i + 1), AmazonUntil.urlEncode(id));
        }
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetLowestOfferListingsForSKU");
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getLowestOfferListingsForASIN(AmazonConfig config, String marketplaceId, List<String> asinList, String itemCondition) throws Exception {
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("MarketplaceId", AmazonUntil.urlEncode(marketplaceId));
        parameters.put("ItemCondition", AmazonUntil.urlEncode(itemCondition));
        for (int i = 0; i < asinList.size(); i++) {
            String id = asinList.get(i);
            parameters.put("ASINList.ASIN." + (i + 1), AmazonUntil.urlEncode(id));
        }
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetLowestOfferListingsForASIN");
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getLowestPricedOffersForSKU(AmazonConfig config, String marketplaceId, String sellerSKU, String itemCondition) throws Exception {
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("MarketplaceId", AmazonUntil.urlEncode(marketplaceId));
        parameters.put("ItemCondition", AmazonUntil.urlEncode(itemCondition));
        parameters.put("SellerSKU", AmazonUntil.urlEncode(sellerSKU));
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetLowestPricedOffersForSKU");
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getLowestPricedOffersForASIN(AmazonConfig config, String marketplaceId, String asin, String itemCondition) throws Exception {
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("MarketplaceId", AmazonUntil.urlEncode(marketplaceId));
        parameters.put("ItemCondition", AmazonUntil.urlEncode(itemCondition));
        parameters.put("ASIN", AmazonUntil.urlEncode(asin));
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetLowestPricedOffersForASIN");
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getMyFeesEstimate(AmazonConfig config, List<FeesEstimateRequest> feesEstimateRequestList) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        Map<String, String> map = AmazonUntil.printAllFieldsByList(feesEstimateRequestList, "FeesEstimateRequestList");
        parameters.putAll(map);
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetMyFeesEstimate");
        return AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getMyPriceForSKU(AmazonConfig config, String marketplaceId, List<String> sellerSKUList, String itemCondition) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("MarketplaceId", marketplaceId);
        parameters.put("ItemCondition", itemCondition);
        if (sellerSKUList != null && !sellerSKUList.isEmpty()){
            for (int i = 0; i < sellerSKUList.size(); i++) {
                String sellerSKU = sellerSKUList.get(i);
                parameters.put("SellerSKUList.SellerSKU." + (i + 1), sellerSKU);
            }
        }
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetMyPriceForSKU");
        return AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getMyPriceForASIN(AmazonConfig config, String marketplaceId, List<String> ASINList, String itemCondition) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("MarketplaceId", marketplaceId);
        parameters.put("ItemCondition", itemCondition);
        if (ASINList != null && !ASINList.isEmpty()){
            for (int i = 0; i < ASINList.size(); i++) {
                String ASIN = ASINList.get(i);
                parameters.put("ASINList.ASIN." + (i + 1), ASIN);
            }
        }
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetMyPriceForASIN");
        return AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getProductCategoriesForSKU(AmazonConfig config, String marketplaceId, String sellerSKU) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("MarketplaceId", marketplaceId);
        parameters.put("SellerSKU", sellerSKU);
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetProductCategoriesForSKU");
        return AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getProductCategoriesForASIN(AmazonConfig config, String marketplaceId, String asin) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("MarketplaceId", marketplaceId);
        parameters.put("ASIN", asin);
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetProductCategoriesForASIN");
        return AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getCompetitivePricingForSKU(AmazonConfig config, String marketplaceId, List<String> sellerSKUList) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("MarketplaceId", marketplaceId);
        if (sellerSKUList != null && !sellerSKUList.isEmpty()){
            for (int i = 0; i < sellerSKUList.size(); i++) {
                String sellerSKU = sellerSKUList.get(i);
                parameters.put("SellerSKUList.SellerSKU." + (i + 1), sellerSKU);
            }
        }
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetCompetitivePricingForSKU");
        return AmazonUntil.doPost(parameters, config);
    }
}
