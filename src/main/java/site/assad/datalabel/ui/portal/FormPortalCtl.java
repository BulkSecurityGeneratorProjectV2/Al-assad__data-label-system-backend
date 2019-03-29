package site.assad.datalabel.ui.portal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.assad.datalabel.mapper.AdminUserMapper;
import site.assad.datalabel.mapper.FormMapper;
import site.assad.datalabel.mapper.UserTaskMapper;
import site.assad.datalabel.mapper.WxUserMapper;
import site.assad.datalabel.po.AdminUserPO;
import site.assad.datalabel.po.FormPO;
import site.assad.datalabel.po.UserTaskPO;
import site.assad.datalabel.po.WxUserPO;
import site.assad.datalabel.service.IUserService;
import site.assad.datalabel.vo.WXFormVO;
import site.assad.datalabel.vo.WXTaskVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static site.assad.datalabel.util.ConstantUtil.*;


@RestController
@RequestMapping("/portal")
public class FormPortalCtl {
    
    private static transient Logger LOGGER = LoggerFactory.getLogger(FormPortalCtl.class);
    @Resource
    private FormMapper formMapper;
    @Resource
    private WxUserMapper wxUserMapper;
    @Resource
    private IUserService userService;
    @Resource
    private UserTaskMapper userTaskMapper;
    @Resource
    private AdminUserMapper adminUserMapper;
    
    /**
     * 获取所有模版统计数据
     */
    @RequestMapping(value="/getAllFormStat.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getAllFormStat(@RequestBody JSONObject jsonParam){
        String openid = (String)jsonParam.get("openid");
        long formCount = formMapper.countPublishForm();
        long taskCouunt = formMapper.countPublishTask();
        long myAllTaskCount = 0;
        long myFinishTaskCount = 0;
        long myRunTaskCount = 0;
        
        if (StringUtils.isNotEmpty(openid)){
            WxUserPO userPO = userService.getWxUserByOpenId(openid);
            myAllTaskCount = userTaskMapper.countByUserId(userPO.getUserId());
            myFinishTaskCount = userTaskMapper.countByUserIdAndStat(userPO.getUserId(), TASK_STATUS_FINISH);
            myRunTaskCount = myAllTaskCount -  myFinishTaskCount;
        }
        JSONObject json = new JSONObject();
        json.put("formCount", formCount);
        json.put("taskCount", taskCouunt);
        json.put("myAllTaskCount", myAllTaskCount);
        json.put("myFinishTaskCount", myFinishTaskCount);
        json.put("myRunTaskCount", myRunTaskCount);
        return json.toJSONString();
    }
    
    /**
     * 获取模版
     */
    @RequestMapping(value="/getForm.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getFormList(@RequestBody JSONObject jsonParam){
        Integer type = (Integer)jsonParam.get("type");
        
        List<FormPO> formPOS = new ArrayList<>(0);
        //查询列表
        if (FORM_TYPE_ALL.equals(type)) {
            formPOS = formMapper.selectByStat(FORM_STATUS_PUBISH);
        }else {
            formPOS = formMapper.selectByStatAndType(FORM_STATUS_PUBISH, type);
        }
        List<WXFormVO> formVOS = new ArrayList<>(0);
        if (CollectionUtils.isNotEmpty(formPOS)) {
            formVOS = formPOS.stream().map(WXFormVO::of).collect(Collectors.toList());
            //组装发布机构名称
            List<String> orgIds = formPOS.stream().map(FormPO::getOrgId).distinct().collect(Collectors.toList());
            List<AdminUserPO> adminPOS = adminUserMapper.selectByOrgIds(orgIds);
            Map<String, String> orgNameDict = adminPOS.stream().collect(Collectors.toMap(AdminUserPO::getOrgId, AdminUserPO::getUserName));
            formVOS.forEach(vo -> vo.setOrgName(orgNameDict.get(vo.getOrgId())));
        }
        return JSON.toJSONString(formVOS);
    }
    
    /**
     * 获取用户待办列表
     */
    @RequestMapping(value="/getTodoTask.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getTodoTask(@RequestBody JSONObject jsonParam){
        String openid = (String)jsonParam.get("openid");
        
        List<UserTaskPO> taskPOS = new ArrayList<>(0);
        List<WXTaskVO> taskVOS = new ArrayList<>(0);
        
        if(StringUtils.isNotEmpty(openid)){
            WxUserPO userPO = userService.getWxUserByOpenId(openid);
            taskPOS = userTaskMapper.selectByUserIdUnFinish(userPO.getUserId());
            //查询对应表单
            if (CollectionUtils.isNotEmpty(taskPOS)) {
                List<String> formIds = taskPOS.stream().map(UserTaskPO::getFormId).distinct().collect(Collectors.toList());
                List<FormPO> formPOS = formMapper.selectByIds(formIds);
                Map<String, FormPO> formDict = formPOS.stream().collect(Collectors.toMap(FormPO::getFormId, Function.identity()));
                //组装视图对象
                taskVOS = taskPOS.stream().map(po -> WXTaskVO.of(po, formDict.get(po.getFormId()))).collect(Collectors.toList());
                //组装发布机构名称
                List<String> orgIds = formPOS.stream().map(FormPO::getOrgId).distinct().collect(Collectors.toList());
                List<AdminUserPO> adminPOS = adminUserMapper.selectByOrgIds(orgIds);
                Map<String, String> orgNameDict = adminPOS.stream().collect(Collectors.toMap(AdminUserPO::getOrgId, AdminUserPO::getUserName));
                taskVOS.forEach(vo -> vo.setOrgName(orgNameDict.get(vo.getOrgId())));
            }
        }
        return JSON.toJSONString(taskVOS);
    }
    
    /**
     * 获取表单详情
     */
    @RequestMapping(value="/getFormDetail.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getFormDetail(@RequestBody JSONObject jsonParam){
        String formId = (String)jsonParam.get("formId");
        
        WXFormVO  formVO = new WXFormVO();
        if(StringUtils.isNotEmpty(formId)){
            FormPO formPO = formMapper.selectById(formId);
            formVO = WXFormVO.of(formPO);
            //组装发布机构名称
            AdminUserPO adminPO = adminUserMapper.selectByOrgId(formPO.getOrgId());
            formVO.setOrgName(adminPO.getUserName());
        }
        return JSON.toJSONString(formVO);
    }
    
    
    
    /**
     * 收藏任务
     */
    @RequestMapping(value="/collectForm.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void collectForm(@RequestBody JSONObject jsonParam){
        String formId = (String)jsonParam.get("formId");
        String openid = (String)jsonParam.get("openid");
    
        if(StringUtils.isEmpty(formId) || StringUtils.isEmpty(openid)) {
            return;
        }
        WxUserPO userPO = userService.getWxUserByOpenId(openid);
        //查询表单是否存在
        FormPO formPO = formMapper.selectById(formId);
        if (formPO == null) {
            return;
        }
        //查询任务是否存在
        UserTaskPO taskPO = userTaskMapper.selectByUserIdAndFormId(userPO.getUserId(), formId);
        if (taskPO != null) {
            return;
        }
        UserTaskPO po = UserTaskPO.getInitPO(formId, userPO.getUserId(), formPO.getOrgId());
        userTaskMapper.add(po);
        
    }
    
    /**
     * 收藏任务
     */
    @RequestMapping(value="/getDataSource.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getDataSource(@RequestBody JSONObject jsonParam){
        String formId = (String)jsonParam.get("formId");
        String openid = (String)jsonParam.get("openid");
        
        if(StringUtils.isEmpty(formId) || StringUtils.isEmpty(openid)) {
            return;
        }
        WxUserPO userPO = userService.getWxUserByOpenId(openid);
        //查询表单是否存在
        FormPO formPO = formMapper.selectById(formId);
        if (formPO == null) {
            return;
        }
        
        UserTaskPO po = UserTaskPO.getInitPO(formId, userPO.getUserId(), formPO.getOrgId());
        userTaskMapper.add(po);
        
    }

}
