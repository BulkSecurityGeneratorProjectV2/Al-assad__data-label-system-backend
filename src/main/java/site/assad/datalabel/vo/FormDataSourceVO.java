package site.assad.datalabel.vo;

import site.assad.datalabel.po.FormDataSourcePO;
import site.assad.datalabel.util.DateUtil;

import java.util.Date;

import static site.assad.datalabel.util.ConstantUtil.*;

public class FormDataSourceVO {
    private String dataSourceId;
    private String formId;
    private String dataType;
    private String content;
    private String path;
    private int sort;
    private String createTime;
    
    /**
     * 快捷转化方法
     **/
    public static FormDataSourceVO of(FormDataSourcePO po) {
        FormDataSourceVO vo = new FormDataSourceVO();
        if (po == null) {
            return vo;
        }
        vo.setDataSourceId(po.getDataSourceId());
        vo.setFormId(po.getFormId());
        if (FORM_TYPE_TEXT.equals(po.getDataType())) {
            vo.setDataType("文本");
        } else if (FORM_TYPE_PICTURE.equals(po.getDataType())) {
            vo.setDataType("图片");
        } else if (FORM_TYPE_VOICE.equals(po.getDataType())) {
            vo.setDataType("音频");
        } else {
            vo.setDataType("未知");
        }
        vo.setContent(po.getContent());
        vo.setPath(po.getPath());
        vo.setSort(po.getSort());
        vo.setCreateTime(DateUtil.formatToDateTime(po.getCreateTime()));
        return vo;
    }
    
    public String getDataSourceId() {
        return dataSourceId;
    }
    
    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }
    
    public String getFormId() {
        return formId;
    }
    
    public void setFormId(String formId) {
        this.formId = formId;
    }
    
    public String getDataType() {
        return dataType;
    }
    
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public int getSort() {
        return sort;
    }
    
    public void setSort(int sort) {
        this.sort = sort;
    }
    
    public String getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
