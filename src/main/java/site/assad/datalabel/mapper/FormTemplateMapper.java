package site.assad.datalabel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import site.assad.datalabel.po.FormPO;

import java.util.List;

@Mapper
public interface FormTemplateMapper {
    
    @Select("select * from form where type=#{type} and org_id='template' order by create_time desc")
    List<FormPO> selectByType(Integer type);
    
    @Select("select * from form where org_id='template' order by create_time desc")
    List<FormPO> selectAllType();
}
