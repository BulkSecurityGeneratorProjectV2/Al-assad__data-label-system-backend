package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import site.assad.datalabel.po.FormItemOptionPO;

import java.util.List;

@Mapper
public interface FormItemOptionMapper {
    
    
    @Select("select * from form_item_option where formId=#{formId}")
    List<FormItemOptionPO> selectByFormId(String formId);
    
    @Insert("insert into form_item_option(item_option_id,form_id,content,sort,create_time) values (#{itemOptionId},#{formId},#{content},#{sort},#{createTime)")
    void insert(FormItemOptionPO po);
    
    @Delete("delete from form_item_option where formId=#{formId}")
    void deleteByFormId(String formId);
}
