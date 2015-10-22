package com.neusoft.parents.bean;

import java.io.Serializable;
import java.util.List;

import com.neusoft.parents.bean.Parents;


public class Students  implements Serializable
{
 

    /**
     * 
     */
    private static final long serialVersionUID = 7472580886814749749L;

    private String stu_id;// 学生ID
 
    private List<Parents> parents;// 家长
    
    
    private String stu_state;

    public String getStu_state()
    {
        return stu_state;
    }

    public void setStu_state(String stu_state)
    {
        this.stu_state = stu_state;
    }

    public String getStu_id()
    {
        return stu_id;
    }

    public void setStu_id(String stu_id)
    {
        this.stu_id = stu_id;
    }

    public List<Parents> getParents()
    {
        return parents;
    }

    public void setParents(List<Parents> parents)
    {
        this.parents = parents;
    }
    
 

 

}
