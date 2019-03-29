package site.assad.datalabel.vo;

import site.assad.datalabel.po.FormItemOptionPO;

import java.util.Date;

public class ItemOptionVO {
    
    private String itemOptionId;
    private String content;
    private int sort;
    
    public static ItemOptionVO of (FormItemOptionPO po) {
        if (po == null){
            return null;
        }
        ItemOptionVO vo = new ItemOptionVO();
        vo.setItemOptionId(po.getItemOptionId());
        vo.setContent(po.getContent());
        vo.setSort(po.getSort());
        return vo;
    }
    public String getItemOptionId() {
        return itemOptionId;
    }
    
    public void setItemOptionId(String itemOptionId) {
        this.itemOptionId = itemOptionId;
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
