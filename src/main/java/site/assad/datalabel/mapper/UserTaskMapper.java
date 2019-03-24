package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import site.assad.datalabel.po.UserTaskPO;

import java.util.List;

@Mapper
public interface UserTaskMapper {
    
    @Select("select * from user_task where user_id=#{userId}")
    List<UserTaskPO> selectByUserId(String userId);
    
    @Insert("insert into user_task(id,user_id,form_id,org_id,task_status,data_source_sort,create_time) values" +
            " (#{id},#{userId},#{formId},#{orgId},#{taskStatus},#{dataSourceSort},#{createTime})")
    void add(UserTaskPO po);
    
    
}
