package site.assad.datalabel.vo;

import site.assad.datalabel.po.FormDataSourcePO;
import site.assad.datalabel.util.FormUtil;

import static site.assad.datalabel.util.ConstantUtil.FORM_TYPE_TEXT;

public class WXDataSourceVO {
    
    private String dataSourceId;
    private Integer dataType;
    /**
     * 文件路径/文本
     */
    private String content;
    private int sort;
    
    public static WXDataSourceVO of (FormDataSourcePO po) {
        if (po == null) {
            return null;
        }
        WXDataSourceVO vo = new WXDataSourceVO();
        vo.setDataSourceId(po.getDataSourceId());
        vo.setDataType(po.getDataType());
        if (FORM_TYPE_TEXT.equals(po.getDataType())) {
            vo.setContent(po.getContent());
        } else {
            vo.setContent(FormUtil.getAssertUrl(po.getPath()));
        }
        vo.setSort(po.getSort());
        return vo;
        
    }
    public String getDataSourceId() {
        return dataSourceId;
    }
    
    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }
    
    public Integer getDataType() {
        return dataType;
    }
    
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public int getSort() {
        return sort;
    }
    
    public void setSort(int sort) {
        this.sort = sort;
    }
}
