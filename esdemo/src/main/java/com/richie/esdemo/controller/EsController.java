package com.richie.esdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.richie.esdemo.esentiry.EsModel;
import com.richie.esdemo.esentiry.EsPage;
import com.richie.esdemo.utils.ElasticsearchUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * demo
 * <p>
 * Created by lylaut on 2019-05-23
 */
@RestController
@RequestMapping("/es")
@Api("es controller")
public class EsController {


    /**
     * 类型
     */
    private String esType = "external";

    /**
     * http://127.0.0.1:8080/es/createIndex
     * 创建索引
     *
     * @return
     */
    @ApiOperation(value = "创建索引")
    @RequestMapping(value = "/createIndex", method = RequestMethod.GET)
    public Object createIndex(@RequestParam(name = "index") String indexName) {
        HashMap<String, Object> map = new HashMap<>();
        if (!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        } else {
            map.put("message", "索引已经存在");
            return map;
        }
        return "索引创建成功";
    }

    /**
     * 插入记录
     *
     * @return
     */
    @ApiOperation(value = "插入记录")
    @RequestMapping(value = "/insertJson", method = RequestMethod.GET)
    public String insertJson(@RequestParam(name = "index") String indexName, @RequestParam(name = "id") String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("age", 25);
        jsonObject.put("name", "j-" + new Random(100).nextInt());
        jsonObject.put("date", new Date());
        String idres = ElasticsearchUtil.addData(jsonObject, indexName, esType, jsonObject.getString("id"));
        return idres;
    }

    /**
     * 插入记录
     *
     * @return
     */
    @ApiOperation(value = "插入记录")
    @RequestMapping(value = "/insertModel", method = RequestMethod.GET)
    public String insertModel(@RequestParam(name = "index") String indexName, @RequestParam(name = "id") String id) {
        EsModel esModel = new EsModel();
        esModel.setId(id);
        esModel.setName("m-" + new Random(100).nextInt());
        esModel.setAge(30);
        esModel.setDate(new Date());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(esModel);
        String idres = ElasticsearchUtil.addData(jsonObject, indexName, esType, jsonObject.getString("id"));
        return idres;
    }

    /**
     * 删除记录
     *
     * @return
     */
    @ApiOperation(value = "删除记录")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "index") String indexName, @RequestParam String id) {
        if (StringUtils.isNotBlank(id)) {
            ElasticsearchUtil.deleteDataById(indexName, esType, id);
            return "删除id=" + id;
        } else {
            return "id为空";
        }
    }

    /**
     * 更新数据
     *
     * @return
     */
    @ApiOperation(value = "更新数据")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam(name = "index") String indexName, @RequestParam String id) {
        if (StringUtils.isNotBlank(id)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("age", 31);
            jsonObject.put("name", "修改");
            jsonObject.put("date", new Date());
            ElasticsearchUtil.updateDataById(jsonObject, indexName, esType, id);
            return "id=" + id;
        } else {
            return "id为空";
        }
    }

    /**
     * 获取数据
     * http://127.0.0.1:8080/es/getData?id=2018-04-25%2016:33:44
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取数据")
    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    public Object getData(@RequestParam(name = "index") String indexName, @RequestParam String id) {
        if (StringUtils.isNotBlank(id)) {
            Map<String, Object> map = ElasticsearchUtil.searchDataById(indexName, esType, id, null);
            return map;
        } else {
            return "id为空";
        }
    }

    /**
     * 查询数据
     * 模糊查询
     *
     * @return
     */
    @ApiOperation(value = "查询数据")
    @RequestMapping(value = "/queryMatchData", method = RequestMethod.GET)
    public String queryMatchData(@RequestParam(name = "index") String indexName) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolean matchPhrase = false;
        if (matchPhrase == Boolean.TRUE) {
            //不进行分词搜索
            boolQuery.must(QueryBuilders.matchPhraseQuery("name", "m"));
        } else {
            boolQuery.must(QueryBuilders.matchQuery("name", "m-m"));
        }
        List<Map<String, Object>> list = ElasticsearchUtil.
                searchListData(indexName, esType, boolQuery, 10, "name", null, "name");
        return JSONObject.toJSONString(list);
    }

    /**
     * 通配符查询数据
     * 通配符查询 ?用来匹配1个任意字符，*用来匹配零个或者多个字符
     *
     * @return
     */
    @ApiOperation(value = "通配符查询数据")
    @RequestMapping(value = "/queryWildcardData", method = RequestMethod.GET)
    public String queryWildcardData(@RequestParam(name = "index") String indexName) {
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name.keyword", "j-?466");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 正则查询
     *
     * @return
     */
    @ApiOperation(value = "正则查询")
    @RequestMapping(value = "/queryRegexpData", method = RequestMethod.GET)
    public String queryRegexpData(@RequestParam(name = "index") String indexName) {
        QueryBuilder queryBuilder = QueryBuilders.regexpQuery("name.keyword", "m--[0-9]{1,11}");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询数字范围数据
     *
     * @return
     */
    @ApiOperation(value = "查询数字范围数据")
    @RequestMapping(value = "/queryIntRangeData", method = RequestMethod.GET)
    public String queryIntRangeData(@RequestParam(name = "index") String indexName) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").from(21)
                .to(25));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询日期范围数据
     *
     * @return
     */
    @ApiOperation(value = "查询日期范围数据")
    @RequestMapping(value = "/queryDateRangeData", method = RequestMethod.GET)
    public String queryDateRangeData(@RequestParam(name = "index") String indexName) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("date").from("2018-04-25T08:33:44.840Z")
                .to("2019-04-25T10:03:08.081Z"));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询分页
     *
     * @param startPage 第几条记录开始
     *                  从0开始
     *                  第1页 ：http://127.0.0.1:8080/es/queryPage?startPage=0&pageSize=2
     *                  第2页 ：http://127.0.0.1:8080/es/queryPage?startPage=2&pageSize=2
     * @param pageSize  每页大小
     * @return
     */
    @ApiOperation(value = "查询分页")
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public String queryPage(@RequestParam(name = "index") String indexName, @RequestParam(name = "start") String startPage, @RequestParam(name = "size") String pageSize) {
        if (StringUtils.isNotBlank(startPage) && StringUtils.isNotBlank(pageSize)) {
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            boolQuery.must(QueryBuilders.rangeQuery("date").from("2018-04-25T08:33:44.840Z")
                    .to("2019-04-25T10:03:08.081Z"));
            EsPage list = ElasticsearchUtil.searchDataPage(indexName, esType, Integer.parseInt(startPage), Integer.parseInt(pageSize), boolQuery, null, null, null);
            return JSONObject.toJSONString(list);
        } else {
            return "startPage或者pageSize缺失";
        }
    }
}

