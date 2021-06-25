package com.cxl.es.create;

import com.cxl.es.util.InitEs;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author cxl
 */
public class CreateIndex {
    private static final Logger LOGGER= LoggerFactory.getLogger(CreateIndex.class);
    public void createIndex() {
        try (RestHighLevelClient client = InitEs.getClient()) {
            //1.创建索引
            CreateIndexRequest request = new CreateIndexRequest("cxl");

            //2.设置索引的settings
            /**
             * index.number_of_shards 分片数
             * index.number_of_replicas 副本数
             * analysis.analyzer.default.tokenizer 默认分词器
             */
            request.settings(Settings.builder().put("index.number_of_shards", 3)
                    .put("index.number_of_replicas", 2)
                    .put("analysis.analyzer.default.tokenizer", "ik_smart"));

            //3.设置索引mappings
            request.mapping(
                    "{\n" +
                            "\"_doc\":{\n" +
                            "\"properties\":{\n" +
                            "\"message\":{\n" +
                            "\"type\":\"test\"\n" +
                            "}\n" +
                            "}\n" +
                            "}\n" +
                            "}", XContentType.JSON);

            //4.设置索引的别名
            request.alias(new Alias("c"));

            //5.发送请求
            //5.1同步方式发送请求
            CreateIndexResponse createIndexResponse=client.indices()
                    .create(request, RequestOptions.DEFAULT);

            //6.处理响应
            boolean acknowledged = createIndexResponse.isAcknowledged();
            boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
            LOGGER.debug("acknowledged>>>>>>{}",acknowledged);
            LOGGER.debug("shardsAcknowledged>>>>>>>>>>>{}",shardsAcknowledged);


            //5.2异步发送请求
            ActionListener<CreateIndexResponse> listener=new ActionListener<CreateIndexResponse>() {
                @Override
                public void onResponse(CreateIndexResponse createIndexResponse) {
                    //6.处理响应
                    boolean acknowledged1 = createIndexResponse.isAcknowledged();
                    boolean shardsAcknowledged1 = createIndexResponse.isShardsAcknowledged();
                    LOGGER.debug("acknowledged1>>>>>>>>>>>{}",acknowledged1);
                    LOGGER.debug("shardsAcknowledged>>>>>>>>>>>{}",shardsAcknowledged1);
                }

                @Override
                public void onFailure(Exception e) {
                    LOGGER.error(e.getMessage());
                }
            };
            client.indices().createAsync(request,RequestOptions.DEFAULT,listener);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
