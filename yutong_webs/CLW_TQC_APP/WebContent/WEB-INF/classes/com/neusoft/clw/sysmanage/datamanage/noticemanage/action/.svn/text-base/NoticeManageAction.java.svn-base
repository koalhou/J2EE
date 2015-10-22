package com.neusoft.clw.sysmanage.datamanage.noticemanage.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.drivermanage.domain.DriverInfo;
import com.neusoft.clw.sysmanage.datamanage.noticemanage.domain.ClwBspUnstructurFileT;
import com.neusoft.clw.sysmanage.datamanage.noticemanage.domain.ClwTqcGonggaoT;
import com.neusoft.clw.sysmanage.datamanage.noticemanage.service.NoticeService;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 公告管理Action
 * @author changwt
 * @version 2013-11-07
 */
public class NoticeManageAction extends PaginationAction {

    private NoticeService noticeService;
	public NoticeService getNoticeService() {
		return noticeService;
	}
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	//json数据map
    private Map<String, String> map=new HashMap<String,String>();
    //状态查询map
    private Map<String, String> stateMap;
    //上传图片名
    private String fileuploadFileName;
    //图片id主键
    private File fileupload; // 和JSP中input标记name同名
    private ClwTqcGonggaoT gonggao;
    //文件路径
    private String filePath;
    //是否置顶
    private Boolean isCheck;
    
    //公告时间
    private Date gonggaoDate;
   
	//公告状态
    private String gonggaoState;

	public String getGonggaoState() {
		return gonggaoState;
	}


	public void setGonggaoState(String gonggaoState) {
		this.gonggaoState = gonggaoState;
	}
	
	public Date getGonggaoDate() {
		return gonggaoDate;
	}

	public void setGonggaoDate(Date gonggaoDate) {
		this.gonggaoDate = gonggaoDate;
	}

	public Boolean getIsCheck() {
		return isCheck;
	}


	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public File getFileupload() {
		return fileupload;
	}


	public void setFileupload(File fileupload) {
		this.fileupload = fileupload;
	}


	public String getFileuploadFileName() {
		return fileuploadFileName;
	}


	public void setFileuploadFileName(String fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}

	public Map<String, String> getStateMap() {
		return stateMap;
	}


	public void setStateMap(Map<String, String> stateMap) {
		this.stateMap = stateMap;
	}


	public Map<String, String> getMap() {
		return map;
	}


	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	/**
     * 进入公告管理页面
     * @return success
     */
    public String init() {
    	//初始化公告状态
    	this.stateMap=Constants.NOTICE_STATE_MAP;
        return SUCCESS;
    }
	/**
     * 公告管理列表
     * @return success
     */
    public String noticeList() {
    	String pageTitle="公告管理列表";
    	log.info(pageTitle);
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
        	gonggao=new ClwTqcGonggaoT();
    		gonggao.setGonggaoDate(this.gonggaoDate);
    		gonggao.setSendFlag(this.gonggaoState);
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortname = request.getParameter("sortname");
            String sortorder = request.getParameter("sortorder");
            gonggao.setSortname(sortname);
            gonggao.setSortorder(sortorder);
            int totalCount = 0;
            totalCount = noticeService.getCount("NoticeManage.getGonggaoCount", gonggao);
            List<ClwTqcGonggaoT> noticeList = (List <ClwTqcGonggaoT>) noticeService.getObjectsByPage("NoticeManage.getGonggaoList", gonggao, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getPagination(noticeList, totalCount, pageIndex, rpNum);// 转换map

            if (noticeList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 设置操作描述
            this.addOperationLog(formatLog(pageTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_NOTICE_QUERY);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(pageTitle, e);
            return ERROR;
        }catch (Exception e) {
            addActionError(getText(e.getMessage()));
            log.error(pageTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }
    /**
     * 进入公告添加页面
     * @return success
     */
    public String preAddNotice() {
    	//实例化公告信息
    	gonggao=new ClwTqcGonggaoT();
    	
        return SUCCESS;
    }
   


	/**
     * 公告添加到数据库
     * @return success
     */
    public String addNotice() {
    	String pageTitle="公告添加到数据库";
    	log.info(pageTitle);
    	try {
    		if(this.gonggao==null){
    			return ERROR;
    		}
    		UserInfo user = getCurrentUser();
    		gonggao.setGonggaoAuthor(user.getUserName());
    		//是否置顶显示
    		if(this.isCheck!=null&&this.isCheck==true){
    			gonggao.setIsTop("1");
    		}else{
    			gonggao.setIsTop("0");
    		}
    		//默认设置未推送
    		gonggao.setSendFlag(Constants.NOTICE_STATE_KEY_UNPUSH);
    		noticeService.insertNotice("NoticeManage.addGonggao", gonggao);
		} catch (Exception e) {
			log.error(pageTitle, e);
            addActionError(e.getMessage());
            return ERROR;
		}
        
        this.addOperationLog(formatLog(pageTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.INSERT);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_NOTICE_ADD);
        return SUCCESS;
    }
    public ClwTqcGonggaoT getGonggao() {
		return gonggao;
	}
	public void setGonggao(ClwTqcGonggaoT gonggao) {
		this.gonggao = gonggao;
	}
	/**
     * 置顶操作
     */
    public String upFirstNotice() {
    	String pageTitle="置顶操作";
    	log.info(pageTitle);
    	try {
    		gonggao=new ClwTqcGonggaoT();
    		HttpServletRequest request = ServletActionContext.getRequest();
    		String gonggaoId=request.getParameter("noticeId");
    		if(!"".equals(gonggaoId)){
    			gonggao.setGonggaoId(Integer.valueOf(gonggaoId));
    			gonggao.setIsTop("1");
    			noticeService.updateTop("NoticeManage.upFirstNotice", gonggao);
    		}
		} catch (Exception e) {
			log.error(pageTitle, e);
            addActionError(e.getMessage());
            return ERROR;
		}
        
        this.addOperationLog(formatLog(pageTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_NOTICE_UPDATE);
        
        return null;
    }
    /**
     * 查询公告信息通过ID
     */
    public String searchNoticeById() {
    	String pageTitle="查询公告信息ByID";
    	log.info(pageTitle);
    	try {
    		gonggao=new ClwTqcGonggaoT();
    		HttpServletRequest request = ServletActionContext.getRequest();
    		String gonggaoId=request.getParameter("noticeId");
    		if(!"".equals(gonggaoId)){
    			gonggao.setGonggaoId(Integer.valueOf(gonggaoId));
    			gonggao=(ClwTqcGonggaoT)noticeService.getObject("NoticeManage.searchGonggaoById", gonggao);
    		}
		} catch (Exception e) {
			log.error(pageTitle, e);
            addActionError(e.getMessage());
            return ERROR;
		}
        
        this.addOperationLog(formatLog(pageTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.SELECT);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_NOTICE_QUERY);
        
        return SUCCESS;
    }
    /**
     * 公告修改
     */
    public String updateNotice() {
    	String pageTitle="公告修改";
    	log.info(pageTitle);
    	try {
    		if(gonggao==null){
    			return ERROR;
    		}
    		if(gonggao.getGonggaoId()!=null){
    			//是否置顶显示
        		if(this.isCheck!=null&&this.isCheck==true){
        			gonggao.setIsTop("1");
        		}else{
        			gonggao.setIsTop("0");
        		}
        		noticeService.updateNotice("NoticeManage.updateGonggao", gonggao);
    		}
		} catch (Exception e) {
			log.error(pageTitle, e);
            addActionError(e.getMessage());
            return ERROR;
		}
        
        this.addOperationLog(formatLog(pageTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_NOTICE_UPDATE);
        
        return SUCCESS;
    }
    
    /**
     * 公告图片上传
     * @return success
     */
    public String addPic() {
    	
    	HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk"); // 务必，防止返回文件名是乱码
        // 保存文件拓展名
        String extName = ""; 
        String errTxt = "";
        BufferedInputStream in = null;
        FileInputStream fis = null;
        FileOutputStream fos=null;
        PrintWriter out=null;
        BufferedOutputStream bos=null;
        ClwBspUnstructurFileT fileInfo=null;
        // 获取拓展名
        if (fileuploadFileName.lastIndexOf(".") >= 0) {
            extName = fileuploadFileName.substring(fileuploadFileName
                    .lastIndexOf("."));
        }
        try {
        	out=response.getWriter();
        	//判断服务器配置路径是否存在
        	if(this.filePath==null || "".equals(this.filePath)){
            	errTxt = "<font color='red'>文件服务器路径不存在!</font>";
                out.print(errTxt);
              	return null;
            }
        	//文件上传目录
            filePath=this.filePath+File.separator+DateUtil.getCurrentDay()+File.separator;
            // 检查上传的是否是图片
            if (checkIsImage(extName)) {
                String strFileName = fileupload.getAbsolutePath().toLowerCase();
                fis = new FileInputStream(strFileName);
                in = new BufferedInputStream(fis);
                byte[] temp = new byte[1024];
            	//文件大小
                long s = fis.available()/1024;//单位为K
                if(s > Constants.NOTICE_UPLOAD_PIC_SIZE.longValue()){
                	errTxt = "<font color='red'>请上传不大于200K的图片!</font>";
                	out.print(errTxt);
                	return null;
                }
                //如果上传的目录不存在则创建目录
               File dirs=new File(filePath);
               if(!dirs.exists()){
            	   if(!dirs.mkdirs()){
   	            	errTxt = "<font color='red'>文件服务器路径不存在!</font>";
   	                out.print(errTxt);
   	               	return null;
                  }
               }
               //图片重命名
               filePath += DateUtil.getCurrentDayTime()+"_"+fileuploadFileName;
               //写出文件到服务器
               fos=new FileOutputStream(filePath);
               bos=new BufferedOutputStream(fos);
               int len=0;
               while ((len=in.read(temp)) != -1) {
                  bos.write(temp,0,len);
               }
               //把图片信息添加到附件信息表
               fileInfo= new ClwBspUnstructurFileT();
               fileInfo.setFileName(this.fileuploadFileName);
               fileInfo.setFileSuffix(extName);
               fileInfo.setFileStorage(Constants.notice_image_storage_file);
               fileInfo.setFilePath(filePath);
               fileInfo.setFileDesc(Constants.notice_image_desc);
               fileInfo.setScreateDate(getCurrTime());
               Integer imageId=noticeService.insertImage("NoticeManage.addNoticeFile", fileInfo);
               out.print("<font color='green'>" + fileuploadFileName+ "上传成功!</font>#"+imageId);
            } else {
               out.print("<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>");
            	
            }
            
        } catch (IOException e) {
        	 out.print("<font color='red'>上传失败!</font>");
        } catch (BusinessException e) {
        	out.print("<font color='red'>上传失败!</font>");
        }catch (Exception e) {
        	out.print("<font color='red'>上传失败!</font>");
        }finally{
        	try {
        		if(fis != null) fis.close();
        		if (in != null) in.close();
                if (fos!=null) fos.close();
                if (bos!=null) bos.close();
        		out.flush();
                out.close();
            } catch (IOException e) { }
        }
        return null;
    }
    /** 检查是否是图片格式 **/
    public static boolean checkIsImage(String imgStr) {
        boolean flag = false;
        if (imgStr != null) {
            if (imgStr.equalsIgnoreCase(".gif")
                    || imgStr.equalsIgnoreCase(".jpg")
                    || imgStr.equalsIgnoreCase(".jpeg")
                    || imgStr.equalsIgnoreCase(".bmp")
                    || imgStr.equalsIgnoreCase(".png")) {
                flag = true;
            }
        }
        return flag;
    }
    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }
    /**
    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, DriverInfo driverObj) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != driverObj) {
            if (null != driverObj.getDriver_id()) {
                OperateLogFormator.format(sb, "driverid", driverObj
                        .getDriver_id());
            }
        }
        return sb.toString();
    }
    /**
     * 得到系统当前时间
     * @return
     */
    public String getCurrTime(){
    	Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);
    }
    /**
     * 得到排序时间
     * @return
     */
    public String getOrderTime(){
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.YEAR, 100);
        Date currentTime = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);
    }
    /**
     * 转换Map
     * @param list
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getPagination(List<ClwTqcGonggaoT> list, int totalCount, String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new HashMap();
        for (int i = 0; i < list.size(); i++) {
        	ClwTqcGonggaoT gonggao = list.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", gonggao.getGonggaoId());
            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    gonggao.getGonggaoTitle(),
                    gonggao.getGonggaoAuthor(),
                    gonggao.getSgonggaoDate(),
                    gonggao.getSendFlag()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
}
