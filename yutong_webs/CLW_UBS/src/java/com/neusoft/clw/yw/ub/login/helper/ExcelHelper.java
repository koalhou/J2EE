/*******************************************************************************
 * @FileName: ExcelHelper.java 2013-7-18 上午10:05:08
 * @Author: zhangzhia
 * @Copyright: 2013 YUTONG Group CLW. All rights reserved.
 * @Remarks: YUTONG PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.ub.login.helper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.neusoft.clw.yw.ub.login.ds.LoginDetailDataInfo;
import com.neusoft.clw.yw.ub.login.ds.LoginStaticsDataInfo;

/**
 * @author zhangzhia 2013-7-18 上午10:05:08
 * 
 */
public class ExcelHelper {

	private static String statis_month = "";
	
	/** *********创建字体**************** */

	private static WritableFont TitileFont; // 数据表标题字体
	private static WritableFont CellTitleFont; // 数据列标题字体
	private static WritableFont textTitleFont; // 数据内容字体

	/** ************以下设置几种格式的单元格属性************ */

	private static WritableCellFormat wcf_header; // 标题样式
	private static WritableCellFormat wcf_titile; // 用于数据标题
	private static WritableCellFormat wcf_text; // 用于数据内容

	public static void exportExcel(Map<String, Object> mapExportList, File file)
			throws WriteException, IOException {
		// 导出数据
		List<LoginStaticsDataInfo> staticsList = (List<LoginStaticsDataInfo>) mapExportList
				.get("AllList");
		List<LoginStaticsDataInfo> staticsA_AreaList = (List<LoginStaticsDataInfo>) mapExportList
				.get("A_AreaList");
		List<LoginStaticsDataInfo> staticsB_AreaList = (List<LoginStaticsDataInfo>) mapExportList
				.get("B_AreaList");
		List<LoginStaticsDataInfo> staticsC_AreaList = (List<LoginStaticsDataInfo>) mapExportList
				.get("C_AreaList");
		List<LoginDetailDataInfo> detailList = (List<LoginDetailDataInfo>) mapExportList
				.get("DetailList");

		/** ********* 初始化字体 **************** */
		// BlodFont = new WritableFont(WritableFont.ARIAL, 12,
		// WritableFont.BOLD);
		// NormalFont = new WritableFont(WritableFont.ARIAL, 11);
		// BarCodeFont = new WritableFont(WritableFont.ARIAL, 11,
		// WritableFont.BOLD);

		TitileFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD); // 数据表标题字体
		CellTitleFont = new WritableFont(WritableFont.ARIAL, 11,
				WritableFont.BOLD); // 数据列标题字体
		textTitleFont = new WritableFont(WritableFont.ARIAL, 11); // 数据内容字体

		/** ********* 初始化单元格属性 **************** */
		// 初始化标题样式
		wcf_header = new WritableCellFormat(TitileFont, NumberFormats.TEXT); // 实例化单元格格式对象（标题、居中）
		wcf_header.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcf_header.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcf_header.setAlignment(Alignment.CENTRE); // 水平对齐
		wcf_header.setBackground(Colour.YELLOW2); // 背景颜色
		wcf_header.setWrap(true); // 是否换行

		// 用于数据标题
		wcf_titile = new WritableCellFormat(CellTitleFont); // 实例化单元格格式对象（正文、左对齐）
		wcf_titile.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcf_titile.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcf_titile.setAlignment(Alignment.CENTRE);
		wcf_titile.setLocked(false); // 设置锁定，还得设置SheetSettings启用保护和设置密码
		wcf_titile.setWrap(true); // 是否换行

		// 用于数据文本
		wcf_text = new WritableCellFormat(textTitleFont); // 实例化单元格格式对象（正文、居中）
		wcf_text.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcf_text.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcf_text.setAlignment(Alignment.LEFT);
		wcf_text.setLocked(true); // 设置锁定，还得设置SheetSettings启用保护和设置密码
		wcf_text.setWrap(true); // 是否换行

		/** ********** 创建工作薄 ********** **/
		WritableWorkbook workbook = Workbook.createWorkbook(file);

		outAllStatTable(staticsList, staticsA_AreaList, staticsB_AreaList, staticsC_AreaList, workbook);
		
		
		
		outDetailTable(detailList, workbook);

		/** 写到文件中 */
		workbook.write();
		/** 关闭文件 */
		workbook.close();
	}

	/**
	 *@author zhangzhia 2013-7-18 上午11:43:29
	 *
	 * @param staticsList
	 * @param workbook
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private static void outAllStatTable(List<LoginStaticsDataInfo> staticsList, 
			List<LoginStaticsDataInfo> staticsA_AreaList,
			List<LoginStaticsDataInfo> staticsB_AreaList,
			List<LoginStaticsDataInfo> staticsC_AreaList,
			WritableWorkbook workbook) throws WriteException,
			RowsExceededException {
		/** ********** 创建明细工作表 ********** **/
		WritableSheet sheet = workbook.createSheet("汇总", 0);

		/** ********* 打印属性 **************** */
		sheet.getSettings().setOrientation(PageOrientation.LANDSCAPE); // 设置为横向打印
		sheet.getSettings().setPaperSize(PaperSize.A4); // 设置纸张
		sheet.getSettings().setFitHeight(297); // 打印区高度
		sheet.getSettings().setFitWidth(210); // 打印区宽度
		// 设置列宽
		sheet.setColumnView(0, 15); // 第1列
		sheet.setColumnView(1, 15); // 第2列
		sheet.setColumnView(2, 15);
		sheet.setColumnView(3, 10);
		
		//客户分类标准
		sheet.setColumnView(6, 10);
		sheet.setColumnView(7, 80);
		
		// 设置边距
		sheet.getSettings().setTopMargin(0.5);
		sheet.getSettings().setBottomMargin(0.3);
		sheet.getSettings().setLeftMargin(0.1);
		sheet.getSettings().setRightMargin(0.1);
		// 设置页脚
		sheet.getSettings().getFooter().getCentre().appendPageNumber(); // 为页脚添加页数
		sheet.getSettings().setFooterMargin(0.07); // 设置页脚边距（下）
		// 设置打印标题行
		sheet.getSettings().setPrintHeaders(true); // 启用打印头信息
		// sheet.getSettings().setPrintTitlesRow(0, 0) ; // 设置标题行

		
		// 客户类别月度客户活跃报表
		
		String title = "安芯" + statis_month + "月份客户活跃度报表";
		
		int startidx = 0;
		sheet.mergeCells(0,startidx,3,startidx);
        sheet.addCell(new Label(0, startidx, title, wcf_header));  

        startidx++;
		sheet.addCell(new Label(0, startidx, "客户名称", wcf_titile));
		sheet.addCell(new Label(1, startidx, "数量", wcf_titile));
		sheet.addCell(new Label(2, startidx, "活跃客户", wcf_titile));
		sheet.addCell(new Label(3, startidx, "活跃比例", wcf_titile));
		
		startidx++;
		if(staticsList.size() > 0)
		{
			for (int i = 0; i < staticsList.size(); i++, startidx++) {
				LoginStaticsDataInfo data = (LoginStaticsDataInfo) staticsList.get(i);
				sheet.setRowView(startidx, 400);
				sheet.addCell(new Label(0, startidx, data.getSystemname(), wcf_text));
				sheet.addCell(new Number(1, startidx, data.getEpCount(), wcf_text));
				sheet.addCell(new Number(2, startidx, data.getActiveCount(), wcf_text));
				sheet.addCell(new Label(3, startidx, data.getActivePercentStr(), wcf_text));
			}
		}
		else
		{
			sheet.setRowView(startidx, 400);
			sheet.mergeCells(0,startidx,3,startidx);
	        sheet.addCell(new Label(0, startidx, "此月份无数据！", wcf_titile));  
	      
		}
		
		sheet.setRowView(startidx, 400);
		sheet.mergeCells(0,startidx,3,startidx);
        sheet.addCell(new Label(0, startidx, "注：活跃度口径，月登录次数大于等于8次的为活跃客户", wcf_text));  
        startidx++;
        // 客户分类标准
		
		title = "客户分类标准";
		
		int typeidx = 0;
		sheet.mergeCells(6,typeidx,7,typeidx);
        sheet.addCell(new Label(6, typeidx, title, wcf_header));  

        typeidx++;
		sheet.addCell(new Label(6, typeidx, "客户类别", wcf_titile));
		sheet.addCell(new Label(7, typeidx, "客户类别说明", wcf_titile));
		
		typeidx++;		
		sheet.setRowView(startidx, 400);
		sheet.addCell(new Label(6, typeidx, "A类", wcf_text));
		sheet.addCell(new Label(7, typeidx, "专业校车公司、客运公司、公交公司、民营校车公司等类似公司", wcf_text));
		
		typeidx++;		
		sheet.setRowView(startidx, 400);
		sheet.addCell(new Label(6, typeidx, "B类", wcf_text));
		sheet.addCell(new Label(7, typeidx, "教育局等政府机构运营和注册车辆超过5台的批量私有学校、幼儿园", wcf_text));
		
		typeidx++;
		sheet.setRowView(startidx, 400);
		sheet.addCell(new Label(6, typeidx, "C类", wcf_text));
		sheet.addCell(new Label(7, typeidx, "5台及以下的私有学校、幼儿园", wcf_text));
		
		
        	
        
		// 类型A区域月度客户活跃报表

		title = "安芯 A 类客户" + statis_month + "月份客户活跃度报表";
		
		startidx +=4;
		sheet.mergeCells(0,startidx,3,startidx);
        sheet.addCell(new Label(0, startidx, title, wcf_header));  

        startidx++;
		sheet.addCell(new Label(0, startidx, "区域", wcf_titile));
		sheet.addCell(new Label(1, startidx, "客户总数量", wcf_titile));
		sheet.addCell(new Label(2, startidx, "活跃客户", wcf_titile));
		sheet.addCell(new Label(3, startidx, "活跃度", wcf_titile));
		
		startidx++;
		for (int i = 0; i < staticsA_AreaList.size(); i++, startidx++) {
			LoginStaticsDataInfo data = (LoginStaticsDataInfo) staticsA_AreaList.get(i);
			sheet.setRowView(startidx, 400);
			sheet.addCell(new Label(0, startidx, data.getSystemname(), wcf_text));
			sheet.addCell(new Number(1, startidx, data.getEpCount(), wcf_text));
			sheet.addCell(new Number(2, startidx, data.getActiveCount(), wcf_text));
			sheet.addCell(new Label(3, startidx, data.getActivePercentStr(), wcf_text));
		}

		// 类型B区域月度客户活跃报表

		title = "安芯 B 类客户" + statis_month + "月份客户活跃度报表";
		
		startidx +=4;
		sheet.mergeCells(0,startidx,3,startidx);
        sheet.addCell(new Label(0, startidx, title, wcf_header));  

        startidx++;
		sheet.addCell(new Label(0, startidx, "区域", wcf_titile));
		sheet.addCell(new Label(1, startidx, "客户总数量", wcf_titile));
		sheet.addCell(new Label(2, startidx, "活跃客户", wcf_titile));
		sheet.addCell(new Label(3, startidx, "活跃度", wcf_titile));
		
		startidx++;
		for (int i = 0; i < staticsB_AreaList.size(); i++, startidx++) {
			LoginStaticsDataInfo data = (LoginStaticsDataInfo) staticsB_AreaList.get(i);
			sheet.setRowView(startidx, 400);
			sheet.addCell(new Label(0, startidx, data.getSystemname(), wcf_text));
			sheet.addCell(new Number(1, startidx, data.getEpCount(), wcf_text));
			sheet.addCell(new Number(2, startidx, data.getActiveCount(), wcf_text));
			sheet.addCell(new Label(3, startidx, data.getActivePercentStr(), wcf_text));
		}
		

		// 类型C区域月度客户活跃报表

		title = "安芯 C 类客户" + statis_month + "月份客户活跃度报表";
		
		startidx +=4;
		sheet.mergeCells(0,startidx,3,startidx);
        sheet.addCell(new Label(0, startidx, title, wcf_header));  

        startidx++;
		sheet.addCell(new Label(0, startidx, "区域", wcf_titile));
		sheet.addCell(new Label(1, startidx, "客户总数量", wcf_titile));
		sheet.addCell(new Label(2, startidx, "活跃客户", wcf_titile));
		sheet.addCell(new Label(3, startidx, "活跃度", wcf_titile));
		
		startidx++;
		for (int i = 0; i < staticsC_AreaList.size(); i++, startidx++) {
			LoginStaticsDataInfo data = (LoginStaticsDataInfo) staticsC_AreaList.get(i);
			sheet.setRowView(startidx, 400);
			sheet.addCell(new Label(0, startidx, data.getSystemname(), wcf_text));
			sheet.addCell(new Number(1, startidx, data.getEpCount(), wcf_text));
			sheet.addCell(new Number(2, startidx, data.getActiveCount(), wcf_text));
			sheet.addCell(new Label(3, startidx, data.getActivePercentStr(), wcf_text));
		}
	}

	/**
	 *@author zhangzhia 2013-7-18 上午11:43:29
	 *
	 * @param detailList
	 * @param workbook
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private static void outDetailTable(List<LoginDetailDataInfo> detailList,
			WritableWorkbook workbook) throws WriteException,
			RowsExceededException {
		/** ********** 创建明细工作表 ********** **/
		WritableSheet sheet = workbook.createSheet("明细", 1);

		/** ********* 打印属性 **************** */
		sheet.getSettings().setOrientation(PageOrientation.LANDSCAPE); // 设置为横向打印
		sheet.getSettings().setPaperSize(PaperSize.A4); // 设置纸张
		sheet.getSettings().setFitHeight(297); // 打印区高度
		sheet.getSettings().setFitWidth(210); // 打印区宽度
		// 设置列宽
		sheet.setColumnView(0, 10); // 第1列
		sheet.setColumnView(1, 10); // 第2列
		sheet.setColumnView(2, 15);
		sheet.setColumnView(3, 30);
		sheet.setColumnView(4, 10);
		sheet.setColumnView(5, 30);
		sheet.setColumnView(6, 15);
		sheet.setColumnView(7, 15);
		sheet.setColumnView(8, 15);
		
		// 设置边距
		sheet.getSettings().setTopMargin(0.5);
		sheet.getSettings().setBottomMargin(0.3);
		sheet.getSettings().setLeftMargin(0.1);
		sheet.getSettings().setRightMargin(0.1);
		// 设置页脚
		sheet.getSettings().getFooter().getCentre().appendPageNumber(); // 为页脚添加页数
		sheet.getSettings().setFooterMargin(0.07); // 设置页脚边距（下）
		// 设置保护，并加密码 锁定的Cell才会起作用
		// 设置打印标题行
		sheet.getSettings().setPrintHeaders(true); // 启用打印头信息
		// sheet.getSettings().setPrintTitlesRow(0, 0) ; // 设置标题行

		/** *********插入标题内容**************** */
		sheet.addCell(new Label(0, 0, "省", wcf_titile));
		sheet.addCell(new Label(1, 0, "市", wcf_titile));
		sheet.addCell(new Label(2, 0, "企业编码", wcf_titile));
		sheet.addCell(new Label(3, 0, "企业名称", wcf_titile));
		sheet.addCell(new Label(4, 0, "企业类型", wcf_titile));
		sheet.addCell(new Label(5, 0, "客户经理", wcf_titile));
		sheet.addCell(new Label(6, 0, "登录次数", wcf_titile));
		sheet.addCell(new Label(7, 0, "是否活跃", wcf_titile));
		sheet.addCell(new Label(8, 0, "车辆数", wcf_titile));

		for (int i = 0, y = 1; i < detailList.size(); i++, y++) {
			LoginDetailDataInfo data = (LoginDetailDataInfo) detailList.get(i);
			sheet.setRowView(y, 400);
			sheet.addCell(new Label(0, y, data.getProvince(), wcf_text));
			sheet.addCell(new Label(1, y, data.getCity(), wcf_text));
			sheet.addCell(new Label(2, y, data.getEnterprise_code(), wcf_text));
			sheet.addCell(new Label(3, y, data.getEnterprise_name(), wcf_text));
			sheet.addCell(new Label(4, y, data.getCustomer_type(), wcf_text));
			sheet.addCell(new Label(5, y, data.getCustomer_manage(), wcf_text));
			sheet.addCell(new Number(6, y, data.getLogin_total(), wcf_text));
			sheet.addCell(new Label(7, y, data.getIs_active().toString(),
					wcf_text));
			sheet.addCell(new Number(8, y, data.getVehicle_total(), wcf_text));
		}
	}

	/**
	 * @return the statis_month
	 */
	public static String getStatis_month() {
		return statis_month;
	}

	/**
	 * @param statis_month the statis_month to set
	 */
	public static void setStatis_month(String year_month) {
		statis_month = year_month.substring(year_month.length() - 2);
	}
	
	
}
