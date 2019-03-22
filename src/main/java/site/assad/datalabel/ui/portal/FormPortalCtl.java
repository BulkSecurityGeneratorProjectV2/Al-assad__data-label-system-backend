package site.assad.datalabel.ui.portal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/portal")
public class FormPortalCtl {
    private static transient Logger LOGGER = LoggerFactory.getLogger(FormPortalCtl.class);
}
