package site.assad.datalabel.util;

public class FormUtil {
    
    /**
     * 获取表单图片链接
     * @param type
     * @return
     */
    public static String getFormImagePath(Integer type){
        switch (type) {
            case 1:
                return "https://ws2.sinaimg.cn/large/006tKfTcly1g1hrpn5jogj302o02ot8o.jpg";
            case 2:
                return "https://ws2.sinaimg.cn/large/006tKfTcly1g1hrqbh6dnj302k02nq2q.jpg";
            case 3:
                return "https://ws2.sinaimg.cn/large/006tKfTcly1g1j49o5e9mj302o02odfo.jpg";
            default:
                return "https://ws2.sinaimg.cn/large/006tKfTcly1g1hrpn5jogj302o02ot8o.jpg";
        }
    }
    
    /**
     * 获取表单类型问文本
     * @param type
     * @return
     */
    public static String getTypeText(Integer type){
        switch (type) {
            case 1 : return "文本型";
            case 2 : return "图片型";
            case 3 : return "音频型";
            default: return "其他类型";
        }
    }
    
    /**
     * 获取字段类型文本
     * @param itemType
     * @return
     */
    public static String getItemTypeText(Integer itemType){
        switch (itemType) {
            case 1 : return "填写型";
            case 2 : return "选择型";
            default: return "其他类型";
        }
    }
}
