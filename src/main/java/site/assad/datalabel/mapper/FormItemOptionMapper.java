package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.*;
import site.assad.datalabel.po.FormItemOptionPO;

import java.util.List;

@Mapper
public interface FormItemOptionMapper {
    
    
    @Select("select * from form_item_option where form_id=#{formId}")
    List<FormItemOptionPO> selectByFormId(String formId);
    
    @Insert("insert into form_item_option(item_option_id,form_id,content,sort,create_time) values (#{itemOptionId},#{formId},#{content},#{sort},#{createTime)")
    void add(FormItemOptionPO po);
    
    @Insert("<script>" +
            "insert into form_item_option(item_option_id,form_id,content,sort,create_time) values "+
            "<foreach collection='list' item='item' index='index'  separator=','>" +
            " (#{item.itemOptionId},#{item.formId},#{item.content},#{item.sort},#{item.createTime})" +
            "</foreach>" +
            "</script>")
    void batchAdd(@Param("list") List<FormItemOptionPO> pos);
    
    @Delete("delete from form_item_option where form_id=#{formId}")
    void deleteByFormId(String formId);
}
