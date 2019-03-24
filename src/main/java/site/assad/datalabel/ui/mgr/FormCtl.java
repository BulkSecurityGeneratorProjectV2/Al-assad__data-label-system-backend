package site.assad.datalabel.ui.mgr;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import site.assad.datalabel.dto.AdminInfoDTO;
import site.assad.datalabel.mapper.FormMapper;
import site.assad.datalabel.po.FormPO;
import site.assad.datalabel.service.IFormService;
import site.assad.datalabel.util.SessionUtil;
import site.assad.datalabel.vo.FormVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

import static site.assad.datalabel.util.ConstantUtil.FORM_STATUS_PUBISH;

@RestController
@RequestMapping("/form")
public class FormCtl {
    
    private static transient Logger LOGGER = LoggerFactory.getLogger(FormCtl.class);
    /**
     * MD我就是要跨域调用
     */
    @Resource
    private FormMapper formMapper;
    @Resource
    private IFormService formService;
    
    /**
     * 发布表单/保存表单
     */
    @RequestMapping("/add.do")
    public String addForm(HttpServletRequest request, ModelMap model, @Param("form") FormVO formVO, boolean isUpdate){
        //权限验证
        AdminInfoDTO adminInfo = SessionUtil.getAdminInfo(request);
        if (adminInfo == null) {
            return "index";
        }
        if (formVO == null) {
            model.put("adminInfo", adminInfo);
            return "task_manager";
        }
        
        //发布表单 TODO 增加数据源校验
        if (!isUpdate){
            FormPO po = new FormPO();
            po.setFormId(UUID.randomUUID().toString());
            po.setOrgId(adminInfo.getOrgId());
            po.setTitle(formVO.getTitle());
            po.setCurNum(0);
            po.setLimitNum(formVO.getLimitNum());
            po.setCreateTime(new Date());
            po.setDescription(formVO.getDescription());
            po.setFormStatus(FORM_STATUS_PUBISH);
            po.setItemType(formVO.getItemType());
            po.setItemContent(formVO.getItemContent());
            po.setType(formVO.getType());
            
            po.setSourceCount(0);
            
            
        }
        //保存表单
        else {
        
        }
        
        
        
        
        
        model.put("adminInfo", adminInfo);
        return "task_manager";
    }
    

}
