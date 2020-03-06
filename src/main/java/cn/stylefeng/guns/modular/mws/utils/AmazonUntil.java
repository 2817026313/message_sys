package cn.stylefeng.guns.modular.mws.utils;


import cn.stylefeng.guns.modular.mws.entity.AmazonConfig;
import cn.stylefeng.guns.modular.mws.entity.ErrorEntity;
import cn.stylefeng.guns.modular.mws.entity.OrderItem;
import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;

public class AmazonUntil {

    /***签名方式*/
    private static String ALGORITHM = "HmacSHA256";

    /****时间转换格式*/
    private static String TIME_FORMAT_STR = "yyyy'-'MM'-'dd'T'HH':'mm':'ss'Z'";

    /****编码格式*/
    private static String CHARACTER_ENCODING = "UTF-8";



    public static JSONObject doPost(HashMap<String, String> parameters, AmazonConfig config) throws Exception {
        String str = DateTime.now(DateTimeZone.UTC).toString(TIME_FORMAT_STR);

        parameters.put("Timestamp", AmazonUntil.urlEncode(str));
        parameters.put("SignatureMethod", AmazonUntil.urlEncode(ALGORITHM));
        parameters.put("SignatureVersion", AmazonUntil.urlEncode("2"));

        parameters.put("Version", AmazonUntil.urlEncode(config.getVersion()));
        parameters.put("Action", AmazonUntil.urlEncode(config.getActive()));
        parameters.put("AWSAccessKeyId", AmazonUntil.urlEncode(config.getAWSAccessKeyId()));
        parameters.put("MWSAuthToken", AmazonUntil.urlEncode(config.getMWSAuthToken()));
        parameters.put("SellerId", AmazonUntil.urlEncode(config.getSellerId()));

        String formattedParameters = AmazonUntil.calculateStringToSignV2(parameters, config.getEndpointUrl(), config.getSuffixPostUrl());
        String signature = AmazonUntil.sign(formattedParameters, config.getSecretKey());
        parameters.put("Signature", AmazonUntil.urlEncode(signature));
        String paramStr = AmazonUntil.sortParams(new StringBuilder(), parameters);
        return  AmazonUntil.doPost(config.getEndpointUrl()+ config.getSuffixPostUrl(), paramStr);
    }

    /**
     * signV2签名内容
     *
     * @param parameters
     * @param serviceUrl
     * @return

     */
    public static String calculateStringToSignV2(
            Map<String, String> parameters, String serviceUrl, String suffixPostUrl)
            throws URISyntaxException {
        URI endpoint = new URI(serviceUrl.toLowerCase());
        StringBuilder data = new StringBuilder();
        data.append("POST\n");
        data.append(endpoint.getHost());
        data.append("\n"+ suffixPostUrl);
        data.append("\n");
        return sortParams(data, parameters);

    }

    /***
     * signV2签名方式
     * @param data
     * @param secretKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalStateException
     * @throws UnsupportedEncodingException
     */
    public static String sign(String data, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IllegalStateException, UnsupportedEncodingException {
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(new SecretKeySpec(secretKey.getBytes(CHARACTER_ENCODING),
                ALGORITHM));
        byte[] signature = mac.doFinal(data.getBytes(CHARACTER_ENCODING));
        String signatureBase64 = new String(Base64.encodeBase64(signature),
                CHARACTER_ENCODING);
        return new String(signatureBase64);
    }

    /**
     * url非法字符转换
     *
     * @param rawValue
     * @return
     */
    public static String urlEncode(String rawValue) {
        String value = (rawValue == null) ? "" : rawValue;
        String encoded = null;

        try {
            encoded = URLEncoder.encode(value, CHARACTER_ENCODING)
                    .replace("+", "%20")
                    .replace("*", "%2A")
                    .replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unknown encoding: " + CHARACTER_ENCODING);
        }

        return encoded;
    }

    /***
     * post请求
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static JSONObject doPost(String url, String params) throws Exception {

        HttpPost httpPost = setHttpPostHead(url, params);
        JSONObject response = getResponse(httpPost);
        JSONObject errorResponse = response.getJSONObject("ErrorResponse");
        if (errorResponse != null){
            ErrorEntity error = errorResponse.getObject("Error", ErrorEntity.class);
            throw new ServiceException(503,error.getCode() + "：" + error.getMessage());
        }else {
            return response;
        }
    }


    public static HttpPost setHttpPostHead(String url, String params){
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        httpPost.setHeader("Accept", "Accept: text/plain, */*");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
        httpPost.addHeader("x-amazon-user-agent", "AmazonJavascriptScratchpad/1.0 (Language=Javascript)");
        httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
        String charSet = "UTF-8";

        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        return httpPost;
    }

    public static JSONObject getResponse(HttpPost httpPost) throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            InputStream is = responseEntity.getContent();
            return parseXML(is);
        } catch (Exception e) {
            throw e;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw e;
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                throw e;
            }
        }
    }

    /**
     * 对传递参数转换
     *
     * @param data
     * @param parameters
     * @return
     */
    public static String sortParams(StringBuilder data, Map<String, String> parameters) {
        Map<String, String> sorted = new TreeMap<String, String>();
        sorted.putAll(parameters);

        Iterator<Entry<String, String>> pairs =
                sorted.entrySet().iterator();
        while (pairs.hasNext()) {
            Entry<String, String> pair = pairs.next();
            if (pair.getValue() != null) {
                data.append(pair.getKey() + "=" + pair.getValue());
            } else {
                data.append(pair.getKey() + "=");
            }
            if (pairs.hasNext()) {
                data.append("&");
            }
        }
        return data.toString();
    }

    public static JSONObject parseXML(InputStream is) throws JDOMException, IOException {

        JSONObject jsonObject = new JSONObject();
        SAXBuilder sb = new SAXBuilder();
        org.jdom2.Document doc = sb.build(is);
        Element root = doc.getRootElement();
        jsonObject.put(root.getName(), iterateElement(root));
        return  jsonObject;
    }

    private static JSONObject iterateElement(Element element) {
        List node = element.getChildren();
        Element et = null;
        JSONObject obj = new JSONObject();
        List list = null;
        for (int i = 0; i < node.size(); i++) {
            list = new LinkedList();
            et = (Element) node.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.getChildren().size() == 0)
                    continue;
                if (obj.containsKey(et.getName())) {
                    try {
                        list = (List) obj.get(et.getName());
                    }catch (ClassCastException e){
                        list.add(obj.get(et.getName()));
                    }

                }
                list.add(iterateElement(et));

            } else {
                if (obj.containsKey(et.getName())) {
                    try {
                        list = (List) obj.get(et.getName());
                    }catch (ClassCastException e){
                        list.add(obj.get(et.getName()));
                    }
                }
                list.add(et.getTextTrim());
            }
            if (list.size() == 1){
                obj.put(et.getName(), list.get(0));
            }else {
                obj.put(et.getName(), list);
            }

        }
        return obj;
    }


    /**
     * Calculate content MD5 hash values for feeds stored on disk.
     */
    public static String computeContentMD5Value( FileInputStream fis )
            throws IOException, NoSuchAlgorithmException {

        DigestInputStream dis = new DigestInputStream( fis,
                MessageDigest.getInstance( "MD5" ));

        byte[] buffer = new byte[8192];
        while( dis.read( buffer ) > 0 );

        String md5Content = new String(
                org.apache.commons.codec.binary.Base64.encodeBase64(
                        dis.getMessageDigest().digest()) );

        // Effectively resets the stream to be beginning of the file
        // via a FileChannel.
        fis.getChannel().position( 0 );

        return md5Content;
    }

    /**
     * Consume the stream and return its Base-64 encoded MD5 checksum.
     */
    public static String computeContentMD5Header(InputStream inputStream) {
        // Consume the stream to compute the MD5 as a side effect.
        DigestInputStream s;
        try {
            s = new DigestInputStream(inputStream,
                    MessageDigest.getInstance("MD5"));
            // drain the buffer, as the digest is computed as a side-effect
            byte[] buffer = new byte[8192];
            while(s.read(buffer) > 0);
            return new String(
                    org.apache.commons.codec.binary.Base64.encodeBase64(
                            s.getMessageDigest().digest()),
                    "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> jsonToArray(JSONObject json, String codeName, Class<T> clazz){
        String codeStr = json.getString(codeName);
        List<T> list = new ArrayList<>();
        if (codeStr.startsWith("[") && codeStr.endsWith("]")){
            list = JSONArray.parseArray(codeStr, clazz);
        }else {
            T t = JSONObject.parseObject(codeStr, clazz);
            list.add(t);
        }
        return list;
    }

    public static void main(String[] args) throws IOException, JDOMException {
        String path = "C:\\Users\\Administrator\\Desktop\\其他\\test.xml";

        FileInputStream fis = new FileInputStream(path);
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = parseXML(fis);

        JSONObject errorResponse = jsonObject.getJSONObject("ErrorResponse");
        if (errorResponse != null){
            ErrorEntity error = errorResponse.getObject("Error", ErrorEntity.class);
            System.out.println(error);
        }else {
            System.out.println(jsonObject);
        }
        String nextToken = null;
        List<OrderItem> marketplaces = new ArrayList<>();
        if (jsonObject != null){
            JSONObject listMarketplaceParticipationsResponse = jsonObject.getJSONObject("ListOrderItemsResponse");
            if (listMarketplaceParticipationsResponse != null){
                JSONObject listMarketplaceParticipationsResult = listMarketplaceParticipationsResponse.getJSONObject("ListOrderItemsResult");
                if (listMarketplaceParticipationsResult != null){
                    nextToken = listMarketplaceParticipationsResult.get("NextToken").toString();
                    JSONObject listMarketplaces = listMarketplaceParticipationsResult.getJSONObject("OrderItems");
                    if (listMarketplaces != null){
                        marketplaces = jsonToArray(listMarketplaces, "OrderItem", OrderItem.class);
                        map.put("NextToken", nextToken);
                        map.put("marketplaces", marketplaces);
                    }
                }
            }
        }

        marketplaces = (List<OrderItem>)map.get("marketplaces");
        for (OrderItem marketplace : marketplaces) {
            System.out.println(marketplace);
        }
        //System.out.println(marketplaces);
    }


    public static  Map<String, String> printAllFieldsByList(List list, String listName){
        Map<String, String> FieldMap = new HashMap<>();
        if (list != null && !list.isEmpty()){
            int i = 0;
            for (Object o : list) {
                i++;
                Class<?> aClass = o.getClass();
                String simpleName = aClass.getSimpleName();
                Map<String, String> map = printAllFields(o);
                if (!map.isEmpty()){
                    for (String s : map.keySet()) {
                        FieldMap.put(listName + "." +simpleName + "." + i + "."+ s, map.get(s));
                    }
                }
            }
        }
        return FieldMap;
    }


    public static  Map<String, String> printAllFields(Object obj){
        Class cls=obj.getClass();
        Field[] fields=cls.getDeclaredFields();
        Map<String, String> map = new HashMap<>();
        for (Field field:fields) {
            AmazonField annotation = field.getAnnotation(AmazonField.class);
            field.setAccessible(true);
            try {
                if (!annotation.type().equals("object")){
                    if (field.get(obj) != null){
                        map.put(annotation.name(), field.get(obj).toString());
                    }
                }else {
                    Map<String, String> map1 = printAllFields(field.get(obj));
                    if (!map1.isEmpty()){
                        for (String s : map1.keySet()) {
                            map.put(annotation.name()+"."+ s, map1.get(s));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return  map;
    }
}
