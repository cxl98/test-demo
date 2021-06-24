package com.cxl.es.bulk;

import com.cxl.es.util.InitEs;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author cxl
 */
public class BulkDemo {
    private static final Logger LOGGER= LoggerFactory.getLogger(BulkDemo.class);

    public void bulk(){
        try(RestHighLevelClient client= InitEs.getClient()) {
            //1.创建批量操作请求
            BulkRequest request=new BulkRequest();
            request.add(new IndexRequest("mess","_doc","1")
                    .source(XContentType.JSON,"field","foo"));

            //2.可选的设置
            request.timeout("2m");
            request.setRefreshPolicy("wait_for");
            request.waitForActiveShards(2);

            //3.发送请求

            //(1)同步
            BulkResponse bulkItemResponses=client.bulk(request, RequestOptions.DEFAULT);

            //(2)异步
            ActionListener<BulkResponse> listener=new ActionListener<BulkResponse>() {
                @Override
                public void onResponse(BulkResponse bulkItemResponses) {

                }

                @Override
                public void onFailure(Exception e) {

                }
            };
            client.bulkAsync(request,RequestOptions.DEFAULT,listener);

            //4.处理响应
            if (bulkItemResponses != null) {
                for (BulkItemResponse bulkResponses: bulkItemResponses) {
                    DocWriteResponse docWriteResponse=bulkResponses.getResponse();

                    if (bulkResponses.getOpType() == DocWriteRequest.OpType.INDEX||bulkResponses.getOpType()==DocWriteRequest.OpType.CREATE) {
                        IndexResponse indexResponse= (IndexResponse) docWriteResponse;
                        //TODO 新增成功的处理
                    }else if (bulkResponses.getOpType() == DocWriteRequest.OpType.UPDATE) {
                        UpdateResponse updateResponse= (UpdateResponse) docWriteResponse;
                        //TODO 修改成功的处理
                    }else if (bulkResponses.getOpType()==DocWriteRequest.OpType.DELETE){
                        DeleteResponse deleteResponse= (DeleteResponse) docWriteResponse;
                        //TODO 删除成功的处理

                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
