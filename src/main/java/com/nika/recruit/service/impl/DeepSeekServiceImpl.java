package com.nika.recruit.service.impl;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nika.recruit.service.DeepSeekService;

/**
 * @author ht
 * 本地deepseek模型测试
 */
public class DeepSeekServiceImpl implements DeepSeekService {




    @Override
    public String generate(String model,String prompt) {

        // 设置请求的URL
        String url = "http://localhost:11434/api/generate";

        // 创建请求体JSON对象
        JSONObject jsonBody = new JSONObject();
        jsonBody.set("model", model);
        jsonBody.set("prompt", prompt);
        jsonBody.set("stream", false);

        // 发送POST请求
        HttpResponse response = HttpRequest.post(url)
                .header("Content-Type", ContentType.JSON.getValue())
                .body(jsonBody.toStringPretty())
                .execute();

        // 检查响应并提取返回的内容
        if (response.isOk()) {
            String responseBody = response.body();
            JSONObject jsonResponse = JSONUtil.parseObj(responseBody);
            return jsonResponse.getStr("response", "");
        }
        return "";

    }

    public static void main(String[] args) {
        DeepSeekServiceImpl deepSeekService = new DeepSeekServiceImpl();
        System.out.println(deepSeekService.generate("deepseek-r1:1.5b","你好,你是什么?"));
    }
}
