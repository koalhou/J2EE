package com.neusoft.empinformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.neusoft.empinformation.bean.EmpInfo;

public class ExcelReader {
	// log4j
	private Logger logger = Logger.getLogger(ExcelReader.class);

	private HSSFWorkbook workbook;// 工作簿

	public ExcelReader(File file) {
		try {
			// 获取工作薄workbook
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<List<Object>> getDatasInSheet(int sheetNumber) {
		List<List<Object>> result = new ArrayList<List<Object>>();

		// 获得指定的sheet
		HSSFSheet sheet = workbook.getSheetAt(sheetNumber);
		// 获得sheet总行数
		int rowCount = sheet.getLastRowNum();
		logger.info("found excel rows count:" + rowCount);
		if (rowCount < 1) {
			return result;
		}

		// 遍历行row
		for (int rowIndex = 0; rowIndex <= rowCount; rowIndex++) {
			// 获得行对象
			HSSFRow row = sheet.getRow(rowIndex);
			if (null != row) {
				List<Object> rowData = new ArrayList<Object>();
				// 获得本行中单元格的个数
				int cellCount = row.getLastCellNum();
				// 遍历列cell
				for (short cellIndex = 0; cellIndex < cellCount; cellIndex++) {
					HSSFCell cell = row.getCell(cellIndex);
					// 获得指定单元格中的数据
					Object cellStr = this.getCellString(cell);

					rowData.add(cellStr);
				}
				result.add(rowData);
			}
		}

		return result;
	}
	public static final DecimalFormat df = new DecimalFormat("#");
	private Object getCellString(HSSFCell cell) {
		// TODO Auto-generated method stub
		Object result = null;
		if (cell != null) {
			// 单元格类型：Numeric:0,String:1,Formula:2,Blank:3,Boolean:4,Error:5
			int cellType = cell.getCellType();
			switch (cellType) {
			case HSSFCell.CELL_TYPE_STRING:
				result = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				//result = cell.getNumericCellValue();
				result = df.format(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				result = cell.getNumericCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				result = cell.getBooleanCellValue();
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				result = null;
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				result = null;
				break;
			default:
				System.out.println("枚举了所有类型");
				break;
			}
		}
		return result;
	}

	// test
	public static void main(String[] args) {
	    File file = new File("f:\\emp\\emp.xls");
		ExcelReader parser = new ExcelReader(file);
		List<List<Object>> datas = parser.getDatasInSheet(0);
		
		List<EmpInfo> emp_list = new ArrayList<EmpInfo>();
		for (int i = 1; i < datas.size(); i++) {
			List row = datas.get(i);
			EmpInfo ab = new EmpInfo();
			ab.setEmp_code(String.valueOf(row.get(1)));
			ab.setEmp_name(String.valueOf(row.get(2)));
			ab.setEip_tel(String.valueOf(row.get(3)));
			ab.setId_card_no(String.valueOf(row.get(4)));
			
			emp_list.add(ab);
			//System.out.println(ab.getEmp_code()+"---"+ab.getEmp_name()+"---"+ab.getEip_tel()+"---"+ab.getId_card_no()+"\t");
		}
		for(EmpInfo mm : emp_list)
		{
			System.out.println(mm.getEmp_code()+"---"+mm.getEmp_name()+"---"+mm.getEip_tel()+"---"+mm.getId_card_no()+"\t");
		}
          
		}
	}

