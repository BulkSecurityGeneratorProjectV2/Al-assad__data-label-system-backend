package site.assad.datalabel.vo;

public class MessageVO {
    
    /**
     * 警告等级：1-success，2-info，3-notify
     */
    private Integer level;
    private String msg;
    
    public static MessageVO of(Integer level, String msg) {
        MessageVO vo = new MessageVO();
        vo.setLevel(level);
        vo.setMsg(msg);
        return vo;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
