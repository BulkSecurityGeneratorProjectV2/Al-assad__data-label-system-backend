package site.assad.datalabel.ui.mgr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import site.assad.datalabel.dto.AdminInfoDTO;
import site.assad.datalabel.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Al-assad
 * @since 2019/3/23
 * created by Intellij-IDEA
 */
@Controller
public class PageCtl {
    
    @RequestMapping("/index.html")
    public String toIndex() {
        return "index";
    }
    
    
    @RequestMapping("/taskManager.html")
    public ModelAndView toTaskManager(HttpServletRequest request) {
        //权限验证
        AdminInfoDTO adminInfo = SessionUtil.getAdminInfo(request);
        if (adminInfo == null) {
            return new ModelAndView("index");
        }
        
        ModelAndView mv = new ModelAndView("task_manager");
        mv.addObject("adminInfo", adminInfo);
        
        
        return mv;
    }
    
    @RequestMapping("/orgInfo.html")
    public String toOrgInfo(HttpServletRequest request, ModelMap model) {
        //权限验证
        AdminInfoDTO adminInfo = SessionUtil.getAdminInfo(request);
        if (adminInfo == null) {
            return "index";
        }
        model.put("adminInfo", adminInfo);
        
        return "org_info";
    }
    
    @RequestMapping("/formCenter.html")
    public String toFormCenter(HttpServletRequest request, ModelMap model) {
        //权限验证
        AdminInfoDTO adminInfo = SessionUtil.getAdminInfo(request);
        if (adminInfo == null) {
            return "index";
        }
        model.put("adminInfo", adminInfo);
        
        return "form_center";
    }
    
    /**
     * 新增表单
     */
    @RequestMapping("/addForm.do")
    public String addForm(HttpServletRequest request, ModelMap model, Integer type, boolean edit){
        //权限验证
        AdminInfoDTO adminInfo = SessionUtil.getAdminInfo(request);
        if (adminInfo == null) {
            return "index";
        }
        model.put("adminInfo", adminInfo);
        model.put("type", type);
        model.put("edit", edit);
        return "add_form";
    }
    
    
    
}
