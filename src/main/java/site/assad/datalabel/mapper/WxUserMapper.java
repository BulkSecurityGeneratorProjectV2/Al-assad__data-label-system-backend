package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import site.assad.datalabel.po.WxUserPO;

import java.util.List;

@Mapper
public interface WxUserMapper {
    
    @Select("select * from wx_user where user_id=#{userId}")
    WxUserPO selectByUserId(String userId);
    
    @Select("select * from wx_user where wx_id=#{wxId}")
    WxUserPO selectByWXId(String wxId);
    
    @Select("<script>" +
            "select * from wx_user where wx_id in ( <foreach collection='list' item='item' index='index' separator=','>#{item}</foreach> ) " +
            "</script>")
    List<WxUserPO> selectByWXIds(@Param("list") List wxIds);
    
    @Insert("insert into wx_user(user_id,wx_id,wx_name,create_time) values (#{userId},#{wxId},#{wxName},#{createTime})")
    void add(WxUserPO po);
    
}
