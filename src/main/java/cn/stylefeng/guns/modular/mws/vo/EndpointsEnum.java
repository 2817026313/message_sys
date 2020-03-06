package cn.stylefeng.guns.modular.mws.vo;

/**
 * Amazon MWS endpoints and MarketplaceId values
 */
public enum EndpointsEnum {

    /**
     *  1. North America region
     */
    BR("https://mws.amazonservices.com", "A2Q3Y263D00KWC"),  // Brazil

    CA("https://mws.amazonservices.ca", "A2EUQ1WTGCTBG2"),   // Canada

    MX("https://mws.amazonservices.com.mx", "A1AM78C64UM0Y8"),// Mexico

    US("https://mws.amazonservices.com", "ATVPDKIKX0DER"),       // US

    /**
     *  2. Europe region
     */
    AE("https://mws.amazonservices.ae", "A2VIGQ35RCS4UG"),  // United Arab Emirates (U.A.E.)

    DE("https://mws-eu.amazonservices.com", "A1PA6795UKMFR9"),// Germany

    EG("https://mws-eu.amazonservices.com", "ARBP9OOSHTCHU"), // Egypt

    ES("https://mws-eu.amazonservices.com", "A1RKKUPIHCS9HS"),  // Spain

    FR("https://mws-eu.amazonservices.com", "A13V1IB3VIYZZH"), // France

    GB("https://mws-eu.amazonservices.com", "A1F83G8C2ARO7P"),  // UK

    IN("https://mws.amazonservices.in", "A21TJRUUN4KGV"),       // India

    IT("https://mws-eu.amazonservices.com", "APJ6JRA9NG5V4"),   // Italy

    NL("https://mws-eu.amazonservices.com", "A1805IZSGTT6HS"),  // Netherlands

    SA("https://mws-eu.amazonservices.com", "A17E79C6D8DWNP"), // Saudi Arabia

    TR("https://mws-eu.amazonservices.com", "A33AVAJ2PDY3EV"),  // Turkey

    /**
     *  3. Far East region
     */
    SG("https://mws-fe.amazonservices.com", "A19VAU5U5O7RUS"),  // Singapore

    AU("https://mws.amazonservices.com.au", "A39IBJ37TRP1C6"),  // Australia

    JP("https://mws.amazonservices.jp", "A1VC38T7YXB528")       // Japan




    ;


    private String endpoint;

    private String marketplaceId;

    EndpointsEnum(String endpoint, String marketplaceId){
        this.endpoint = endpoint;
        this.marketplaceId = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }
}
