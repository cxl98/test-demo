package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class MapUtil {
    public static String MAP_AK = "Puzsuhx0wmeNTQFoHgfDEf9U";

    public static String MAP_URL = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=" + MAP_AK;

    /**
     * 将经纬度获取解析成详细地址
     *
     * @param lng 经度
     * @param lat 纬度
     * @return
     */
    public static String getAddress(double lng, double lat) {
        String address = "";
        String location = lat + "," + lng;
        BufferedReader in;
        URL url;
        URLConnection connection;
        try {
            url = new URL(MAP_URL + "&location=" + location);
            connection = url.openConnection();
            connection.setDoOutput(true);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String line;
            StringBuilder text = new StringBuilder("");
            while ((line = in.readLine()) != null) {
                text.append(line.trim());
            }
            JSONObject result = JSONObject.parseObject(text.toString());
            if (result != null && result.getIntValue("status") == 0) {
                address = result.getJSONObject("result").getString("formatted_address");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    /**
     * 将地址解析成经纬度
     *
     * @param address 地址，例：福建省莆田市秀屿区
     * @return 返回经纬度数据。例：{"lng":119.1115713003065,"lat":25.32365439756104}
     */
    public static JSONObject getPosition(String address) {
        BufferedReader in;
        URL url;
        URLConnection connection;
        try {
            url = new URL(MAP_URL + "&address=" + address);
            connection = url.openConnection();
            connection.setDoOutput(true);
            in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder text = new StringBuilder("");
            while ((line = in.readLine()) != null) {
                text.append(line.trim());
            }
            JSONObject result = JSONObject.parseObject(text.toString());
            if (result != null && result.getIntValue("status") == 0) {
                return result.getJSONObject("result").getJSONObject("location");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getAddress(119.07773096, 25.44845014));
        System.out.println(getPosition("福建省莆田市秀屿区"));
    }
}
