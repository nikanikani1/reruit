package com.nika.recruit.service.impl;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nika.recruit.constants.PromptConstant;
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
        System.out.println(deepSeekService.generate("deepseek-r1:1.5b",
                PromptConstant.GET_JOB_SEEKER_SCORE_PROMPT +
                "这是职位要求：\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"title\": \"软件开发工程师\",\n" +
                "        \"description\": \"负责软件的设计、开发和维护工作，要求熟悉Java编程。\",\n" +
                "        \"location\": \"北京\",\n" +
                "        \"salaryRange\": \"15k-25k\",\n" +
                "        \"companyName\": \"ABC科技有限公司\"\n" +
                "    },\n" +
                "这是候选人信息：\n" +
                "[\n" +
                "{\n" +
                "  \"realName\": \"张伟2\",\n" +
                "    \"summary\": \"拥有3年后端开发经验的软件工程师，擅长使用Java和Spring框架构建高性能的企业级应用。\",\n" +
                "    \"experience\": [\n" +
                "      {\n" +
                "        \"company\": \"智汇科技有限公司\",\n" +
                "        \"position\": \"高级软件工程师\",\n" +
                "        \"startDate\": 1527811200000,\n" +
                "        \"endDate\": 1653955200000,\n" +
                "        \"description\": \"负责设计和实现公司核心产品的后端服务，使用Spring Boot和MyBatis优化系统性能，提升响应速度200%。\",\n" +
                "        \"achievements\": [\n" +
                "          \"成功主导并完成了一个关键项目的开发，为公司带来显著的经济效益\",\n" +
                "          \"引入并实施了代码审查流程，提高了团队整体的代码质量\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"company\": \"创想网络有限公司\",\n" +
                "        \"position\": \"初级开发工程师\",\n" +
                "        \"startDate\": 1458000000000,\n" +
                "        \"endDate\": 1527638400000,\n" +
                "        \"description\": \"参与多个项目的后端开发，协助高级工程师进行系统维护和故障排查。\",\n" +
                "        \"achievements\": [\n" +
                "          \"参与开发的公司内部管理系统，提高了工作效率\",\n" +
                "        ]\n" +
                "      }\n" +
                "    ],\n" +
                "    \"education\": [\n" +
                "      {\n" +
                "        \"institution\": \"华中科技大学\",\n" +
                "        \"degree\": \"计算机科学与技术学士\",\n" +
                "        \"startDate\": 1346457600000,\n" +
                "        \"endDate\": 1467244800000,\n" +
                "        \"gpa\": 3.8,\n" +
                "        \"honors\": [\n" +
                "          \"连续三年获得校级奖学金\",\n" +
                "          \"优秀毕业生\"\n" +
                "        ]\n" +
                "      }\n" +
                "    ],\n" +
                "    \"skills\": {\n" +
                "      \"programmingLanguages\": [\n" +
                "        \"Java\",\n" +
                "        \"Python\",\n" +
                "        \"SQL\"\n" +
                "      ],\n" +
                "      \"tools\": [\n" +
                "        \"Git\",\n" +
                "      ],\n" +
                "      \"softSkills\": [\n" +
                "        \"团队协作\",\n" +
                "        \"问题解决\",\n" +
                "        \"沟通能力\"\n" +
                "      ]\n" +
                "    },\n" +
                "},\n" +
                "{\n" +
                "  \"realName\": \"张伟1\",\n" +
                "    \"summary\": \"拥有5年后端开发经验的软件工程师，擅长使用Java和Spring框架构建高性能的企业级应用。\",\n" +
                "    \"experience\": [\n" +
                "      {\n" +
                "        \"company\": \"智汇科技有限公司\",\n" +
                "        \"position\": \"初级软件工程师\",\n" +
                "        \"startDate\": 1527811200000,\n" +
                "        \"endDate\": 1653955200000,\n" +
                "        \"description\": \"负责设计和实现公司核心产品的后端服务，使用Spring Boot和MyBatis优化系统性能，提升响应速度200%。\",\n" +
                "        \"achievements\": [\n" +
                "          \"成功主导并完成了一个关键项目的开发，为公司带来显著的经济效益\",\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"company\": \"创想网络有限公司\",\n" +
                "        \"position\": \"初级开发工程师\",\n" +
                "        \"startDate\": 1458000000000,\n" +
                "        \"endDate\": 1527638400000,\n" +
                "        \"description\": \"参与多个项目的后端开发，协助高级工程师进行系统维护和故障排查。\",\n" +
                "        \"achievements\": [\n" +
                "          \"参与开发的公司内部管理系统，提高了工作效率\",\n" +
                "          \"获得年度最佳新人奖\"\n" +
                "        ]\n" +
                "      }\n" +
                "    ],\n" +
                "    \"education\": [\n" +
                "      {\n" +
                "        \"institution\": \"华中科技大学\",\n" +
                "        \"degree\": \"计算机科学与技术学士\",\n" +
                "        \"startDate\": 1346457600000,\n" +
                "        \"endDate\": 1467244800000,\n" +
                "        \"gpa\": 2.0,\n" +
                "        \"honors\": [\n" +
                "          \"连续三年获得校级奖学金\",\n" +
                "        ]\n" +
                "      }\n" +
                "    ],\n" +
                "    \"skills\": {\n" +
                "      \"programmingLanguages\": [\n" +
                "        \"Java\",\n" +
                "      ],\n" +
                "      \"tools\": [\n" +
                "        \"Git\",\n" +
                "      ],\n" +
                "      \"softSkills\": [\n" +
                "        \"团队协作\",\n" +
                "      ]\n" +
                "    },\n" +
                "},\n" +
                "{\n" +
                        "  \"realName\": \"李娜\",\n" +
                        "  \"summary\": \"具有7年前端开发经验的Web工程师，熟练掌握React和Vue.js框架，能够构建用户友好的交互式Web应用。\",\n" +
                        "  \"experience\": [\n" +
                        "    {\n" +
                        "      \"company\": \"蓝图科技有限公司\",\n" +
                        "      \"position\": \"前端技术负责人\",\n" +
                        "      \"startDate\": \"2017-04-01\",\n" +
                        "      \"endDate\": \"2021-04-01\",\n" +
                        "      \"description\": \"领导前端团队，负责公司多个重点项目的前端开发和维护工作，通过引入Webpack和ES6新特性，优化了开发流程和页面性能。\",\n" +
                        "      \"achievements\": [\n" +
                        "        \"带领团队成功实施了三个大型Web项目的前端开发\",\n" +
                        "        \"通过技术分享和培训，提升了团队整体技术水平\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"company\": \"星河网络有限公司\",\n" +
                        "      \"position\": \"前端开发工程师\",\n" +
                        "      \"startDate\": \"2013-09-01\",\n" +
                        "      \"endDate\": \"2017-03-31\",\n" +
                        "      \"description\": \"负责公司官网及多个产品线的Web前端设计和开发工作，与UI设计师和后端工程师紧密合作，确保产品按时上线。\",\n" +
                        "      \"achievements\": [\n" +
                        "        \"参与开发的产品线获得用户好评，并带来显著的业务增长\",\n" +
                        "        \"被评为年度优秀员工\"\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"education\": [\n" +
                        "    {\n" +
                        "      \"institution\": \"复旦大学\",\n" +
                        "      \"degree\": \"软件工程硕士\",\n" +
                        "      \"startDate\": \"2011-09-01\",\n" +
                        "      \"endDate\": \"2013-06-30\",\n" +
                        "      \"gpa\": 3.75,\n" +
                        "      \"honors\": [\n" +
                        "        \"获得研究生国家奖学金\",\n" +
                        "        \"优秀研究生\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"institution\": \"华东师范大学\",\n" +
                        "      \"degree\": \"计算机科学与技术学士\",\n" +
                        "      \"startDate\": \"2008-09-01\",\n" +
                        "      \"endDate\": \"2012-06-30\",\n" +
                        "      \"gpa\": 3.6,\n" +
                        "      \"honors\": [\n" +
                        "        \"多次获得学业奖学金\",\n" +
                        "        \"计算机科学与技术专业优秀毕业生\"\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"skills\": {\n" +
                        "    \"programmingLanguages\": [\"JavaScript\", \"HTML5\", \"CSS3\"],\n" +
                        "    \"tools\": [\"React\", \"Vue.js\", \"Webpack\", \"npm\"],\n" +
                        "    \"softSkills\": [\"项目管理\", \"团队协作\", \"创新思维\"]\n" +
                        "  }\n" +
                        "}" +
                "]"
        ));
    }
}
