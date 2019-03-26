package site.assad.datalabel.ui.mgr;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import site.assad.datalabel.dto.AdminInfoDTO;
import site.assad.datalabel.mapper.FormItemOptionMapper;
import site.assad.datalabel.mapper.FormMapper;
import site.assad.datalabel.mapper.FormTemplateMapper;
import site.assad.datalabel.mapper.UserTaskMapper;
import site.assad.datalabel.po.FormItemOptionPO;
import site.assad.datalabel.po.FormPO;
import site.assad.datalabel.po.UserTaskPO;
import site.assad.datalabel.util.SessionUtil;
import site.assad.datalabel.vo.FormTemplateVO;
import site.assad.datalabel.vo.FormVO;
import site.assad.datalabel.vo.MessageVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static site.assad.datalabel.util.ConstantUtil.*;

/**
 * @author Al-assad
 * @since 2019/3/23
 * created by Intellij-IDEA
 */
@Controller
public class PageCtl {
    
 
    @Resource
    FormTemplateMapper formTemplateMapper;
    @Resource
    FormMapper formMapper;
    @Resource
    UserTaskMapper userTaskMapper;
    @Resource
    FormItemOptionMapper formItemOptionMapper;
    //什么分层实现、什么代码规范、什么开闭原则，见鬼去吧！我就是要快！
    
    @RequestMapping("/index.html")
    public String toIndex() {
        return "index";
    }
    
    /**
     * 任务管理
     **/
    @RequestMapping("/taskManager.html")
    public ModelAndView toTaskManager(HttpServletRequest request) {
        //权限验证
        AdminInfoDTO adminInfo = SessionUtil.getAdminInfo(request);
        if (adminInfo == null) {
            return new ModelAndView("index");
        }
        ModelAndView mv = new ModelAndView("task_manager");
        mv.addObject("adminInfo", adminInfo);
        
        //查询表单列表
        List<FormPO> formPOS = formMapper.selectByOrgId(adminInfo.getOrgId());
        
        //表单相关统计字段计算
        int totalForm = 0;
        int startingForm = 0;
        int finishForm = 0;
        int totalTask = 0;
        if (CollectionUtils.isNotEmpty(formPOS)) {
            totalForm =  formPOS.size();
            finishForm = (int)formPOS.stream().filter(po -> FORM_STATUS_FINISH.equals(po.getFormStatus())).count();
            startingForm = totalForm - finishForm;
            totalTask = formPOS.stream().filter(po -> FORM_STATUS_FINISH.equals(po.getFormStatus())).mapToInt(FormPO::getLimitNum).sum();
        }
        //查询任务
        List<UserTaskPO> taskPOS = userTaskMapper.selectByOrgId(adminInfo.getOrgId());
    
        //任务相关统计字段计算
        int finishTask = 0;
        int totalTaskUser = 0;
        if (CollectionUtils.isNotEmpty(taskPOS)) {
            finishTask = (int) taskPOS.stream().filter(po -> TASK_STATUS_FINISH.equals(po.getTaskStatus())).count();
            totalTaskUser = (int) taskPOS.stream().map(UserTaskPO::getUserId).distinct().count();
        }
        
        mv.addObject("totalForm", totalForm);
        mv.addObject("startingForm", startingForm);
        mv.addObject("finishForm", finishForm);
        mv.addObject("totalTask", totalTask);
        mv.addObject("finishTask", finishTask);
        mv.addObject("totalTaskUser", totalTaskUser);
        mv.addObject("form", formPOS);
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
    
    /**
     * 用户详情
     */
    @RequestMapping("/formDetail.html")
    public String toFormDetail(HttpServletRequest request, ModelMap model, String formId){
        //权限验证
        AdminInfoDTO adminInfo = SessionUtil.getAdminInfo(request);
        if (adminInfo == null) {
            return "index";
        }
        if (StringUtils.isEmpty(formId)) {
            model.put("adminInfo", adminInfo);
            return "task_manager";
        }
        //查询表单对象
        FormPO formPO = formMapper.selectById(formId);
        if(formPO == null){
            model.put("adminInfo", adminInfo);
            return "task_manager";
        }
        FormVO formVO = new FormVO();
        formVO.setFormId(formPO.getFormId());
        formVO.setOrgId(formPO.getOrgId());
        formVO.setTitle(formPO.getTitle());
        formVO.setLimitNum(formPO.getLimitNum());
        formVO.setCurNum(formPO.getCurNum());
        formVO.setDescription(formPO.getDescription());
        formVO.setItemContent(formPO.getItemContent());
        formVO.setItemType(formPO.getItemType());
        formVO.setType(formPO.getType());
        //查询表单字段对象
        List<FormItemOptionPO> optionPOS = formItemOptionMapper.selectByFormId(formId);
        if (CollectionUtils.isNotEmpty(optionPOS)) {
            formVO.setItemTags(optionPOS.stream().map(FormItemOptionPO::getContent).collect(Collectors.joining(",")));
        }
        model.put("msg", MessageVO.of(MSG_INFO, "开始编辑/查看任务详情"));
        model.put("adminInfo", adminInfo);
        model.put("form", formVO);
        return "form_detail";
    }
    
    
    /**
     * 模版中心
     */
    @RequestMapping("/formCenter.html")
    public String toFormCenter(HttpServletRequest request, ModelMap model, Integer type) {
        //权限验证
        AdminInfoDTO adminInfo = SessionUtil.getAdminInfo(request);
        if (adminInfo == null) {
            return "index";
        }
        model.put("adminInfo", adminInfo);
        
        List<FormPO> formPOS;
        if (type == null || type == 0) {
            type = 1;
        }
        formPOS = formTemplateMapper.selectByType(type);
        
        List<FormTemplateVO> templateVOS = new ArrayList<>(0);
        if (CollectionUtils.isNotEmpty(formPOS)) {
            templateVOS = formPOS.stream()
                    .map(FormTemplateVO::of)
                    .collect(Collectors.toList());
        }
        model.put("templates", templateVOS);
        return "form_center";
    }
    
    /**
     * 新增表单
     */
    @RequestMapping("/addForm.do")
    public String addForm(HttpServletRequest request, ModelMap model, Integer type, boolean edit, String templateId) {
        //权限验证
        AdminInfoDTO adminInfo = SessionUtil.getAdminInfo(request);
        if (adminInfo == null) {
            return "index";
        }
        FormPO po = new FormPO();
        
        //通过模版创建
        if (StringUtils.isNotEmpty(templateId)) {
            FormPO templatePO = formMapper.selectById(templateId);
            //初始化PO
            po = FormPO.getInitPO();
            po.setOrgId(adminInfo.getOrgId());
            po.setTitle(templatePO.getTitle());
            po.setItemType(templatePO.getItemType());
            po.setDescription(templatePO.getDescription());
            po.setItemContent(templatePO.getItemContent());
            po.setLimitNum(templatePO.getLimitNum());
            po.setType(templatePO.getType());
            po.setFormStatus(FORM_STATUS_DRAFT);
            //插入表单数据
            formMapper.add(po);
        }
        
        model.put("adminInfo", adminInfo);
        model.put("type", type);
        model.put("edit", edit);
        model.put("form", po);
        return "add_form";
    }
    
    
}
