package site.assad.datalabel.service;

import org.springframework.lang.NonNull;
import site.assad.datalabel.po.AdminUserPO;
import site.assad.datalabel.po.WxUserPO;

public interface IUserService {
    
    /**
     * 管理后台登录验证
     */
    AdminUserPO validLogin(String username, String password);
    
    /**
     * 管理后台注册
     */
    boolean registerAdmin(AdminUserPO po);
    
    /**
     * 核查微信用户,
     */
    @NonNull WxUserPO getWxUserByOpenId(String openid);
}
