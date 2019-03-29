package site.assad.datalabel.util;

/**
 * 常量工具类
 * @author Al-assad
 * @since 2019/3/22
 * created by Intellij-IDEA
 */
public class ConstantUtil {
    
    /**
     * 用户任务完成状态：未进行
     */
    public final static Integer TASK_STATUS_UN_START = 1;
    /**
     * 用户任务完成状态：进行中
     */
    public final static Integer TASK_STATUS_STARTING = 2;
    /**
     * 用户任务完成状态：已完成
     */
    public final static Integer TASK_STATUS_FINISH = 3;
    
    
    /**
     * 表单类型：文本标注
     */
    public final static Integer FORM_TYPE_TEXT = 1;
    /**
     * 表单类型：图片标注
     */
    public final static Integer FORM_TYPE_PICTURE = 2;
    /**
     * 表单类型：音频标注
     */
    public final static Integer FORM_TYPE_VOICE = 3;
    /**
     *
     */
    public final static Integer FORM_TYPE_ALL = 0;
    
    
    /**
     * 字段类型：填写型
     */
    public final static Integer ITEM_TYPE_TEXT = 1;
    /**
     * 字段类型：选择型
     */
    public final static Integer ITEM_TYPE_RADIO = 2;
    
    
    /**
     * 表单状态：草稿
     */
    public final static Integer FORM_STATUS_DRAFT = 0;
    /**
     * 表单状态：发布
     */
    public final static Integer FORM_STATUS_PUBISH = 1;
    /**
     * 表单状态：完成
     */
    public final static Integer FORM_STATUS_FINISH = 2;
    
    /**
     * 缺省orgId
     */
    public final static String ORG_ID_TEMPLATE = "template";
    
    /**
     * 消息等级：成功
     */
    public final static Integer MSG_SUCCESS = 1;
    
    /**
     * 消息等级：信息
     */
    public final static Integer MSG_INFO = 2;
    
    /**
     * 消息等级：提醒
     */
    public final static Integer MSG_NOTIFY = 3;
    
 
    
}
