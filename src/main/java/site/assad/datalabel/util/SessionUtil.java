package site.assad.datalabel.util;

import site.assad.datalabel.dto.AdminInfoDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    
    /**
     * 设置管理员信息到session
     */
    public static void setAdminInfo(HttpServletRequest request, AdminInfoDTO adminInfo) {
        if (adminInfo == null) {
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("adminId", adminInfo.getAdminId());
        session.setAttribute("userName", adminInfo.getUserName());
        session.setAttribute("orgId", adminInfo.getOrgId());
        //4小时过期
        session.setMaxInactiveInterval(4 * 3600);
    }
    
    /**
     * 从请求session中获取管理员信息
     **/
    public static AdminInfoDTO getAdminInfo(HttpServletRequest request){
        AdminInfoDTO adminInfo = new AdminInfoDTO();
        HttpSession session = request.getSession();
        adminInfo.setAdminId((String)session.getAttribute("adminId"));
        adminInfo.setUserName((String)session.getAttribute("userName"));
        adminInfo.setOrgId((String)session.getAttribute("orgId"));
        return adminInfo;
    }
}
