package site.assad.datalabel.ui.mgr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Al-assad
 * @since 2019/3/23
 * created by Intellij-IDEA
 */
@Controller
public class PageCtl {
    
    @RequestMapping("/index.html")
    public String toIndex(){
        return "index";
    }
    
    @RequestMapping("/taskManager.html")
    public ModelAndView toTaskManager(){
        ModelAndView mv = new ModelAndView("/task_manager");
        return mv;
    }


}
