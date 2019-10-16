package cn.itcast;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

public class EsManager {
//    准备客户端
    private TransportClient client = null;

    @Before
    public void init() throws Exception{
//        初始化客户端，此代码是官网提供
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
    }

    @Test
    public void testSearch(){
        SearchResponse response = client.prepareSearch("heima") //指定索引库
//                .setQuery(QueryBuilders.termQuery("goodsName", "手机"))// Query设置查询方式-term
//                .setQuery(QueryBuilders.matchAllQuery())// Query设置查询方式-查询所有
//                .setQuery(QueryBuilders.matchQuery("goodsName","小米手机"))// Query设置查询方式-分词查询
//                .setQuery(QueryBuilders.wildcardQuery("goodsName","*手*"))// Query设置查询方式-模糊查询
//                .setQuery(QueryBuilders.fuzzyQuery("goodsName", "大米").fuzziness(Fuzziness.ONE))// Query设置查询方式-容错查询

//                .setQuery(QueryBuilders.rangeQuery("price").gte(1000).lte(5000))// Query设置查询方式-区间查询
                .setQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("goodsName", "手机"))
                                                   .mustNot(QueryBuilders.rangeQuery("price").gte(1000).lte(5000))) //组合查询
//                .setPostFilter(QueryBuilders.rangeQuery("price").from(1000).to(5000))     //过滤查询
                .setFrom(0).setSize(60)  //分页
                .get();  //执行查询
        SearchHits searchHits = response.getHits();
        System.out.println("查询总条数"+searchHits.getTotalHits());
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }


//    客户端关闭
    @After
    public void end(){
        client.close();
    }



}
