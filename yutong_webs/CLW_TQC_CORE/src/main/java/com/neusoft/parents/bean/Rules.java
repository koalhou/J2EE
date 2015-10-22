package com.neusoft.parents.bean;

import java.io.Serializable;

public class Rules implements Serializable
{

    private static final long serialVersionUID = -5029134132103685618L;

    private String rule_id; // 规则ID

    private String user_id;

    private String stu_id;

    private String pm_rule_id;

    private String on_off;

    private String flag;

    private String valid_flag;

    public String getRule_id()
    {
        return rule_id;
    }

    public void setRule_id(String rule_id)
    {
        this.rule_id = rule_id;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

    public String getStu_id()
    {
        return stu_id;
    }

    public void setStu_id(String stu_id)
    {
        this.stu_id = stu_id;
    }

    public String getPm_rule_id()
    {
        return pm_rule_id;
    }

    public void setPm_rule_id(String pm_rule_id)
    {
        this.pm_rule_id = pm_rule_id;
    }

    public String getOn_off()
    {
        return on_off;
    }

    public void setOn_off(String on_off)
    {
        this.on_off = on_off;
    }

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public String getValid_flag()
    {
        return valid_flag;
    }

    public void setValid_flag(String valid_flag)
    {
        this.valid_flag = valid_flag;
    }

}
