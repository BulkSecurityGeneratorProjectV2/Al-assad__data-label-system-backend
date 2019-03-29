package site.assad.datalabel.vo;

import site.assad.datalabel.po.FormPO;
import site.assad.datalabel.util.DateUtil;
import site.assad.datalabel.util.FormUtil;

public class WXFormVO {
    
    private String formId;
    private String orgId;
    private String title;
    private String description;
    private int curNum;
    private int limitNum;
    private int sourceCount;
    private String type;
    private String itemType;
    private Integer itemTypeIdx;
    private Integer typeIdx;
    private String itemContent;
    private String createTime;
    private String imagePath;
    private String orgName;
    
    public static WXFormVO of(FormPO po) {
        WXFormVO vo = new WXFormVO();
        if (po == null) {
            return vo;
        }
        vo.setFormId(po.getFormId());
        vo.setOrgId(po.getOrgId());
        vo.setTitle(po.getTitle());
        vo.setDescription(po.getDescription());
        vo.setCurNum(po.getCurNum());
        vo.setLimitNum(po.getLimitNum());
        vo.setSourceCount(po.getSourceCount());
        vo.setTypeIdx(po.getType());
        vo.setItemTypeIdx(po.getItemType());
        vo.setType(FormUtil.getTypeText(po.getType()));
        vo.setItemType(FormUtil.getItemTypeText(po.getItemType()));
        vo.setItemContent(po.getItemContent());
        vo.setCreateTime(DateUtil.formatToDate(po.getCreateTime()));
        vo.setImagePath(FormUtil.getFormImagePath(po.getType()));
        return vo;
    }
    
    public String getOrgName() {
        return orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
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
    
    public int getCurNum() {
        return curNum;
    }
    
    public void setCurNum(int curNum) {
        this.curNum = curNum;
    }
    
    public int getLimitNum() {
        return limitNum;
    }
    
    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }
    
    public int getSourceCount() {
        return sourceCount;
    }
    
    public void setSourceCount(int sourceCount) {
        this.sourceCount = sourceCount;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getItemType() {
        return itemType;
    }
    
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    
    public String getItemContent() {
        return itemContent;
    }
    
    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }
    
    public String getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public Integer getItemTypeIdx() {
        return itemTypeIdx;
    }
    
    public void setItemTypeIdx(Integer itemTypeIdx) {
        this.itemTypeIdx = itemTypeIdx;
    }
    
    public Integer getTypeIdx() {
        return typeIdx;
    }
    
    public void setTypeIdx(Integer typeIdx) {
        this.typeIdx = typeIdx;
    }
}
