package site.assad.datalabel.vo;

import site.assad.datalabel.po.FormDataSourcePO;

public class WXDataSourceVO {
    
    private String dataSourceId;
    private String dataType;
    /**
     * 文件路径/文本
     */
    private String content;
    private int sort;
    
    
    public static WXDataSourceVO of (FormDataSourcePO po) {
        if (po == null) {
            return null;
        }
        WXDataSourceVO vo = new WXDataSourceVO();
        //TODO
        
        
        return vo;
        
    }
    public String getDataSourceId() {
        return dataSourceId;
    }
    
    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }
    
    public String getDataType() {
        return dataType;
    }
    
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public int getSort() {
        return sort;
    }
    
    public void setSort(int sort) {
        this.sort = sort;
    }
}
