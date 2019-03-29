package site.assad.datalabel.vo;

import site.assad.datalabel.po.FormPO;
import site.assad.datalabel.po.UserTaskPO;
import site.assad.datalabel.util.DateUtil;
import site.assad.datalabel.util.FormUtil;

import java.text.DecimalFormat;

/**
 *
 * @author Al-assad
 * @since 2019/3/29
 * created by Intellij-IDEA
 */
public class WXTaskVO {
    
    private String taskId;
    private String formId;
    private String orgId;
    private String title;
    private int dataSourceSort;
    private String finishPrecent;
    private int sourceCount;
    private String type;
    private String itemType;
    private String itemContent;
    private String imagePath;
    private String orgName;
    
    public static WXTaskVO of(UserTaskPO taskPO, FormPO formPO){
        WXTaskVO taskVO = new WXTaskVO();
        if (taskPO == null || formPO == null){
            return taskVO;
        }
        taskVO.setTaskId(taskPO.getId());
        taskVO.setDataSourceSort(taskPO.getDataSourceSort());
        taskVO.setFormId(formPO.getFormId());
        taskVO.setOrgId(formPO.getOrgId());
        taskVO.setTitle(formPO.getTitle());
        taskVO.setSourceCount(formPO.getSourceCount());
        taskVO.setType(FormUtil.getTypeText(formPO.getType()));
        taskVO.setItemContent(formPO.getItemContent());
        taskVO.setItemType(FormUtil.getItemTypeText(formPO.getItemType()));
        taskVO.setImagePath(FormUtil.getFormImagePath(formPO.getType()));
        DecimalFormat df = new DecimalFormat("0");
        taskVO.setFinishPrecent(df.format((double)(taskPO.getDataSourceSort()) / formPO.getSourceCount() * 100) + " %");
        return taskVO;
    }
    
    public String getTaskId() {
        return taskId;
    }
    
    public void setTaskId(String taskId) {
        this.taskId = taskId;
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
    
    public int getDataSourceSort() {
        return dataSourceSort;
    }
    
    public void setDataSourceSort(int dataSourceSort) {
        this.dataSourceSort = dataSourceSort;
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
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public String getOrgName() {
        return orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
    public String getFinishPrecent() {
        return finishPrecent;
    }
    
    public void setFinishPrecent(String finishPrecent) {
        this.finishPrecent = finishPrecent;
    }
}
