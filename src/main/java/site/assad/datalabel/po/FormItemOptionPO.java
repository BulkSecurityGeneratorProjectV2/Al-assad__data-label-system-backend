package site.assad.datalabel.po;

import java.util.Date;

/**
 *
 * @author Al-assad
 * @since 2019/3/22
 * created by Intellij-IDEA
 */
public class FormItemOptionPO {
    
    private String itemOptionId;
    private String formId;
    private String content;
    private int sort;
    private Date createTime;
    
    public String getItemOptionId() {
        return itemOptionId;
    }
    
    public void setItemOptionId(String itemOptionId) {
        this.itemOptionId = itemOptionId;
    }
    
    public String getFormId() {
        return formId;
    }
    
    public void setFormId(String formId) {
        this.formId = formId;
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
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
