package cn.stylefeng.guns.modular.mws.service;

import cn.stylefeng.guns.modular.mws.entity.AmazonConfig;
import cn.stylefeng.guns.modular.mws.entity.FeesEstimateRequest;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 *  produce service
 */
public interface ProductService {

    /**
     * Returns a list of products and their attributes, based on a search query.
     * @param marketplaceId  Required  A marketplace identifier. Specifies the marketplace from which products are returned.
     * @param Query         Required   A search string with the same support as that provided on Amazon marketplace websites.
     * @param QueryContextId  An identifier for the context within which the given search will be performed. A marketplace might provide mechanisms for constraining a search to a subset of potential items.
     * @return
     */
    JSONObject listMatchingProducts(AmazonConfig config,String marketplaceId, String Query, String QueryContextId) throws Exception;

    /**
     * Returns a list of products and their attributes, based on a list of ASIN values.
     * @param config
     * @param marketplaceId   A marketplace identifier. Specifies the marketplace from which products are returned.
     * @param ASINList  A structured list of ASIN values. Used to identify products in the given marketplace. Maximum: 10 ASIN values
     * @return
     */
    JSONObject getMatchingProduct(AmazonConfig config, String marketplaceId, List<String> ASINList) throws Exception;

    /**
     *  Returns a list of products and their attributes, based on a list of ASIN, GCID, SellerSKU, UPC, EAN, ISBN, and JAN values.
     * @param config
     * @param marketplaceId  Required  An encrypted, Amazon-defined marketplace identifier.
     * @param idType    Required  IdType values: ASIN, GCID, SellerSKU, UPC, EAN, ISBN, JAN
     * @param idList    Required  A structured list of Id values.  Maximum: Five Id values
     * @return
     * @throws Exception
     */
    JSONObject getMatchingProductForId(AmazonConfig config, String marketplaceId, String idType, List<String> idList)throws Exception;

    /**
     * Returns the current competitive price of a product, based on SellerSKU.
     * @param config
     * @param marketplaceId
     * @param sellerSKUList
     * @return
     * @throws Exception
     */
    JSONObject getCompetitivePricingForSKU(AmazonConfig config, String marketplaceId, List<String> sellerSKUList) throws Exception;

    /**
     *  Returns the operational status of the Products API section.
     * @param config
     * @return
     */
    JSONObject getServiceStatus(AmazonConfig config) throws Exception;

    /**
     * Returns the current competitive price of a product, based on ASIN.
     * @param config
     * @param marketplaceId
     * @param asinList
     * @return
     * @throws Exception
     */
    JSONObject getCompetitivePricingForASIN(AmazonConfig config, String marketplaceId, List<String> asinList) throws Exception;

    /**
     * Returns the current competitive price of a product, based on SellerSKU.
     * @param config
     * @param marketplaceId
     * @param sellerSKUList
     * @param itemCondition
     * @return
     * @throws Exception
     */
    JSONObject getLowestOfferListingsForSKU(AmazonConfig config, String marketplaceId, List<String> sellerSKUList, String itemCondition) throws Exception;

    /**
     * Returns the current competitive price of a product, based on ASIN.
     * @param config
     * @param marketplaceId
     * @param asinList
     * @param itemCondition
     * @return
     * @throws Exception
     */
    JSONObject getLowestOfferListingsForASIN(AmazonConfig config, String marketplaceId, List<String> asinList, String itemCondition) throws Exception;

    /**
     * Returns pricing information for the lowest-price active offer listings for up to 20 products, based on SellerSKU.
     * @param config
     * @param marketplaceId
     * @param sellerSKU
     * @param itemCondition
     * @return
     * @throws Exception
     */
    JSONObject getLowestPricedOffersForSKU(AmazonConfig config, String marketplaceId, String sellerSKU, String itemCondition) throws Exception;

    /**
     * Returns lowest priced offers for a single product, based on ASIN.
     * @param config
     * @param marketplaceId
     * @param asin
     * @param itemCondition
     * @return
     * @throws Exception
     */
    JSONObject getLowestPricedOffersForASIN(AmazonConfig config, String marketplaceId, String asin, String itemCondition) throws Exception;

    /**
     *
     * @param config
     * @param feesEstimateRequestList
     * @return
     * @throws Exception
     */
    JSONObject getMyFeesEstimate(AmazonConfig config, List<FeesEstimateRequest> feesEstimateRequestList ) throws Exception;

    /**
     * Returns pricing information for your own active offer listings, based on SellerSKU.
     * @param config
     * @param marketplaceId
     * @param sellerSKUList
     * @param itemCondition
     * @return
     * @throws Exception
     */
    JSONObject getMyPriceForSKU (AmazonConfig config, String marketplaceId, List<String> sellerSKUList, String itemCondition) throws Exception;

    /**
     * Returns pricing information for your own active offer listings, based on ASIN.
     * @param config
     * @param marketplaceId
     * @param ASINList
     * @param itemCondition
     * @return
     * @throws Exception
     */
    JSONObject getMyPriceForASIN(AmazonConfig config, String marketplaceId, List<String> ASINList, String itemCondition) throws Exception;

    /**
     *  Returns the parent product categories that a product belongs to, based on SellerSKU.
     * @param config
     * @param marketplaceId
     * @param sellerSKU
     * @return
     * @throws Exception
     */
    JSONObject getProductCategoriesForSKU (AmazonConfig config, String marketplaceId, String sellerSKU) throws Exception;

    /**
     *  Returns the parent product categories that a product belongs to, based on ASIN.
     * @param config
     * @param marketplaceId
     * @param asin
     * @return
     * @throws Exception
     */
    JSONObject getProductCategoriesForASIN (AmazonConfig config, String marketplaceId, String asin) throws Exception;
}
