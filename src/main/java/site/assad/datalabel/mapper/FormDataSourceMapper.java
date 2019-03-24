package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.*;
import site.assad.datalabel.po.FormDataSourcePO;

import java.util.List;

@Mapper
public interface FormDataSourceMapper {
    
    @Select("select * from form_data_source where form_id=#{id}")
    List<FormDataSourcePO> selectByFormId(String id);
    
    @Insert("<script>" +
            "insert into form_data_source(data_source_id, form_id, data_type, content, path, sort, create_time) values" +
            "<foreach collection='list' item='item' index='index'  separator=','>" +
            " (#{item.dataSourceId},#{item.formId},#{item.dataType},#{item.content},#{item.path},#{item.sort},#{item.createTime})" +
            "</foreach>" +
            "</script>")
    void batchAdd(@Param("list") List<FormDataSourcePO> pos);
    
    @Delete("delete from form_data_source where form_id=#{id}")
    void deleteByFormId(String id);
    
}
