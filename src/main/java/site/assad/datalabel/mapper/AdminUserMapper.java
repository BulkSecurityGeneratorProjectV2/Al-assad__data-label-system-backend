package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import site.assad.datalabel.po.AdminUserPO;

@Mapper()
public interface AdminUserMapper {
    
    @Select("select * from admin_user where user_name=#{username} and pwd=#{password} ")
    AdminUserPO selectByNameAndPwd(String username, String password);
    
    @Select("select * from admin_user where user_name=#{username} ")
    AdminUserPO selectByUserName(String username);
    
    @Select("select * from admin_user where admin_id=#{adminId} ")
    AdminUserPO selectById(String adminId);
    
    @Insert("insert into admin_user(admin_id,org_id,user_name,pwd,create_time) values(#{adminId},#{orgId},#{userName},#{pwd},#{createTime})")
    void add(AdminUserPO po);
    
    
}
