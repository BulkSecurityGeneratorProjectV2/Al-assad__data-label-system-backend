package site.assad.datalabel.vo;

import site.assad.datalabel.po.FormPO;
import site.assad.datalabel.util.DateUtil;

import java.util.Date;

import static site.assad.datalabel.util.ConstantUtil.*;

public class FormTemplateVO {
    
    private String templateId;
    private String title;
    private String description;
    private String type;
    private String itemContent;
    private String createTime;
    
    public static FormTemplateVO of (FormPO po){
        FormTemplateVO vo = new FormTemplateVO();
        if (po == null) {
            return vo;
        }
        vo.setTemplateId(po.getFormId());
        vo.setTitle(po.getTitle());
        vo.setDescription(po.getDescription());
        if (FORM_TYPE_TEXT.equals(po.getType())) {
            vo.setType("文本型");
        } else if (FORM_TYPE_PICTURE.equals(po.getType())) {
            vo.setType("图片型");
        } else if (FORM_TYPE_VOICE.equals(po.getType())) {
            vo.setType("音频型");
        } else {
            vo.setType("未知类型");
        }
        vo.setItemContent(po.getItemContent());
        vo.setCreateTime(DateUtil.formatToDate(po.getCreateTime()));
        return vo;
    }
    
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
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
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
}
