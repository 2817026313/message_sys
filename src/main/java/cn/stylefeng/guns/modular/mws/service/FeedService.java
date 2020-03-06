package cn.stylefeng.guns.modular.mws.service;

import cn.stylefeng.guns.modular.mws.entity.AmazonConfig;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 亚马逊 MWS 上传数据 API 部分
 */
@Service
public interface FeedService {

    /**
     * 上传上传数据以供亚马逊MWS处理
     *
     *      FeedContent	: 必填 上传数据自身的实际内容，可以是 XML 文件或库存模板文件格式。您必须 在 HTTP 请求的正文中包含 FeedContent。 类型：HTTP-BODY
     *      FeedType： 必填  指明了数据处理方式。 String
     *      MarketplaceIdList: 您已注册的要应用上传数据的一个或多个 商城 ID。上传数据将应用于您指定的所有商城。 例如： &MarketplaceIdList.Id.1=A13V1IB3VIYZZH &MarketplaceIdList.Id.2=A1PA6795UKMFR9
     *      PurgeAndReplace: xs:boolean  可启用清除并替换功能的布尔值。要清除并替换现有数据，设置为 true，否则设置为 false。该值仅适用于商品相关库存模板文件上传数据类型，不可在上传数据正文中指定清除并替换。只能在特殊情况下使用该参数。其使用受到限制，在 24 小时的时段内，只允许进行一次清除和替换。
     *
     *
     * @return String  FeedSubmissionId  上传数据提交的唯一编码
     */
    String SubmitFeed(String xmlUrl, String FeedType, List<String> MarketplaceIdList, boolean PurgeAndReplace) throws Exception;

    /**
     * Returns a list of all feed submissions submitted in the previous 90 days.
     * @param config
     * @param map:
     *           FeedSubmissionIdList:A structured list of no more than 100 FeedSubmmissionId values.
     *           MaxCount:A non-negative integer that indicates the maximum number of feed submissions to return in the list. If you specify a number greater than 100, the request is rejected.
     *           FeedTypeList: A structured list of one or more FeedType values by which to filter the list of feed submissions.
     *           FeedProcessingStatusList: 	A structured list of one or more feed processing statuses by which to filter the list of feed submissions.
     *           SubmittedFromDate:	The earliest submission date that you are looking for, in
     *           SubmittedToDate: 	The latest submission date that you are looking for, in ISO8601 date format.
     * @return
     * @throws Exception
     */
    JSONObject getFeedSubmissionList(AmazonConfig config, HashMap<String, Object> map) throws Exception;

    /**
     * Returns a list of feed submissions using the NextToken parameter.
     * @param config
     * @param nextToken A string token returned by a previous request to either
     * @return
     * @throws Exception
     */
    JSONObject getFeedSubmissionListByNextToken(AmazonConfig config, String nextToken) throws Exception;

    /**
     *Returns a count of the feeds submitted in the previous 90 days.
     * @param config
     * @param map
     *           FeedTypeList: A structured list of one or more FeedType values by which to filter the list of feed submissions.
     *           FeedProcessingStatusList: 	A structured list of one or more feed processing statuses by which to filter the list of feed submissions.
     *           SubmittedFromDate:	The earliest submission date that you are looking for, in
     *           SubmittedToDate: 	The latest submission date that you are looking for, in ISO8601 date format.
     * @return
     * @throws Exception
     */
    JSONObject getFeedSubmissionCount(AmazonConfig config, HashMap<String, Object> map) throws Exception;
}
