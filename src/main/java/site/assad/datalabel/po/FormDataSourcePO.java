package site.assad.datalabel.po;

import java.util.Date;

/**
 *
 * @author Al-assad
 * @since 2019/3/22
 * created by Intellij-IDEA
 */
public class FormDataSourcePO {
    
    private String dataSourceId;
    private String formId;
    private Integer dataType;
    private String content;
    private String path;
    private int sort;
    private Date createTime;
    
    public String getDataSourceId() {
        return dataSourceId;
    }
    
    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }
    
    public String getFormId() {
        return formId;
    }
    
    public void setFormId(String formId) {
        this.formId = formId;
    }
    
    public Integer getDataType() {
        return dataType;
    }
    
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public int getSort() {
        return sort;
    }
    
    public void setSort(int sort) {
        this.sort = sort;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}
