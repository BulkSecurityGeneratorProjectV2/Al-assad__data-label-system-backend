package site.assad.datalabel.service;

import site.assad.datalabel.po.AdminUserPO;

public interface IUserService {
    
    /**
     * 管理后台登录验证
     */
    AdminUserPO validLogin(String username, String password);
    
    /**
     * 管理后台注册
     */
    boolean registerAdmin(AdminUserPO po);
}
