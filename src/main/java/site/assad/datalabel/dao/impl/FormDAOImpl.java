package site.assad.datalabel.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import site.assad.datalabel.dao.IFormDAO;

@Repository("formDAO")
public class FormDAOImpl implements IFormDAO {
    
    private static transient Logger LOGGER = LoggerFactory.getLogger(FormDAOImpl.class);
}
