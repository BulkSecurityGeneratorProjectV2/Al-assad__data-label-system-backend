package site.assad.datalabel.ui.mgr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.assad.datalabel.mapper.FormTemplateMapper;

import javax.annotation.Resource;

@RestController
@RequestMapping("/form")
public class FormMgrCtl {
    
    private static transient Logger LOGGER = LoggerFactory.getLogger(FormMgrCtl.class);
    @Resource
    private FormTemplateMapper formTemplateMapper;
    
}
