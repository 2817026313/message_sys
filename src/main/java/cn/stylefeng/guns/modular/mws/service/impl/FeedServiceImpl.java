package cn.stylefeng.guns.modular.mws.service.impl;

import cn.stylefeng.guns.modular.mws.entity.AmazonConfig;
import cn.stylefeng.guns.modular.mws.service.FeedService;
import cn.stylefeng.guns.modular.mws.utils.AmazonUntil;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

public class FeedServiceImpl implements FeedService {

    /***post请求后缀*/
    private static String SUFFIX_POST_URL = "/Feeds/2009-01-01";

    private static String ENDPOINT_URL = "https://mws.amazonservices.com";

    private static String VERSION = "2009-01-01";

    private static String secretKey = "";
    /****时间转换格式*/
    private static String TIME_FORMAT_STR = "yyyy'-'MM'-'dd'T'HH':'mm':'ss'Z'";

    /***签名方式*/
    private static String ALGORITHM = "HmacSHA256";

    private static String SIGNATURE_VERSION ="2";

    @Override
    public String SubmitFeed(String xmlUrl, String feedType, List<String> marketplaceIdList, boolean purgeAndReplace) throws Exception {
        String action = "SubmitFeed";

        String str = DateTime.now(DateTimeZone.UTC).toString(TIME_FORMAT_STR);
        HashMap<String, String> parameters = new HashMap<>();

        FileInputStream fis = new FileInputStream(xmlUrl);
        String contentMD5Value = AmazonUntil.computeContentMD5Value(fis);

        parameters.put("Action", AmazonUntil.urlEncode(action));
        parameters.put("Version", AmazonUntil.urlEncode(VERSION));
        parameters.put("FeedType", feedType);
        parameters.put("ContentMD5Value", contentMD5Value);
        parameters.put("PurgeAndReplace", String.valueOf(purgeAndReplace));

        if(marketplaceIdList != null && !marketplaceIdList.isEmpty()){
            for (int i = 0; i < marketplaceIdList.size(); i++) {
                String marketplaceId = marketplaceIdList.get(i);
                parameters.put("MarketplaceIdList.Id." + (i + 1), marketplaceId);
            }
        }
        parameters.put("Timestamp", AmazonUntil.urlEncode(str));
        parameters.put("SignatureMethod", AmazonUntil.urlEncode(ALGORITHM));
        parameters.put("SignatureVersion", AmazonUntil.urlEncode(SIGNATURE_VERSION));
        String formattedParameters = AmazonUntil.calculateStringToSignV2(parameters, ENDPOINT_URL, SUFFIX_POST_URL);
        String signature = AmazonUntil.sign(formattedParameters, secretKey);
        parameters.put("Signature", AmazonUntil.urlEncode(signature));
        String paramStr = AmazonUntil.sortParams(new StringBuilder(), parameters);

        HttpPost httpPost = new HttpPost(ENDPOINT_URL + SUFFIX_POST_URL + "?" + paramStr);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        httpPost.setHeader("Accept", "Accept: text/plain, */*");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
        httpPost.addHeader("x-amazon-user-agent", "AmazonJavascriptScratchpad/1.0 (Language=Javascript)");
        httpPost.addHeader("X-Requested-With", "XMLHttpRequest");

        HttpEntity build = MultipartEntityBuilder.create().addBinaryBody("FeedContent", fis).build();
        httpPost.setEntity(build);

        String feedSubmissionId = null;
        JSONObject response = AmazonUntil.getResponse(httpPost);
        JSONObject submitFeedResult = response.getJSONObject("SubmitFeedResult");
        if (submitFeedResult != null){
            JSONObject feedSubmissionInfo = submitFeedResult.getJSONObject("FeedSubmissionInfo");
            if (feedSubmissionInfo != null){
                feedSubmissionId = feedSubmissionInfo.getString("FeedSubmissionId");
            }
        }
        return feedSubmissionId;
    }

    @Override
    public JSONObject getFeedSubmissionList(AmazonConfig config, HashMap<String, Object> map) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("MaxCount", AmazonUntil.urlEncode(map.get("MaxCount").toString()));
        parameters.put("SubmittedFromDate", AmazonUntil.urlEncode(map.get("SubmittedFromDate").toString()));
        parameters.put("SubmittedToDate", AmazonUntil.urlEncode(map.get("SubmittedToDate").toString()));
       if (map.get("FeedSubmissionIdList") != null){
          List<String> feedSubmissionIdList = (List<String>) map.get("FeedSubmissionIdList");
           for (int i = 0; i < feedSubmissionIdList.size(); i++) {
               String id = feedSubmissionIdList.get(i);
               parameters.put("FeedSubmissionIdList.Id." + (i + 1), AmazonUntil.urlEncode(id));
           }
       }

        if (map.get("FeedTypeList") != null){
            List<String> feedTypeList = (List<String>) map.get("FeedTypeList");
            for (int i = 0; i < feedTypeList.size(); i++) {
                String type = feedTypeList.get(i);
                parameters.put("FeedTypeList.Type." + (i + 1), AmazonUntil.urlEncode(type));
            }
        }

        if (map.get("FeedProcessingStatusList") != null){
            List<String> feedProcessingStatusList = (List<String>) map.get("FeedProcessingStatusList");
            for (int i = 0; i < feedProcessingStatusList.size(); i++) {
                String status = feedProcessingStatusList.get(i);
                parameters.put("FeedProcessingStatusList.Status." + (i + 1), AmazonUntil.urlEncode(status));
            }
        }

        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetFeedSubmissionList");
        return AmazonUntil.doPost(parameters, config);
    }

    @Override
    public JSONObject getFeedSubmissionListByNextToken(AmazonConfig config, String nextToken) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("NextToken", AmazonUntil.urlEncode(nextToken));
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetFeedSubmissionList");
        return AmazonUntil.doPost(parameters, config);

    }

    @Override
    public JSONObject getFeedSubmissionCount(AmazonConfig config, HashMap<String, Object> map) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("SubmittedFromDate", AmazonUntil.urlEncode(map.get("SubmittedFromDate").toString()));
        parameters.put("SubmittedToDate", AmazonUntil.urlEncode(map.get("SubmittedToDate").toString()));
        if (map.get("FeedTypeList") != null){
            List<String> feedTypeList = (List<String>) map.get("FeedTypeList");
            for (int i = 0; i < feedTypeList.size(); i++) {
                String type = feedTypeList.get(i);
                parameters.put("FeedTypeList.Type." + (i + 1), AmazonUntil.urlEncode(type));
            }
        }
        if (map.get("FeedProcessingStatusList") != null){
            List<String> feedProcessingStatusList = (List<String>) map.get("FeedProcessingStatusList");
            for (int i = 0; i < feedProcessingStatusList.size(); i++) {
                String status = feedProcessingStatusList.get(i);
                parameters.put("FeedProcessingStatusList.Status." + (i + 1), AmazonUntil.urlEncode(status));
            }
        }
        config.setSuffixPostUrl(SUFFIX_POST_URL);
        config.setVersion(VERSION);
        config.setActive("GetFeedSubmissionCount");
        return AmazonUntil.doPost(parameters, config);
    }
}
