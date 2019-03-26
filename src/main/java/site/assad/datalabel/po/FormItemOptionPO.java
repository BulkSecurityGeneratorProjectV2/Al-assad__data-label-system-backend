package site.assad.datalabel.po;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Al-assad
 * @since 2019/3/22
 * created by Intellij-IDEA
 */
public class FormItemOptionPO {
    
    private String itemOptionId;
    private String formId;
    private String content;
    private int sort;
    private Date createTime;
    
    public String getItemOptionId() {
        return itemOptionId;
    }
    
    public void setItemOptionId(String itemOptionId) {
        this.itemOptionId = itemOptionId;
    }
    
    public String getFormId() {
        return formId;
    }
    
    public void setFormId(String formId) {
        this.formId = formId;
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
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    /**
     * 构建列表
     */
    public static List<FormItemOptionPO> buildList(String formId, List<String> values){
        if (StringUtils.isEmpty(formId) || CollectionUtils.isEmpty(values)) {
            return new ArrayList<>(0);
        }
        Date now = new Date();
        List<FormItemOptionPO> pos = new ArrayList<>(values.size());
        for (int i = 0; i< values.size(); i++) {
            FormItemOptionPO po = new FormItemOptionPO();
            po.setItemOptionId(UUID.randomUUID().toString());
            po.setContent(values.get(i));
            po.setFormId(formId);
            po.setSort(i+1);
            po.setCreateTime(now);
            pos.add(po);
        }
        return pos;
    }
}
