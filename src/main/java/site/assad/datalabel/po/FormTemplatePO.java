package site.assad.datalabel.po;

import java.util.Date;

/**
 *
 * @author Al-assad
 * @since 2019/3/22
 * created by Intellij-IDEA
 */
public class FormTemplatePO {
    
    private String templateId;
    private String title;
    private String description;
    private Integer type;
    private Integer itemType;
    private String itemContent;
    private Date createTime;
    
    public String getTemplateId() {
        return templateId;
    }
    
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public Integer getItemType() {
        return itemType;
    }
    
    public void setItemType(Integer itemType) {
        this.itemType = itemType;
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
