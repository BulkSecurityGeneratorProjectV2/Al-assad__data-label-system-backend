package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.*;
import site.assad.datalabel.po.AdminUserPO;
import site.assad.datalabel.po.FormPO;

import java.util.List;

@Mapper
public interface FormMapper {
    
    
    
    @Select("select * from form where form_id=#{id} ")
    FormPO selectById(String id);
    
    @Select("<script>" +
            "select * from form where form_id in ( <foreach collection='list' item='item' index='index' separator=','>#{item}</foreach> ) " +
            "</script>")
    List<FormPO> selectByIds(@Param("list") List<String> ids);
    
    @Select("select * from form where form_status=#{status} ")
    List<FormPO> selectByStat(Integer status);
    
    @Select("select * from form where form_status=#{status} and type=#{type} ")
    List<FormPO> selectByStatAndType(Integer status, Integer type);
    
    @Select("select * from form where org_id=#{orgId} order by create_time desc")
    List<FormPO> selectByOrgId(String orgId);
    
    @Insert("insert into form(form_id,org_id,title,description,cur_num,limit_num,source_count,form_status,type,item_type,item_content,create_time) " +
            "values(#{formId},#{orgId},#{title},#{description},#{curNum},#{limitNum},#{sourceCount},#{formStatus},#{type},#{itemType},#{itemContent},#{createTime})")
    void add(FormPO po);
    
    @Update("update form set org_id=#{orgId},title=#{title},description=#{description},cur_num=#{curNum},limit_num=#{limitNum},source_count=#{sourceCount},form_status=#{formStatus},type=#{type},item_type=#{itemType},item_content=#{itemContent} " +
            "where form_id=#{formId}")
    void update(FormPO po);
    
    /**
     * 更新数据源数据
     */
    @Update("update form set source_count=source_count+#{sourceCount} where form_id=#{formId}")
    void updateSourceCount(int sourceCount, String formId);
    
    /**
     * 更新填单数量
     */
    @Update("update form set cur_num=cur_num+#{count}, form_status=#{status} where form_id=#{formId}")
    void increaseCurNum(int count, String formId, Integer status);
    
    @Delete("delete from form where form_id=#{id} ")
    void delet(String id);
    
    @Select("select count(1) from form where form_status='1'")
    long countPublishForm();
    
    @Select("select sum(limit_num) from form where form_status='1'")
    long countPublishTask();
    
    
    
}
