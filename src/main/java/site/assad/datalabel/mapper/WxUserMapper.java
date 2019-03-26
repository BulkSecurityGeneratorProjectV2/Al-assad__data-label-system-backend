package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import site.assad.datalabel.po.WxUserPO;

import java.util.List;

@Mapper
public interface WxUserMapper {
    
    @Select("select * from wx_user where user_id=#{userId}")
    WxUserPO selectByUserId(String userId);
    
    @Select("select * from wx_user where wx_id=#{wxId}")
    WxUserPO selectByWXId(String wxId);
    
    @Select("select * from wx_user where wx_id in ( ${wxIds} )")
    List<WxUserPO> selectByWXIds(String wxIds);
    
    @Insert("insert into wx_user(user_id,wx_id,wx_name,create_time) values (#{userId},#{wxId},#{wxName},#{createTime})")
    void add(WxUserPO po);
    
}
