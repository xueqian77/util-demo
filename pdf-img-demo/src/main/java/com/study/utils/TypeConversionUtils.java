package com.study.utils;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

/**
 * @program: java-pdf-demo
 * @description: 类型转换Utils
 * @author: 唐嘉
 * @create: 2019-12-19 15:41
 */
public class TypeConversionUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeConversionUtils.class);

    //String转Long
    public static Long StringToLong(String str) {
        try {
            if (str != null && !str.trim().equals("")) {
                return Long.parseLong(str);
            }
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
        return null;
    }

    //Long转String
    public static String LongToString(Long id) {
        try {
            return id.toString();
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
        return null;
    }

    //int转Long
    public static Long IntToLong(int num) {
        try {
            return (long) num;

        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
        return null;
    }

    //Object转Int
    public static int ObjectToInt(Object num) {
        try {
            return Integer.parseInt(num.toString());

        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
        return 0;
    }

    /**
     * listToTree
     * <p>方法说明<p>
     * 将JSONArray数组转为树状结构
     *
     * @param arr   需要转化的数据
     * @param id    数据唯一的标识键值
     * @param pid   父id唯一标识键值
     * @param child 子节点键值
     * @return JSONArray
     */
    public static JSONArray listToTree(JSONArray arr, String id, String ladel, String pid, String child) {
        JSONArray r = new JSONArray();
        JSONObject hash = new JSONObject();
        //将数组转为Object的形式，key为数组中的id
        for (int i = 0; i < arr.size(); i++) {
            JSONObject json = (JSONObject) arr.get(i);
            json.put("value", json.get(id));
            json.put("label", json.get(ladel));
            hash.put(json.get(id).toString(), json);
        }
        //遍历结果集
        for (int j = 0; j < arr.size(); j++) {
            //单条记录
            JSONObject aVal = (JSONObject) arr.get(j);
            //在hash中取出key为单条记录中pid的值
            JSONObject hashVP = (JSONObject) hash.get(aVal.get(pid).toString());
            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if (hashVP != null) {
                //检查是否有child属性
                if (hashVP.get(child) != null) {
                    JSONArray ch = (JSONArray) hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                } else {
                    JSONArray ch = new JSONArray();
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }
            } else {
                r.add(aVal);
            }
        }
        return r;
    }

    /**
     * inputStrem转byte
     *
     * @param inStream
     * @return
     * @throws IOException
     */
    public static final byte[] inputStremTobyte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    //Object转String
    public static String ObjectToString(Object obj) {
        try {
            return obj.toString();
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
        return null;
    }

    //Object转Long
    public static Long ObjectToLong(Object obj) {
        try {
            return Long.valueOf(String.valueOf(obj));
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
        return null;
    }


    //十六进制字节数组转为字符串
    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    //集合转字符串
    public static String ListToString(List list) {
        try {
            StringBuffer str2 = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                String string = list.get(i).toString();
                str2.append("'" + string + "'");
                if (i != list.size() - 1) {
                    str2.append(",");
                }
            }
            return str2.toString();
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
        return null;
    }

    /*替换String中的变量*/
    public static String replace(String mainString, String oldString, String newString) {
        if (mainString == null)
            return null;
        int i = mainString.lastIndexOf(oldString);
        if (i < 0)
            return mainString;
        StringBuffer mainSb = new StringBuffer(mainString);
        for (; i >= 0; i = mainString.lastIndexOf(oldString, i - 1))
            mainSb.replace(i, i + oldString.length(), newString);

        return mainSb.toString();
    }

    /*替换MapLong为String*/
    public static List<Map<String, Object>> mapLongtoMapString(List<Map<String, Object>> mapList) {
        List<Map<String, Object>> newMapList = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            for (String obj : map.keySet()) {
                String key = obj;
                Object value = map.get(obj);
                // 判断取出来的值是否为空
                if (value != null && value instanceof Long) {
                    // 转换为String类型
                    map.put(key, map.get(key) + "");
                }
            }
            newMapList.add(map);
        }
        return newMapList;

    }
}
