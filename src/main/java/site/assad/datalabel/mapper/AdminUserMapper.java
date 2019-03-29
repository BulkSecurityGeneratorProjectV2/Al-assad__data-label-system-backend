package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import site.assad.datalabel.po.AdminUserPO;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    
    @Select("select * from admin_user where user_name=#{username} and pwd=#{password} ")
    AdminUserPO selectByNameAndPwd(String username, String password);
    
    @Select("select * from admin_user where user_name=#{username} ")
    AdminUserPO selectByUserName(String username);
    
    @Select("select * from admin_user where admin_id=#{adminId} ")
    AdminUserPO selectById(String adminId);
    
    @Select("<script>" +
            "select * from admin_user where org_id in ( <foreach collection='list' item='item' index='index' separator=','>#{item}</foreach> ) " +
            "</script>")
    List<AdminUserPO> selectByOrgIds(@Param("list") List<String> ids);
    
    @Select("select * from admin_user where org_id=#{orgId} ")
    AdminUserPO selectByOrgId(String orgId);
    
    @Insert("insert into admin_user(admin_id,org_id,user_name,pwd,create_time) values(#{adminId},#{orgId},#{userName},#{pwd},#{createTime})")
    void add(AdminUserPO po);
    

    
    
}
