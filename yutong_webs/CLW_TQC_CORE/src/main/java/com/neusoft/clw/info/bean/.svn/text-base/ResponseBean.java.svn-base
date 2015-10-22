package com.neusoft.clw.info.bean;

import java.util.List;

/**
 * http响应消息对应的JavaBean 
 */
public class ResponseBean {
	private String olxDir;
	private String olxVersion;
	private String olxCompress;  // 是否压缩
	
	private String functionName; // 很重要
	

	private String functionSeq; // 很重要
	
	private String treeObjectVersion;

	// 响应消息字段
	private String beginTime;
	private String endTime;
	private int sumAo;
	private int sumUo;
	private int sumEo;
	private int recordNum; //当前页包含记录数
	private int pageNo;    //页面编号
	private int pageCount; //页面总数
	private int totalCount; //记录总数
	
	// 状态描述字段
	private int c;    // 返回码，标识功能请求的执行结果。一般来说c=0表示执行正确，其他非0的均可能是某种错误，需要从e和m中获取详细信息
	public List<TerminalBizRecord> getTerminalBizRecord() {
		return terminalBizRecord;
	}

	public void setTerminalBizRecord(List<TerminalBizRecord> terminalBizRecord) {
		this.terminalBizRecord = terminalBizRecord;
	}

	private String e; // FunctionException  接口功能异常，是异常堆栈信息，包含完整的接口失败情况描述
	private String m; // bstr      接口错误返回描述信息
	
	// 详细记录列表
	private List<VehicRecord> vehicRecord;     // list of vehicle	
	private List<TerminalRecord> terminalRecord;     // list of terminal
	private List<TerminalBizRecord> terminalBizRecord;
 
	
	private String simNo;  //车辆sim卡号
	private String vehicName; //车辆名称
	
	
	//liuja增加start
    private double mileage;
    private String stuState;
    private String latitude;
    private String longitude;
    private String direction;
    private String speed;
    
    

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public String getStuState() {
		return stuState;
	}

	public void setStuState(String stuState) {
		this.stuState = stuState;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}
	//liuja 增加 end

	public ResponseBean() {
		olxDir = null;
		olxVersion = null;
		olxCompress=null;
		functionName = null;
		functionSeq = null;
		treeObjectVersion = null;

		beginTime = null;
		endTime = null;
		sumAo = 0;
		sumUo = 0;
		sumEo = 0;
		recordNum = 0;   //当前页包含记录数
		pageNo = 0;      //页面编号
	    pageCount = 0;  //页面总数
	    totalCount = 0; //记录总数
		vehicRecord = null;
	 
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}
	public List<TerminalRecord> getTerminalRecord() {
		return terminalRecord;
	}

	public void setTerminalRecord(List<TerminalRecord> terminalRecord) {
		this.terminalRecord = terminalRecord;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getSumAo() {
		return sumAo;
	}

	public void setSumAo(int sumAo) {
		this.sumAo = sumAo;
	}

	public int getSumUo() {
		return sumUo;
	}

	public void setSumUo(int sumUo) {
		this.sumUo = sumUo;
	}
	
	public int getSumEo() {
        return sumEo;
    }

    public void setSumEo(int sumEo) {
        this.sumEo = sumEo;
    }
	
	public int getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
	}

 
	 
	public List<VehicRecord> getVehicRecord() {
		return vehicRecord;
	}

	public void setVehicRecord(List<VehicRecord> vehicRecord) {
		this.vehicRecord = vehicRecord;
	}

	public String getOlxDir() {
		return olxDir;
	}

	public void setOlxDir(String olxDir) {
		this.olxDir = olxDir;
	}

	public String getOlxVersion() {
		return olxVersion;
	}

	public void setOlxVersion(String olxVersion) {
		this.olxVersion = olxVersion;
	}
	
	public String getOlxCompress() {
		return olxCompress;
	}

	public void setOlxCompress(String olxCompress) {
		this.olxCompress = olxCompress;
	}
	
	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionSeq() {
		return functionSeq;
	}

	public void setFunctionSeq(String functionSeq) {
		this.functionSeq = functionSeq;
	}

	public String getTreeObjectVersion() {
		return treeObjectVersion;
	}

	public void setTreeObjectVersion(String treeObjectVersion) {
		this.treeObjectVersion = treeObjectVersion;
	}
	public void printBeanData() {
		System.out.println(String.format("olxDir = %s ", olxDir));
		System.out.println(String.format("olxVersion = %s ", olxVersion));
		System.out.println(String.format("functionName = %s ", functionName));
		System.out.println(String.format("functionSeq = %s ", functionSeq));
		System.out.println(String.format("treeObjectVersion = %s ", treeObjectVersion));
		//
		System.out.println(String.format("beginTime = %s ", beginTime));
		System.out.println(String.format("endTime = %s ", endTime));
		System.out.println(String.format("sumAo = %d ", sumAo));
		System.out.println(String.format("sumUo = %d ", sumUo));
		System.out.println(String.format("sumEo = %d ", sumEo));
		System.out.println(String.format("recordNum = %d ", recordNum));

		//
		if (vehicRecord != null) {
			System.out.println(String.format("listSummary =  "));
			for (VehicRecord record : vehicRecord) {
				System.out.println(record.toString());
			}
		}
		 
	}
	
	@Override
	public  String  toString() {
	    StringBuffer str = new StringBuffer("");
	    str.append(String.format("olxDir = %s \n", olxDir));
	    str.append(String.format("olxVersion = %s \n", olxVersion));
	    str.append(String.format("functionName = %s \n", functionName));
	    str.append(String.format("functionSeq = %s \n", functionSeq));
	    str.append(String.format("treeObjectVersion = %s \n", treeObjectVersion));
        //
	    str.append(String.format("beginTime = %s \n", beginTime));
	    str.append(String.format("endTime = %s \n", endTime));
	    str.append(String.format("sumAo = %d \n", sumAo));
	    str.append(String.format("sumUo = %d \n", sumUo));
	    str.append(String.format("sumEo = %d \n", sumEo));
	    str.append(String.format("recordNum = %d \n", recordNum));

        //
        if (vehicRecord != null) {
            str.append(String.format("listSummary =  \n"));
            for (VehicRecord record : vehicRecord) {
                str.append(record.toString());
            }
        } 
       
        return str.toString();
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getSimNo() {
        return simNo;
    }

    public void setSimNo(String simNo) {
        this.simNo = simNo;
    }

    public String getVehicName() {
        return vehicName;
    }

    public void setVehicName(String vehicName) {
        this.vehicName = vehicName;
    }
}
