package site.assad.datalabel.vo;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import site.assad.datalabel.po.FormItemOptionPO;
import site.assad.datalabel.po.FormPO;
import site.assad.datalabel.util.FormUtil;

import java.util.List;
import java.util.stream.Collectors;

public class WXSimpleFormVO {
    
    private String formId;
    private String orgId;
    private String title;
    private int sourceCount;
    private Integer type;
    private Integer itemType;
    private String itemContent;
    private String imagePath;
    private List<ItemOptionVO> itemOption;
    
    
    public static WXSimpleFormVO of (FormPO formPO, List<FormItemOptionPO> optionPOS){
        if (formPO == null) {
            return null;
        }
        WXSimpleFormVO formVO = new WXSimpleFormVO();
        formVO.setFormId(formPO.getFormId());
        formVO.setOrgId(formPO.getOrgId());
        formVO.setTitle(formPO.getTitle());
        formVO.setSourceCount(formPO.getSourceCount());
        formVO.setType(formPO.getType());
        formVO.setItemType(formPO.getItemType());
        formVO.setItemContent(formPO.getItemContent());
        formVO.setImagePath(FormUtil.getFormImagePath(formPO.getType()));
        if (CollectionUtils.isNotEmpty(optionPOS)) {
            List<ItemOptionVO> optionVOS = optionPOS.stream().map(ItemOptionVO::of).collect(Collectors.toList());
            formVO.setItemOption(optionVOS);
        }
        return formVO;
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
    
    public int getSourceCount() {
        return sourceCount;
    }
    
    public void setSourceCount(int sourceCount) {
        this.sourceCount = sourceCount;
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
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public List<ItemOptionVO> getItemOption() {
        return itemOption;
    }
    
    public void setItemOption(List<ItemOptionVO> itemOption) {
        this.itemOption = itemOption;
    }
}
