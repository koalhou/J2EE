package com.yutong.clw.beans.vehicle_real;

import java.io.Serializable;

public class Parents implements Serializable
{

    private static final long serialVersionUID = -776994914213881387L;

    private String usr_id;

    private String cld_id;

    private String cld_alias;

    private String remind_station_id;

    private String remind_station_name;

    private String remind_type;

    private String remind_value;

    public String getUsr_id()
    {
        return usr_id;
    }

    public void setUsr_id(String usr_id)
    {
        this.usr_id = usr_id;
    }

    public String getCld_id()
    {
        return cld_id;
    }

    public void setCld_id(String cld_id)
    {
        this.cld_id = cld_id;
    }

    public String getCld_alias()
    {
        return cld_alias;
    }

    public void setCld_alias(String cld_alias)
    {
        this.cld_alias = cld_alias;
    }

    public String getRemind_station_id()
    {
        return remind_station_id;
    }

    public void setRemind_station_id(String remind_station_id)
    {
        this.remind_station_id = remind_station_id;
    }

    public String getRemind_station_name()
    {
        return remind_station_name;
    }

    public void setRemind_station_name(String remind_station_name)
    {
        this.remind_station_name = remind_station_name;
    }

    public String getRemind_type()
    {
        return remind_type;
    }

    public void setRemind_type(String remind_type)
    {
        this.remind_type = remind_type;
    }

    public String getRemind_value()
    {
        return remind_value;
    }

    public void setRemind_value(String remind_value)
    {
        this.remind_value = remind_value;
    }

     

}
