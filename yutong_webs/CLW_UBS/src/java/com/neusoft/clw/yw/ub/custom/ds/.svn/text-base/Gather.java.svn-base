package com.neusoft.clw.yw.ub.custom.ds;

import java.io.Serializable;

import com.neusoft.clw.yw.ub.srv.CommonUtil;

/**
 * 汇总统计DTO
 */
public class Gather implements Serializable
{
    private static final long serialVersionUID = -5055991428255438137L;
    
    private  String reportDate;
    private String isHoliday;//是否节假日
    
    private String entID;//企业ID
    private String entName;//企业名称
    private String entCode;//企业编码
    private String entType;//企业类型
    private String entOpenTime;//开通时间
    private int ac;//是否活跃  1 活跃  0 不活跃

    private int loginCnt;// 登陆数
    private int busCnt;// 车辆数
    private int openEntCnt;// 企业开通数
    private int emEntCnt;// 活跃企业数
    private float ep;// 活跃度

    private int vis;// 总访次
    private float dayVis;// 日访次
    private int amVis;// 上午访次
    private int pmVis;// 下午访次
    private int niVis;// 晚上访次
    private int addVis;// 追加访次
    private int oVis=0;// 原始访次
    
    private String openEntCntPercent;// 企业开通数占比
    private String emEntCntPercent;// 活跃企业数占比
    private String visPercent;// 总访次占比
    private String amVisPercent;// 上午访次占比
    private String pmVisPercent;// 下午访次占比
    private String niVisPercent;// 晚上访次占比
    private String addVisPercent;// 追加访次占比
    private String loginCntPercent;// 登陆次数占比
    private String oVisPercent;// 原始访次占比


    public int getBusCnt()
    {
        return busCnt;
    }

    public void setBusCnt(int busCnt)
    {
        this.busCnt = busCnt;
    }

   
    public int getOpenEntCnt()
    {
        return openEntCnt;
    }

    public void setOpenEntCnt(int openEntCnt)
    {
        this.openEntCnt = openEntCnt;
    }

    public int getEmEntCnt()
    {
        return emEntCnt;
    }

    public void setEmEntCnt(int emEntCnt)
    {
        this.emEntCnt = emEntCnt;
    }

    public float getEp()
    {
        
        if(this.openEntCnt==0){
            return 0;
        }
       return  CommonUtil.getPercent(this.emEntCnt, this.openEntCnt);
    }


    public int getVis()
    {
        return vis;
    }

    public void setVis(int vis)
    {
        this.vis = vis;
    }

    public float getDayVis()
    {
        return dayVis;
    }

    public void setDayVis(float dayVis)
    {
        this.dayVis = dayVis;
    }

    public int getAmVis()
    {
        return amVis;
    }

    public void setAmVis(int amVis)
    {
        this.amVis = amVis;
    }

    public int getPmVis()
    {
        return pmVis;
    }

    public void setPmVis(int pmVis)
    {
        this.pmVis = pmVis;
    }

    public int getNiVis()
    {
        return niVis;
    }

    public void setNiVis(int niVis)
    {
        this.niVis = niVis;
    }

    public int getAddVis()
    {
        return addVis;
    }

    public void setAddVis(int addVis)
    {
        this.addVis = addVis;
    }

    public String getReportDate()
    {
        return reportDate;
    }

    public void setReportDate(String reportDate)
    {
        this.reportDate = reportDate;
    }

    public String getOpenEntCntPercent()
    {
        return openEntCntPercent;
    }

    public void setOpenEntCntPercent(String openEntCntPercent)
    {
        this.openEntCntPercent = openEntCntPercent;
    }

    public String getEmEntCntPercent()
    {
        return emEntCntPercent;
    }

    public void setEmEntCntPercent(String emEntCntPercent)
    {
        this.emEntCntPercent = emEntCntPercent;
    }

    public String getVisPercent()
    {
        return visPercent;
    }

    public void setVisPercent(String visPercent)
    {
        this.visPercent = visPercent;
    }

    public String getAmVisPercent()
    {
        return amVisPercent;
    }

    public void setAmVisPercent(String amVisPercent)
    {
        this.amVisPercent = amVisPercent;
    }

    public String getPmVisPercent()
    {
        return pmVisPercent;
    }

    public void setPmVisPercent(String pmVisPercent)
    {
        this.pmVisPercent = pmVisPercent;
    }

    public String getNiVisPercent()
    {
        return niVisPercent;
    }

    public void setNiVisPercent(String niVisPercent)
    {
        this.niVisPercent = niVisPercent;
    }

    public String getAddVisPercent()
    {
        return addVisPercent;
    }

    public void setAddVisPercent(String addVisPercent)
    {
        this.addVisPercent = addVisPercent;
    }
    
    public String getEntName()
    {
        return entName;
    }

    public void setEntName(String entName)
    {
        this.entName = entName;
    }

    public String getEntCode()
    {
        return entCode;
    }

    public void setEntCode(String entCode)
    {
        this.entCode = entCode;
    }

    public String getEntType()
    {
        return entType;
    }

    public void setEntType(String entType)
    {
        this.entType = entType;
    }

    public String getEntOpenTime()
    {
        return entOpenTime;
    }

    public void setEntOpenTime(String entOpenTime)
    {
        this.entOpenTime = entOpenTime;
    }

    public String getEntID()
    {
        return entID;
    }

    public void setEntID(String entID)
    {
        this.entID = entID;
    }

    
    public int getAc()
    {
        return ac;
    }

    public void setAc(int ac)
    {
        this.ac = ac;
    }

    public int getLoginCnt()
    {
        return loginCnt;
    }

    public void setLoginCnt(int loginCnt)
    {
        this.loginCnt = loginCnt;
    }

    public String getLoginCntPercent()
    {
        return loginCntPercent;
    }

    public void setLoginCntPercent(String loginCntPercent)
    {
        this.loginCntPercent = loginCntPercent;
    }

    public String getIsHoliday()
    {
        return isHoliday;
    }

    public void setIsHoliday(String isHoliday)
    {
        this.isHoliday = isHoliday;
    }

    public int getoVis() {
		return oVis;
	}

	public void setoVis(int oVis) {
		this.oVis = oVis;
	}

	public String getoVisPercent() {
		return oVisPercent;
	}

	public void setoVisPercent(String oVisPercent) {
		this.oVisPercent = oVisPercent;
	}

	@Override
    public String toString()
    {
        return "Gather [reportDate=" + reportDate + ", entID=" + entID
                + ", entName=" + entName + ", entCode=" + entCode
                + ", entType=" + entType + ", entOpenTime=" + entOpenTime+ ", ac=" + ac
                + ", busCnt=" + busCnt + ", openEntCnt=" + openEntCnt+ ", loginCnt=" + loginCnt
                + ", emEntCnt=" + emEntCnt + ", ep=" + getEp() + ", vis=" + vis
                + ", dayVis=" + dayVis + ", amVis=" + amVis + ", pmVis="
                + pmVis + ", niVis=" + niVis + ", addVis=" + addVis+ ", oVis=" + oVis + ", oVisPercent=" + oVisPercent
                + ", openEntCntPercent=" + openEntCntPercent
                + ", emEntCntPercent=" + emEntCntPercent + ", visPercent="
                + visPercent + ", amVisPercent=" + amVisPercent
                + ", pmVisPercent=" + pmVisPercent + ", niVisPercent="
                + niVisPercent + ", addVisPercent=" + addVisPercent+ ", loginCntPercent=" + loginCntPercent + "]";
    }


}
