package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import site.assad.datalabel.po.UserTaskPO;

import java.util.List;

@Mapper
public interface UserTaskMapper {
    
    @Select("select * from user_task where user_id=#{userId}")
    List<UserTaskPO> selectByUserId(String userId);
    
    @Select("select form_id from user_task where user_id=#{userId} and task_status=3")
    List<String> selectFinishTaskFormIdByUserId(String userId);
    
    
    @Select("select * from user_task where user_id=#{userId} and form_id=#{formId}")
    UserTaskPO selectByUserIdAndFormId(String userId, String formId);
    
    @Select("select * from user_task where user_id=#{userId} and task_status <> 3")
    List<UserTaskPO> selectByUserIdUnFinish(String userId);
    
    @Select("select count(1) from user_task where user_id=#{userId}")
    long countByUserId(String userId);
    
    @Select("select * from user_task where user_id=#{userId} and task_status=#{status}")
    List<UserTaskPO> selectByUserIdAndStat(String userId, Integer status);
    
    @Select("select count(1) from user_task where user_id=#{userId} and task_status=#{status}")
    long countByUserIdAndStat(String userId, Integer status);
    
    @Select("select * from user_task where org_id=#{orgId}")
    List<UserTaskPO> selectByOrgId(String orgId);
    
    @Insert("insert into user_task(id,user_id,form_id,org_id,task_status,data_source_sort,create_time) values" +
            " (#{id},#{userId},#{formId},#{orgId},#{taskStatus},#{dataSourceSort},#{createTime})")
    void add(UserTaskPO po);
    
    @Select("delete from user_task where form_id=#{formId}")
    void deleteByFormId(String formId);
    
    @Update("update user_task set task_status=#{taskStatus}, data_source_sort=#{dataSourceSort} where form_id=#{formId}")
    void updateStatusAndSort(String formId, Integer taskStatus, int dataSourceSort);
    
    
}
