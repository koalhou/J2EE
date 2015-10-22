package com.neusoft.clw.yw.ftly.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import com.neusoft.clw.yw.ftly.ds.ZsptFtlyInfo;

import com.neusoft.clw.yw.ftly.service.FtlyInfoService;
/**
 * @author ytzspt
 * @version Revision: 1.1 @date : 2012-12-17 上午09:08:39
 */
public class FtlyInfoServiceImpl implements FtlyInfoService{

	
	public InputStream getExcelInputStream(List dataList) {
		
		jxl.write.Label label;
		WritableWorkbook workbook;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook = Workbook.createWorkbook(out);
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			
			sheet.setColumnView(0,13); //调整第一列宽度    
			sheet.setColumnView(1,20); //调整第二列宽度    
			sheet.setColumnView(2,18); //调整第三列宽度    
			sheet.setColumnView(3,15); //调整第四列宽度    
			sheet.setColumnView(4,15); //调整第五列宽度    
			sheet.setColumnView(5,15); //调整第六列宽度    
			sheet.setColumnView(6,15); //调整第七列宽度   	
			sheet.setColumnView(7,15); //调整第八列宽度    
			sheet.setColumnView(8,15); //调整第九列宽度    
			sheet.setColumnView(9,15); //调整第十列宽度   	
			sheet.setColumnView(10,15); //调整第十一列宽度    
			sheet.setColumnView(11,15); //调整第十二列宽度   
			

			label = new jxl.write.Label(0, 0, "车牌号");
			sheet.addCell(label);
			
			label = new jxl.write.Label(1, 0, "上报时间");
			sheet.addCell(label);
			
			label = new jxl.write.Label(2, 0, "油箱油位异常类型");
			sheet.addCell(label);
			
			label = new jxl.write.Label(3, 0, "油箱油位");
			sheet.addCell(label);
			
			label = new jxl.write.Label(4, 0, "油箱油位变化");
			sheet.addCell(label);
			
			label = new jxl.write.Label(5, 0, "油箱油量");
			sheet.addCell(label);
			
			label = new jxl.write.Label(6, 0, "纬度");
			sheet.addCell(label);
			
			label = new jxl.write.Label(7, 0, "经度");
			sheet.addCell(label);
			
			label = new jxl.write.Label(8, 0, "海拔");
			sheet.addCell(label);
			
			label = new jxl.write.Label(9, 0, "方向");
			sheet.addCell(label);
			
			label = new jxl.write.Label(10, 0, "GPS速度");
			sheet.addCell(label);
			
			label = new jxl.write.Label(11, 0, "车速");
			sheet.addCell(label);
			
			for(int i=0;i<dataList.size();i++){
				
				ZsptFtlyInfo info = (ZsptFtlyInfo)dataList.get(i);
				String oilboxState = info.getOilboxState();
				
				if(oilboxState.equals("001")){
					
					jxl.write.WritableFont font = new  jxl.write.WritableFont(WritableFont.ARIAL, 10 ,WritableFont.BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.RED);    
					jxl.write.WritableCellFormat backgroud = new  jxl.write.WritableCellFormat(font);    
				//	backgroud.setBackground(jxl.format.Colour.WHITE);  
					
					label = new jxl.write.Label(0, i+1, info.getVehicleLn(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(1, i+1, info.getReportTimeString(),backgroud);
					sheet.addCell(label);
					
					
					label = new jxl.write.Label(2, i+1, "油量减少",backgroud);
					sheet.addCell(label);
					
					
					
					label = new jxl.write.Label(3, i+1, info.getOilboxLevel(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(4, i+1, info.getAddOill(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(5, i+1, info.getOilboxMass(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(6, i+1, info.getLatitude(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(7, i+1, info.getLongitude(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(8, i+1, info.getElevation(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(9, i+1, info.getDirection(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(10, i+1, info.getGpsSpeeding(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(11, i+1, info.getSpeeding(),backgroud);
					sheet.addCell(label);
					
				}
				
				else{
					
					label = new jxl.write.Label(0, i+1, info.getVehicleLn());
					sheet.addCell(label);
					
					label = new jxl.write.Label(1, i+1, info.getReportTimeString());
					sheet.addCell(label);
					
					
					if(oilboxState.equals("00")){
						label = new jxl.write.Label(2, i+1, "油位正常");
						sheet.addCell(label);
					}
					else if(oilboxState.equals("10")){		
						label = new jxl.write.Label(2, i+1, "加油告警");
						sheet.addCell(label);
					}else{
						label = new jxl.write.Label(2, i+1, "其他");
						sheet.addCell(label);
					}

					label = new jxl.write.Label(3, i+1, info.getOilboxLevel());
					sheet.addCell(label);
					
					label = new jxl.write.Label(4, i+1, info.getAddOill());
					sheet.addCell(label);
					
					label = new jxl.write.Label(5, i+1, info.getOilboxMass());
					sheet.addCell(label);
					
					label = new jxl.write.Label(6, i+1, info.getLatitude());
					sheet.addCell(label);
					
					label = new jxl.write.Label(7, i+1, info.getLongitude());
					sheet.addCell(label);
					
					label = new jxl.write.Label(8, i+1, info.getElevation());
					sheet.addCell(label);
					
					label = new jxl.write.Label(9, i+1, info.getDirection());
					sheet.addCell(label);
					
					label = new jxl.write.Label(10, i+1, info.getGpsSpeeding());
					sheet.addCell(label);
					
					label = new jxl.write.Label(11, i+1, info.getSpeeding());
					sheet.addCell(label);
					
				}
				
			}
			
			workbook.write();
			workbook.close();
			
			return new ByteArrayInputStream(out.toByteArray());   
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
