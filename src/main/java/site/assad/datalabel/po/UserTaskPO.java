package site.assad.datalabel.po;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.UUID;

import static site.assad.datalabel.util.ConstantUtil.TASK_STATUS_UN_START;

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
    
    public static UserTaskPO getInitPO(@NonNull String formId,@NonNull String userId, @NonNull String orgId){
        UserTaskPO po = new UserTaskPO();
        po.setId(UUID.randomUUID().toString());
        po.setFormId(formId);
        po.setUserId(userId);
        po.setOrgId(orgId);
        po.setTaskStatus(TASK_STATUS_UN_START);
        po.setDataSourceSort(0);
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
