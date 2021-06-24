package com.cxl.es.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author cxl
 */
public class InitEs {
    public static RestHighLevelClient getClient(){
        return new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1",9200,"http")));
    }
}
