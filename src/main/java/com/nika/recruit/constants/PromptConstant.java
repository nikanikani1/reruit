package com.nika.recruit.constants;

/**
 * @author ht
 */
public interface PromptConstant {


    String GET_JOB_SEEKER_SCORE_PROMPT = "下面我会给你一份职位要求和数份候选人简历信息，请你根据所给信息，输出以下json格式。每位候选人的score上线为100分，下限0分,和职位要求约接近分数越高,每位候选人的分数不得一致。无需其它信息：\n" +
            "[\n" +
            "{\n" +
            "   realName:\"x\",\n" +
            "   score:\"x\", \n" +
            "   reason:\"x\"\n" +
            "}\n" +
            "]\n" +
            "\n";

}
