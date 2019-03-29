package site.assad.datalabel.po;

import site.assad.datalabel.vo.FormInstanceParamVO;

import java.util.Date;
import java.util.UUID;

public class FormInstancePO {
    
    private String id;
    private String userId;
    private String formId;
    private String dataSourceId;
    private String itemContent;
    private Date createTime;
    
    public static FormInstancePO of(String formId, String userId, String dataSourceId, String content){
        FormInstancePO po = new FormInstancePO();
        po.setId(UUID.randomUUID().toString());
        po.setDataSourceId(dataSourceId);
        po.setFormId(formId);
        po.setUserId(userId);
        po.setItemContent(content);
        po.setCreateTime(new Date());
        return po;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getFormId() {
        return formId;
    }
    
    public void setFormId(String formId) {
        this.formId = formId;
    }
    
    public String getDataSourceId() {
        return dataSourceId;
    }
    
    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
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
