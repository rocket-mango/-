package com.demogroup.demoweb.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MakeJsonUtil {
    public static JSONObject makeJoinJson(String username, String password) throws ParseException {
        String jsonStr="{\"username\":\""+username+"\", \"password\":\""+password+"\"}";
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonStr);

        return jsonObject;

    }
}
