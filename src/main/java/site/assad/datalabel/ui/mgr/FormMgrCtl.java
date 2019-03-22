package site.assad.datalabel.ui.mgr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
public class FormMgrCtl {
    
    private static transient Logger LOGGER = LoggerFactory.getLogger(FormMgrCtl.class);
    
    @RequestMapping("/test.do")
    public String test(){
        return "hello world!";
    }
}
