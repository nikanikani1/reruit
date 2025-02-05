package com.nika.recruit.constants;

/**
 * 通知用的常量
 * @author ht
 */
public interface NotificationConstant {

    /**
     * 提醒简历新增通知
     */
    String NOTIFY_RESUME_FINISH_CONTENT = "欢迎亲爱的小伙伴入驻nika recruitment, 请点击我的简历并完善简历！";

    String NOTIFY_RESUME_FINISH_TITLE = "新人入驻指引";

    /**
     * 简历 新增/修改 完成通知
     */
    String RESUME_FINISH_CONTENT = "您的简历已经修改完成！可以投递简历啦";

    String RESUME_FINISH_TITLE = "简历修改完成通知";


    /**
     * 求职者 投递职位 通知
     */
    String JOB_VOTE_TITLE = "%s向您发布的职位 \"%s\" 发起了投递";
    String JOB_VOTE_CONTENT = "请尽快查看该用户简历信息";
    
}
