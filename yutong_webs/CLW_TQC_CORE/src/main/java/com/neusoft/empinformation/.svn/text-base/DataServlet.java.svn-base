package com.neusoft.empinformation;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;  


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.neusoft.empinformation.bean.EmpInfo;

public class DataServlet extends HttpServlet{

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
            System.out.println(getMD5.getMD5Str("000000"));
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
    		for (int i = 1; i < datas.size(); i++) {
    			List row = datas.get(i);
    			EmpInfo ab = new EmpInfo();
    			ab.setEmp_code(String.valueOf(row.get(1)));   //员工号
    			System.out.println(ab.getEmp_code());
    			ab.setEmp_name(String.valueOf(row.get(2)));   //姓名
    			if(!"".equals(String.valueOf(row.get(3)))&&String.valueOf(row.get(3)).length()>=11 && isMobileNO(String.valueOf(row.get(3)).substring(0, 11)))
    				ab.setEip_tel(String.valueOf(row.get(3)).substring(0, 11));    //eip电话
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
    				System.out.println(ab.getAccount_email());
    			}
    			else{
    				ab.setAccount_email("");
    			}
    			ResultSet emp = oracle_stmt.executeQuery(" select account_id from clw_tqc_ygb.clw_bsp_account_t where account_code = " + "'" + ab.getEmp_code() +" '"+ " and  account_id not in (select t.emp_id account_id from clw_tqc_ygb.clw_tqc_emp_t t) "  );
        		while (emp.next()) {        
        			//EmpInfo board=new EmpInfo();
        			//(emp_ID,emp_code,emp_card_id,emp_name,emp_sex,emp_birth,sync_time,emp_belong,sync_flag,
        			//emp_name_short,photo_name;,vaset_time,vaset_user_id,valid_flag,modify_time,modify,create_time,creater,organization_id,enterprise_id,
        			//ygb_tel,eip_tel,emp_remark,emp_depart,account_id,emp_address,id_card_no,default_pwd,push_rule)
    				ab.setEmp_ID(emp.getString("account_id"));    
    				//board.setEmp_code(emp.getString("account_code"));    
    				//board.setEmp_card_id(emp.getString(""));    
    				//emp_list.add(board);       
    			
              oracle_stmt.executeUpdate("insert into clw_tqc_ygb.clw_tqc_emp_t (emp_id, emp_code, emp_name, emp_sex,emp_birth,sync_time,emp_belong,sync_flag,emp_name_short,valid_flag,creater,enterprise_id,eip_tel, account_id,id_card_no,default_pwd,push_rule) values( " 
                + "'" + ab.getEmp_ID() + "','"    //员工编码
        		+ ab.getEmp_code()  + "','"     //员工号
        		//+ ab.getEmp_card_id()  + "','"     //员工卡号，现在还没有值，还是空的
        		+ ab.getEmp_name() + "',"      //员工姓名
        		+  0 + "," //性别
        		+ " to_date(" + ab.getEmp_birth() + ",'yyyy-mm-dd'),"            //出生年月
        		+ " sysdate" + ","      //当前日期
        		+  1 + ", "  //所属厂区
        		+  0 + ",'"  //同步成功 0：成功 1失败
        		+ ab.getEmp_name_short() + "',"      //员工登录EIP的用户名
        		+  0 + ",'"    //有效 0有效  1无效 
        		+ "liuja ',"
        		+  1 + ",'"   //组织
        		+ ab.getEip_tel()  + " ','"   //EIP电话
        		+ ab.getEmp_ID()  + " ','"   //BSP账户
        		+ ab.getId_card_no() + "','" 
        		+ ab.getDefault_pwd() + "'," 
        		+  11111111  //性别
                + ")");
    			
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
	 public static boolean isMobileNO(String mobiles){
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
  
}