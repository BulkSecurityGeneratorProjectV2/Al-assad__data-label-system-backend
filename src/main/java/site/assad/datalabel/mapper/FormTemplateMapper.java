package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import site.assad.datalabel.po.AdminUserPO;
import site.assad.datalabel.po.FormTemplatePO;

import java.util.List;

@Mapper
public interface FormTemplateMapper {
    
    
    @Select("select * from form_template where title like #{keyword}")
    List<FormTemplatePO> selectByTitle(String keyword);
    
    @Select("select * from form_template where template_id=#{id} ")
    FormTemplatePO selectById(String id);
    
    @Insert("insert into form_template(template_id,title,description,type,item_type,item_content,create_time) " +
            "values(#{templateId},#{title},#{description},#{type},#{itemType},#{itemContent},#{createTime})")
    void add(FormTemplatePO po);
}
