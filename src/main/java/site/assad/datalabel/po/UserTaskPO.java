package site.assad.datalabel.po;

import java.util.Date;

/**
 * @author Al-assad
 * @since 2019/3/22
 * created by Intellij-IDEA
 */
public class UserTaskPO {
    
    private String id;
    private String userId;
    private String formId;
    private String orgId;
    private Integer taskStatus;
    private Integer dataSourceSort;
    private Date createTime;
    
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
    
    public String getOrgId() {
        return orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    
    public Integer getTaskStatus() {
        return taskStatus;
    }
    
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }
    
    public Integer getDataSourceSort() {
        return dataSourceSort;
    }
    
    public void setDataSourceSort(Integer dataSourceSort) {
        this.dataSourceSort = dataSourceSort;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
