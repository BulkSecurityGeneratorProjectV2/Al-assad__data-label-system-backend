package site.assad.datalabel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import site.assad.datalabel.mapper.AdminUserMapper;
import site.assad.datalabel.mapper.WxUserMapper;
import site.assad.datalabel.po.AdminUserPO;
import site.assad.datalabel.po.WxUserPO;
import site.assad.datalabel.service.IUserService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements IUserService {
    
    private static transient Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private WxUserMapper wxUserMapper;
    
    @Override
    public AdminUserPO validLogin(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return null;
        }
        return adminUserMapper.selectByNameAndPwd(username, password);
    }
    
    @Override
    public boolean registerAdmin(AdminUserPO po) {
        if (adminUserMapper.selectByUserName(po.getUserName()) != null) {
            return false;
        }
        adminUserMapper.add(po);
        return true;
    }
    
    @Override
    public WxUserPO getWxUserByOpenId(String openid) {
        if (StringUtils.isEmpty(openid)) {
            new WxUserPO();
        }
        WxUserPO po = wxUserMapper.selectByWXId(openid);
        if (po == null) {
            po = new WxUserPO();
            po.setUserId(UUID.randomUUID().toString());
            po.setCreateTime(new Date());
            po.setWxId(openid);
            po.setWxName("微信用户***");
            wxUserMapper.add(po);
        }
        return po;
    }
    
}
