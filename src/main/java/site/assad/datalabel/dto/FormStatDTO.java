package site.assad.datalabel.dto;

import java.util.Date;

public class FormStatDTO {
    
    /**
     * 表单标题
     */
    private String formTitle;
    /**
     * 数据源描述，文本/文件path
     */
    private String dataSourceContent;
    /**
     * 数据源字段
     */
    private String itemContent;
    /**
     * 产生时间
     */
    private Date createTime;
    
    public String getFormTitle() {
        return formTitle;
    }
    
    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }
    
    public String getDataSourceContent() {
        return dataSourceContent;
    }
    
    public void setDataSourceContent(String dataSourceContent) {
        this.dataSourceContent = dataSourceContent;
    }
    
    public String getItemContent() {
        return itemContent;
    }
    
    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
