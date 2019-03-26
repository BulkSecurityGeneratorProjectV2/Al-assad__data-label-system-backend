package site.assad.datalabel.vo;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import site.assad.datalabel.po.FormPO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static site.assad.datalabel.util.ConstantUtil.ITEM_TYPE_RADIO;

/**
 *
 * @author Al-assad
 * @since 2019/3/24
 * created by Intellij-IDEA
 */
public class FormVO {
    
    private String formId;
    private String orgId;
    private String title;
    private String description;
    private int curNum;
    private int limitNum;
    private int sourceCount;
    private Integer formStatus;
    private Integer type;
    private Integer itemType;
    private String itemContent;
    private String itemTags;
    private Date createTime;
    
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
    
    public Integer getFormStatus() {
        return formStatus;
    }
    
    public void setFormStatus(Integer formStatus) {
        this.formStatus = formStatus;
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
    
    public String getItemTags() {
        return itemTags;
    }
    
    public void setItemTags(String itemTags) {
        this.itemTags = itemTags;
    }
    
    public List<String> getItemOption(){
        String tags = getItemTags();
        if (StringUtils.isEmpty(tags)) {
            return new ArrayList<>(0);
        }
        String[] tagArray = tags.trim().split(",");
        if (ArrayUtils.isEmpty(tagArray)) {
            return new ArrayList<>(0);
        }
        return Arrays.asList(tagArray);
    }
    /**
     * 发布参数校验
     */
    public static boolean publishValid(FormVO vo){
        if (vo == null){
            return false;
        }
        if (StringUtils.isEmpty(vo.getTitle()) || StringUtils.isEmpty(vo.getDescription()) || StringUtils.isEmpty(vo.getItemContent())
                || vo.getLimitNum() == 0 || vo.getItemType() == null || vo.getType() == null) {
            return false;
        }
        if (ITEM_TYPE_RADIO.equals(vo.getItemType()) && CollectionUtils.isEmpty(vo.getItemOption())) {
            return false;
        }
        if (vo.getLimitNum() < 1) {
            return false;
        }
        return true;
    }
    
}
