package com.nika.recruit.constants;

/**
 * @author ht
 */
public interface PromptConstant {


    String GET_JOB_SEEKER_SCORE_PROMPT = "下面我会给你一份职位要求和数份候选人简历信息，请你根据所给信息，输出以下json。每位候选人的score为0-100分,若满足职位要求则为60分，若有其它亮眼的技能则可以加分，每位候选人的分数不得一致。无需其它信息：\n" +
            "[\n" +
            "{\n" +
            "   realName:\"x\",\n" +
            "   score:\"x\", \n" +
            "   reason:\"x\"\n" +
            "}\n" +
            "]\n" +
            "\n";

}
