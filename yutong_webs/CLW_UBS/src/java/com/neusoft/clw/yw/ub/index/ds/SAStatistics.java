package com.neusoft.clw.yw.ub.index.ds;

public class SAStatistics
{
    private  String reportDate;
    private int total;// 服务数
    private int baseReq=0;// 满足基本要求的服务数
    private int qulityReq=0;// 满足质量要求的服务数
    private float baseReqPercent=0;// 满足基本要求的占比
    private float qulityReqPercent=0;// 满足质量要求的占比
    
    public String getReportDate()
    {
        return reportDate;
    }
    public void setReportDate(String reportDate)
    {
        this.reportDate = reportDate;
    }
    public int getTotal()
    {
        return total;
    }
    public void setTotal(int total)
    {
        this.total = total;
    }
    public int getBaseReq()
    {
        return baseReq;
    }
    public void setBaseReq(int baseReq)
    {
        this.baseReq = baseReq;
    }
    public int getQulityReq()
    {
        return qulityReq;
    }
    public void setQulityReq(int qulityReq)
    {
        this.qulityReq = qulityReq;
    }
    public float getBaseReqPercent()
    {
        return baseReqPercent;
    }
    public void setBaseReqPercent(float baseReqPercent)
    {
        this.baseReqPercent = baseReqPercent;
    }
    public float getQulityReqPercent()
    {
        return qulityReqPercent;
    }
    public void setQulityReqPercent(float qulityReqPercent)
    {
        this.qulityReqPercent = qulityReqPercent;
    }
	@Override
	public String toString() {
		return "SAStatistics [reportDate=" + reportDate + ", total=" + total
				+ ", baseReq=" + baseReq + ", qulityReq=" + qulityReq
				+ ", baseReqPercent=" + baseReqPercent + ", qulityReqPercent="
				+ qulityReqPercent + "]";
	}

    
}
