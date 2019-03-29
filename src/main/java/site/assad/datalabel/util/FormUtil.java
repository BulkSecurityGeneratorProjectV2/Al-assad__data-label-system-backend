package site.assad.datalabel.util;

import org.apache.commons.lang3.StringUtils;

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
                return "https://ws3.sinaimg.cn/large/006tKfTcgy1g1jy4yy06oj302o02oweg.jpg";
            case 3:
                return "https://ws4.sinaimg.cn/large/006tKfTcgy1g1jy57t86dj302o02owef.jpg";
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
    
    /**
     * 获取资源真实路径
     */
    public static String getAssertUrl(String url){
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        if (url.startsWith(".")){
            url = url.substring(1);
        }
        return ConfUtil.BASE_URL + url;
    }
    
}
