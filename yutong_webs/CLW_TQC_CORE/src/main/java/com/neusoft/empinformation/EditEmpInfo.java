package com.neusoft.empinformation;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.neusoft.empinformation.bean.EmpInfo;

public class EditEmpInfo {
	
	public static void main(String[] args) {  
        Connection oracle_conn = null;  
        Statement oracle_stmt = null;  
        ResultSet oracle_rs = null;  
          
        Connection mssql_conn = null;  
        Statement mssql_stmt = null;  
        ResultSet mssql_rs = null;  
                  
        try {  
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            oracle_conn = DriverManager.getConnection("jdbc:oracle:thin:@10.8.1.243:1521:orcl", "clw_tqc", "tqc"); 
            //oracle_conn = DriverManager.getConnection("jdbc:oracle:thin:@10.8.1.167:1521:CLW", "clw_tqc", "tqc110");
            //jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=10.8.1.243)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME =ORCL)(SERVER=DEDICATED))) 
            oracle_stmt = oracle_conn.createStatement();
            
            
              
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            //mssql_conn = DriverManager.getConnection("jdbc:sqlserver://192.168.0.213:1433;DatabaseName=VIS", "username", "password");  
              
            //mssql_stmt = mssql_conn.createStatement();  
            //mssql_rs = mssql_stmt.executeQuery("select * from VideoBaseInfo");
            
            File file = new File("f:\\emp\\emp.xls");
    		ExcelReader parser = new ExcelReader(file);
    		List<List<Object>> datas = parser.getDatasInSheet(0);
    		
    		List<EmpInfo> emp_list = new ArrayList<EmpInfo>();
    		
    		MD5 getMD5 = new MD5();
            //System.out.println(getMD5.getMD5Str("000000"));
//    		for (int i = 1; i < datas.size(); i++) {
//    			List row = datas.get(i);
//    			EmpInfo ab = new EmpInfo();
//    			ab.setEmp_code(String.valueOf(row.get(1)));   //员工号
//    			ab.setEmp_name(String.valueOf(row.get(2)));   //姓名
//    			ab.setEip_tel(String.valueOf(row.get(3)));    //eip电话
//    			ab.setId_card_no(String.valueOf(row.get(4))); //身份证号
//    			ab.setEmp_name_short(String.valueOf(row.get(5)));   //姓名简称   EIP登录名
//    			//获得密码
//    			if( !"".equals(ab.getId_card_no()) && ab.getId_card_no().length() >= 6){
//    				ab.setDefault_pwd(ab.getId_card_no().substring(ab.getId_card_no().length()-6,ab.getId_card_no().length()));
//    				System.out.println(getMD5.getMD5Str(ab.getDefault_pwd()));
//    				ab.setDefault_pwd(getMD5.getMD5Str(ab.getDefault_pwd()));
//    			}
//    			else{
//    				ab.setDefault_pwd("");
//    			}
//    			//获得邮箱
//    			if( !"".equals(ab.getEmp_name_short()) && ab.getEmp_name_short().length()>0){
//    				ab.setAccount_email(ab.getEmp_name_short()+ "@YUTONG.COM" );
//    				System.out.println(ab.getAccount_email());
//    			}
//    			else{
//    				ab.setAccount_email("");
//    			}
//    			
//    			
//    			
//    			emp_list.add(ab);
//    			//System.out.println(ab.getEmp_code()+"---"+ab.getEmp_name()+"---"+ab.getEip_tel()+"---"+ab.getId_card_no()+"\t");
//    		}
//    		for(EmpInfo ms : emp_list)
//    		{
//    			  oracle_stmt.executeUpdate("insert into clw_tqc_ygb.clw_bsp_account_t (account_id, account_name, account_pwd, account_code,account_email )  values ( clw_tqc_ygb.EMP_ID_SEQ.NEXTVAL ,"    //员工编码
//                  		+ "'" + ms.getEmp_name_short()  + "','"     //姓名简称
//                  		+ ms.getDefault_pwd() + "',"      //员工姓名
//                  		+ ms.getEmp_code()  + ",'"     //员工号
//                  		+ ms.getAccount_email()  + "'"     //邮箱
//                          + ")"); 
//    			//System.out.println("结束");
//
//
//            }
            String phone;
    		for (int i = 1; i < datas.size(); i++) {
    			List row = datas.get(i);
    			EmpInfo ab = new EmpInfo();
    			ab.setEmp_code(String.valueOf(row.get(1)));   //员工号
    			//System.out.println(ab.getEmp_code());
    			ab.setEmp_name(String.valueOf(row.get(2)));   //姓名
    			phone = StringFilter(String.valueOf(row.get(3)));
//    			if(null != phone && phone.length() > 0 && !phone.equals(String.valueOf(row.get(3)))){
//    				System.out.println("电话号码不同：" + phone + "原电话：" + String.valueOf(row.get(3)));
//    			}
    			if(!"".equals(phone)&&phone.length()>=11 && isMobileNO(phone.substring(0, 11))){
    				ab.setEip_tel(phone.substring(0, 11));    //eip电话    
    			}
    			else if(!"".equals(phone)&&phone.length()>=11 && isMobileNO(phone.substring(phone.length()-11, phone.length()))){
    				ab.setEip_tel(phone.substring(phone.length()-11, phone.length()));    //eip电话
    			}
    			else{
    				ab.setEip_tel("");
    			}
    			ab.setId_card_no(String.valueOf(row.get(4))); //身份证号
    			ab.setEmp_name_short(String.valueOf(row.get(5)));   //姓名简称   EIP登录名
    			//获得密码
    			if( !"".equals(ab.getId_card_no()) && ab.getId_card_no().length() >= 6){
    				ab.setDefault_pwd(ab.getId_card_no().substring(ab.getId_card_no().length()-6,ab.getId_card_no().length()));
    				//System.out.println(getMD5.getMD5Str(ab.getDefault_pwd()));
    				ab.setDefault_pwd(getMD5.getMD5Str(ab.getDefault_pwd()));
    			}
    			//获得出生年月日
    			if( !"".equals(ab.getId_card_no()) && ab.getId_card_no().length() >= 17){
    				ab.setEmp_birth(ab.getId_card_no().substring(ab.getId_card_no().length()-12,ab.getId_card_no().length()-4));
    				//System.out.println(ab.getEmp_birth());
    			}
    			else if(!"".equals(ab.getId_card_no()) && ab.getId_card_no().length() >= 14 &&  ab.getId_card_no().length()<17){
    				ab.setEmp_birth("19" + ab.getId_card_no().substring(ab.getId_card_no().length()-9,ab.getId_card_no().length()-3));
    			}
    			else{
    				ab.setDefault_pwd("");
    			}
    			//获得邮箱
    			if( !"".equals(ab.getEmp_name_short()) && ab.getEmp_name_short().length()>0){
    				ab.setAccount_email(ab.getEmp_name_short()+ "@YUTONG.COM" );
    				//System.out.println(ab.getAccount_email());
    			}
    			else{
    				ab.setAccount_email("");
    			}
    			/*if(null != phone && phone.length() > 0 && !phone.equals(String.valueOf(row.get(3)))){
    				System.out.println("电话号码不同：" + phone + "原电话：" + String.valueOf(row.get(3)));
	    			System.out.println("员工号：" + ab.getEmp_code());
	              oracle_stmt.executeUpdate("update  clw_tqc_ygb.clw_tqc_emp_t t set t.eip_tel = "
	                + "'" + ab.getEip_tel() + "'" 
	                + " where t.emp_code = " 
	                + "'" + ab.getEmp_code() +"'" 
	                + " and t.eip_tel is null "
	                );

    			}*/
    			if(null != ab.getEip_tel() && ab.getEip_tel().length() > 0 &&  !isMobileNO1(ab.getEip_tel())  &&  isMobileNO(ab.getEip_tel())     ){
    				
    				System.out.println("电话号码不同：" + phone + "原电话：" + String.valueOf(row.get(3)));
	    			System.out.println("员工号：" + ab.getEmp_code());
	              oracle_stmt.executeUpdate("update  clw_tqc_ygb.clw_tqc_emp_t t set t.eip_tel = "
	                + "'" + ab.getEip_tel() + "'" 
	                + " where t.emp_code = " 
	                + "'" + ab.getEmp_code() +"'" 
	                + " and t.eip_tel is null "
	                );
	              }

    			//emp_list.add(ab);
    			//System.out.println(ab.getEmp_code()+"---"+ab.getEmp_name()+"---"+ab.getEip_tel()+"---"+ab.getId_card_no()+"\t");
    		}

    		
              
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if(oracle_rs != null) {  
                    oracle_rs.close();  
                    oracle_rs = null;  
                }  
                  
                if(oracle_stmt != null) {  
                    oracle_stmt.close();  
                    oracle_stmt = null;  
                }  
                  
                if(oracle_conn != null) {  
                    oracle_conn.close();  
                    oracle_conn = null;  
                }  
                  
                if(mssql_rs != null) {  
                    mssql_rs.close();  
                    mssql_rs = null;  
                }  
                  
                if(mssql_stmt != null) {  
                    mssql_stmt.close();  
                    mssql_stmt = null;  
                }  
                  
                if(mssql_conn != null) {  
                    mssql_conn.close();  
                    mssql_conn = null;  
                }  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    } 
	
	/**
	  * 验证手机号码
	  * @param mobiles
	  * @return
	  */
	 public static boolean isMobileNO1(String mobiles){
	  boolean flag = false;
	  try{
	   Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
	   Matcher m = p.matcher(mobiles);
	   flag = m.matches();
	  }catch(Exception e){
	   //LOG.error("验证手机号码错误", e);
		System.out.println("验证手机号码错误"+ e);
	   flag = false;
	  }
	  return flag;
	 }
	 
	 
		/**
	  * 验证手机号码
	  * @param mobiles
	  * @return
	  */
	 public static boolean isMobileNO(String mobiles){
	  boolean flag = false;
	  try{
	   Pattern p = Pattern.compile("^(1[0-9])\\d{9}$");
	   Matcher m = p.matcher(mobiles);
	   flag = m.matches();
	  }catch(Exception e){
	   //LOG.error("验证手机号码错误", e);
		System.out.println("验证手机号码错误"+ e);
	   flag = false;
	  }
	  return flag;
	 }
	 
	// 手机号过滤特殊字符   
	    public   static   String StringFilter(String   str)   //throws   PatternSyntaxException   
	    {      
	                // 只允许字母和数字        
	                // String   regEx  =  "[^a-zA-Z0-9]";                      
	                   // 清除掉所有特殊字符   
	          String regEx="[`~! @#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]";   
	          Pattern   p   =   Pattern.compile(regEx);      
	          Matcher   m   =   p.matcher(str);      
	          return   m.replaceAll("").trim();      
	          }      
       
//	    public    void   testStringFilter()   throws   PatternSyntaxException   {      
//	          String   str   =   "*adCVs*34_a _09_b5*[/435^*&城池()^$$&*).{}+.|.)%%*(*.中国}34{45[]12.fd'*&999下面是中文的字符￥……{}【】。，；’“‘”？";      
//	          System.out.println(str);      
//	          System.out.println(StringFilter(str));      
//	          }    

	
	

}
