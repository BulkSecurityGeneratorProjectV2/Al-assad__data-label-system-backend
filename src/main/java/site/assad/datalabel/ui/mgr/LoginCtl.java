package site.assad.datalabel.ui.mgr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import site.assad.datalabel.dto.AdminInfoDTO;
import site.assad.datalabel.po.AdminUserPO;
import site.assad.datalabel.service.IUserService;
import site.assad.datalabel.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Al-assad
 * @since 2019/3/23
 * created by Intellij-IDEA
 */
@Controller
public class LoginCtl {
    private static transient Logger LOGGER = LoggerFactory.getLogger(LoginCtl.class);
    
    @Autowired
    private IUserService userService;
    
    /**
     * 用户登录
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView login(String username, String password, HttpServletRequest request){
        
        AdminUserPO po = userService.validLogin(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        if(po == null){
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("msg", "登录失败");
            return mv;
        }
        //设置cookie
        AdminInfoDTO adminInfo = new AdminInfoDTO();
        adminInfo.setAdminId(po.getAdminId());
        adminInfo.setUserName(po.getUserName());
        adminInfo.setOrgId(po.getOrgId());
        SessionUtil.setAdminInfo(request, adminInfo);
        
        ModelAndView mv = new ModelAndView("forward:/taskManager.html");
        mv.addObject("adminInfo", adminInfo);
        return mv;
    }
    
    /**
     * 管理员注册
     */
    @ResponseBody
    @RequestMapping("/register.do")
    public String registerAdmin(String username, String password) {
        AdminUserPO po = new AdminUserPO();
        po.setUserName(username);
        po.setPwd(DigestUtils.md5DigestAsHex(password.getBytes()));
        po.setOrgId(UUID.randomUUID().toString());
        po.setAdminId(UUID.randomUUID().toString());
        po.setCreateTime(new Date());
        boolean successful = userService.registerAdmin(po);
        return "successful:" + successful;
    }
}
