package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import site.assad.datalabel.po.AdminUserPO;
import site.assad.datalabel.po.FormPO;

@Mapper
public interface FormMapper {
    
    @Select("select * from form where form_id=#{id} ")
    FormPO selectById(String id);
    
    @Insert("insert into form(form_id,org_id,title,description,cur_num,limit_num,source_count,form_status,type,item_type,item_content,create_time) " +
            "values(#{formId},#{orgId},#{title},#{description},#{curNum},#{limitNum},#{sourceCount},#{formStatus},#{type},#{itemType},#{itemContent},#{createTime})")
    void add(FormPO po);
    
    @Update("update form set org_id=#{orgId},title=#{title},description=#{description},cur_num=#{curNum},limit_num=#{limitNum},source_count=#{sourceCount},form_status=#{formStatus},type=#{type},item_type=#{itemType},item_content=#{itemContent} " +
            "where form_id=#{formId}")
    void update(FormPO po);
    
}
