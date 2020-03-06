package cn.stylefeng.guns.modular.mws.service.impl;

import cn.stylefeng.guns.modular.mws.entity.AmazonConfig;
import cn.stylefeng.guns.modular.mws.service.OrderService;
import cn.stylefeng.guns.modular.mws.utils.AmazonUntil;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    /***post请求后缀*/
    private static String SUFFIX_POST_URL = "/Sellers/2011-07-01";

    private static String VERSION = "2011-07-01";

    @Override
    public JSONObject listOrders(AmazonConfig config, Map<String, Object> params) throws Exception {

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("CreatedAfter", AmazonUntil.urlEncode(params.get("CreatedAfter").toString()));
        parameters.put("CreatedBefore", AmazonUntil.urlEncode(params.get("CreatedBefore").toString()));
        parameters.put("LastUpdatedAfter", AmazonUntil.urlEncode(params.get("LastUpdatedAfter").toString()));
        parameters.put("LastUpdatedBefore", AmazonUntil.urlEncode(params.get("LastUpdatedBefore").toString()));
        parameters.put("FulfillmentChannel", AmazonUntil.urlEncode(params.get("FulfillmentChannel").toString()));
        parameters.put("PaymentMethod", AmazonUntil.urlEncode(params.get("PaymentMethod").toString()));
        parameters.put("BuyerEmail", AmazonUntil.urlEncode(params.get("BuyerEmail").toString()));
        parameters.put("SellerOrderId",  AmazonUntil.urlEncode(params.get("SellerOrderId").toString()));
        parameters.put("MaxResultsPerPage",  AmazonUntil.urlEncode(params.get("MaxResultsPerPage").toString()));
        parameters.put("EasyShipShipmentStatus",  AmazonUntil.urlEncode(params.get("EasyShipShipmentStatus").toString()));

        if (params.get("MarketplaceIds") != null){
            List<String> marketplaceIds = (List<String>)params.get("MarketplaceIds");
            for (int i = 0; i < marketplaceIds.size(); i++) {
                String marketplaceId = marketplaceIds.get(i);
                parameters.put("MarketplaceId.Id." +(i + 1),  AmazonUntil.urlEncode(marketplaceId));
            }
        }

        if (params.get("OrderStatus") != null){
            List<String> orderStatusList = (List<String>)params.get("OrderStatus");
            for (int i = 0; i < orderStatusList.size(); i++) {
                String orderStatus = orderStatusList.get(i);
                parameters.put("OrderStatus.Status." +(i + 1),  orderStatus);
            }
        }
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("ListOrders");
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject listOrdersByNextToken(AmazonConfig config, String nextToken) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("NextToken", nextToken);
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("ListOrdersByNextToken");
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getOrder(AmazonConfig config, String amazonOrderId) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("AmazonOrderId", amazonOrderId);
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetOrder");
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject listOrderItems(AmazonConfig config, String amazonOrderId) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("AmazonOrderId", amazonOrderId);
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("ListOrderItems");
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject listOrderItemsByNextToken(AmazonConfig config, String nextToken) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("NextToken", nextToken);
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("ListOrderItemsByNextToken");
        return  AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getServiceStatus(AmazonConfig config) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetServiceStatus");
        return  AmazonUntil.doPost(parameters, config);
    }

}
