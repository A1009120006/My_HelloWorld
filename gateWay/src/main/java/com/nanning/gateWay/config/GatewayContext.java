package com.nanning.gateWay.config;
import org.springframework.util.MultiValueMap;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GatewayContext {

    public static final String CACHE_GATEWAY_CONTEXT = "cacheGatewayContext";

    /**
     * cache json body
     */
    private String cacheBody;
    /**
     * cache json body
     */
    private MultiValueMap<String, String> queryParams;
    /**
     * cache formdata
     */
    private MultiValueMap<String, String> formData;
    /**
     * cache reqeust path
     */
    private String path;
}