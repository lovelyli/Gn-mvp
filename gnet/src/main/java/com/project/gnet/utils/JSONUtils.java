package com.project.gnet.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class JSONUtils {

    /**
     * 把Java对象转换为JSON数据格式
     *
     * @param object
     * @return
     */
    public static String getJson(Object object) {
        try {
//            return JSON.toJSONString(object);
            return JSON.toJSONString(object);
        } catch (Exception e) {
            // logger.error("[JsonUtils]Fail to getjson", e);
        }
        return null;
    }

    private static boolean checkMap(Map map){
        return map.containsKey("sentences") && map.containsKey("format");
    }

    private static String getJsonForUpload(Map object){
        String wmJson = map2Json((Map<String, ? extends Object>) object);
        LogUtils.i("wmJson === " + wmJson);
        return wmJson;
    }
    private static SerializeConfig config = new SerializeConfig();
    /**
     * QuoteFieldNames———-输出key时是否使用双引号,默认为true
     * WriteMapNullValue———-是否输出值为null的字段,默认为false
     * WriteNullNumberAsZero———-数值字段如果为null,输出为0,而非null
     * WriteNullListAsEmpty———-List字段如果为null,输出为[],而非null
     * WriteNullStringAsEmpty———-字符类型字段如果为null,输出为”“,而非null
     * WriteNullBooleanAsFalse———-Boolean字段如果为null,输出为false,而非null
     * WriteDateUseDateFormat–———-日期格式,默认为false
     *
     * @param map
     * @return
     */
    public static String map2Json(Map<String, ? extends Object> map) {
        return JSON.toJSONString(map, config, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteMapNullValue, SerializerFeature.NotWriteDefaultValue,
                SerializerFeature.SortField, SerializerFeature.WriteNonStringKeyAsString);
    }

    /**
     * 把Java对象转换为JSON数据格式
     *
     * @param object
     * @return
     */
    public static String getJsonStr(Object object) {
        if (object instanceof Map) {
            if (checkMap((Map) object)) {
                return getJsonForUpload((Map) object);
            }
            JSONObject obj = new JSONObject((Map) object);
            LogUtils.i("obj === " + obj.toString());
            return obj.toString();
        }
        return null;
    }

    /**
     * 把JSON数据格式转换为JAVA对象
     *
     * @param <T>
     * @param jsonData
     * @param clz
     * @return
     */
    public static <T> T readValue(String jsonData, Class<T> clz) {
        try {
            return JSON.parseObject(jsonData, clz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把JSON数据格式转换为JAVA对象
     *
     * @param <T>
     * @param jsonData
     * @param clz
     * @return
     */
    public static <T> List<T> readListValue(String jsonData, Class<T> clz) {
        try {
            return JSON.parseArray(jsonData, clz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据参数,值封装成 json字符串
     *
     * @param title   json头
     * @param params  键
     * @param params2 值
     * @return json字符串
     * @throws JSONException json封装不匹配
     */
    public static String createJSON(String title, String[] params, Object[] params2) throws JSONException {
        JSONObject titleNode = new JSONObject();
        JSONObject paramsNode = new JSONObject();
        LogUtils.d("params 长度:" + params.length);
        LogUtils.d("params2 长度:" + params2.length);

        for (int i = 0; i < params.length; i++) {
            paramsNode.put(params[i], params2[i]);
        }
        titleNode.put(title, paramsNode);
        return titleNode.toString();
    }

}
