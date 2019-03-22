package site.assad.datalabel.po;

import java.util.Date;

/**
 * @author Al-assad
 * @since 2019/3/22
 * created by Intellij-IDEA
 */
public class WxUserPO {
    
    private String userId;
    private String wxId;
    private String wxName;
    private Date createTime;
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getWxId() {
        return wxId;
    }
    
    public void setWxId(String wxId) {
        this.wxId = wxId;
    }
    
    public String getWxName() {
        return wxName;
    }
    
    public void setWxName(String wxName) {
        this.wxName = wxName;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
