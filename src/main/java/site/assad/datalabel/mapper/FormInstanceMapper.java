package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.*;
import site.assad.datalabel.po.FormDataSourcePO;
import site.assad.datalabel.po.FormInstancePO;

import java.util.List;

@Mapper
public interface FormInstanceMapper {

    @Select("select * from form_instance where form_id=#{id}")
    List<FormInstancePO> selectByFormId(String id);
    
    @Insert("insert into form_instance(id,user_id,form_id,data_source_id,item_content,create_time) values" +
            " (#{id},#{userId},#{formId},#{dataSourceId},#{itemContent},#{createTime})")
    void add(FormInstancePO po);
    
    @Delete("delete from form_instance where form_id=#{id}")
    void deleteByFormId(String id);
    
    
}
