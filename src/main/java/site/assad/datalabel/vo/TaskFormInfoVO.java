package site.assad.datalabel.vo;

import java.util.Date;

public class TaskFormInfoVO {
    
    private String userName;
    private String formTitle;
    private String formType;
    private String month;
    private String date;
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getFormTitle() {
        return formTitle;
    }
    
    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }
    
    public String getFormType() {
        return formType;
    }
    
    public void setFormType(String formType) {
        this.formType = formType;
    }
    
    public String getMonth() {
        return month;
    }
    
    public void setMonth(String month) {
        this.month = month;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
}
