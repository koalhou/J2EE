package com.neusoft.clw.yw.ftly.ds;

import java.util.Comparator;
import java.util.Date;


public class ComparatorFtlyInfo implements Comparator {

		 public int compare(Object arg0, Object arg1) {
			  ZsptFtlyInfo ftlyInfo=(ZsptFtlyInfo)arg0;
			  ZsptFtlyInfo ftlyInfo1=(ZsptFtlyInfo)arg1;
	
			   //首先比较年龄，如果年龄相同，则比较名字
//			  Long info0 = Long.valueOf(ftlyInfo.getReportTimeString().replaceAll("[-\\s:]",""));
//			  Long info1 = Long.valueOf(ftlyInfo1.getReportTimeString().replaceAll("[-\\s:]",""));
			  Date info0 = ftlyInfo.getReportTime();
			  Date info1 = ftlyInfo1.getReportTime();
			  int flag=info0.compareTo(info1);
			  if(flag==0){
				  return ftlyInfo.getVinCode().compareTo(ftlyInfo1.getVinCode());
			  }else{
				  return flag;
			  }  
		 }
		 
}


	

