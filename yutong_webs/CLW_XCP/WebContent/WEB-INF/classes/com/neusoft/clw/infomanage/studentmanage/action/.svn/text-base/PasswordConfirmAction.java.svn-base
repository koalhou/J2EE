package com.neusoft.clw.infomanage.studentmanage.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.md5.MD5digest;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class PasswordConfirmAction extends PaginationAction {
    public static final String USER_IS_CHECK = "USER_IS_CHECK";
    
    /** service共通类 */
    private transient Service service;

    private String moduleName = "";
    
    public String init() {
        HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        
        this.moduleName = request.getParameter("moduleName");
       
        return SUCCESS;
    }

    /**
     * 修改密碼
     * @return
     */
    public String passwordConfirm() {
        return SUCCESS;
    }

    /**
     * 验证密码
     * @param inputPassword
     * @return
     */
    public boolean checkPassword(String inputPassword) {
        boolean ret = false;
        WebContext ctx = WebContextFactory.get();
        HttpServletRequest request = ctx.getHttpServletRequest();
        UserInfo user = (UserInfo) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("userId", user.getUserID());
            map.put("studentPwd", MD5digest.generatePassword(inputPassword));
            
            int cnt = service.getCount("StudentManage.checkUser", map);
            if (cnt == 0) {
                return false;
            } else {
                user.setUserIsCheck(USER_IS_CHECK);
                request.getSession().setAttribute(Constants.USER_SESSION_KEY, user);
                return true;
            }
        } catch (BusinessException e) {
            log.error("密码验证DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
    }
    
    /**
     * 验证学生卡号唯一性
     * @param studentCode
     * @return
     */
    public boolean checkStudentCode(String studentCard) {
        boolean ret = false;
        
        try {
            int cnt = service.getCount("StudentManage.checkStudentCard", studentCard.trim());
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("验证学生卡号唯一性DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
    }
    
    /**
     * 验证驾驶员卡号唯一性
     * @param studentCode
     * @return
     */
    public boolean checkDriverCard(String driverCard) {
        boolean ret = false;
        
        try {
            int cnt = service.getCount("DriverManage.checkDriverCard", driverCard.trim());
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("验证驾驶员卡号唯一性DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
    }
    
    /**
     * 验证司乘卡号唯一性
     * @param stewardCard
     * @return
     */
    public boolean checkStewardCard(String stewardCard) {
        boolean ret = false;
        
        try {
            int cnt = service.getCount("StewardManage.checkStewardCard", stewardCard.trim());
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("验证司乘卡号唯一性DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
    }
    
    /**
     * 更新卡号
     * @param cardNo
     * @param dataType
     * @return
     */
    public String updateCardNoByInputType(String dataId, String cardNo, String dataType) {
        String ret = "";
        if("driver".equals(dataType)) {
            // 驾驶员采集
            if(checkDriverCard(cardNo)) {
                // 存在相同的卡号时
                ret = "exists";
            } else {
                // 不存在相同卡号时，根据ID更新数据
                Map<String, String> driverMap = new HashMap<String, String>();
                driverMap.put("driverId", dataId.trim());
                driverMap.put("driverCard", cardNo.trim());
                try {
                    service.update("DriverManage.updateDriverCardById", driverMap);
                    ret = "success";
                } catch (BusinessException e) {
                    ret = "error";
                    log.error("更新驾驶员卡号DWR异常发生，异常原因：" + e.getMessage());
                }
            }
        } else if("steward".equals(dataType)) {
            // 跟车老师采集
            if(checkStewardCard(cardNo)) {
                // 存在相同的卡号时
                ret = "exists";
            } else {
                // 不存在相同卡号时，根据ID更新数据
                Map<String, String> stewardMap = new HashMap<String, String>();
                stewardMap.put("stewardId", dataId.trim());
                stewardMap.put("stewardCard", cardNo.trim());
                try {
                    service.update("StewardManage.updateStewardCardById", stewardMap);
                    ret = "success";
                } catch (BusinessException e) {
                    ret = "error";
                    log.error("更新跟车老师卡号DWR异常发生，异常原因：" + e.getMessage());
                }
            }
        } else if("student".equals(dataType)) {
            // 学生采集
            if(checkStudentCode(cardNo)) {
                // 存在相同的卡号时
                ret = "exists";
            } else {
                // 不存在相同卡号时，根据ID更新数据
                Map<String, String> studentMap = new HashMap<String, String>();
                studentMap.put("studentId", dataId.trim());
                studentMap.put("studentCard", cardNo.trim());
                try {
                    service.update("StudentManage.updateStudentCardById", studentMap);
                    ret = "success";
                } catch (BusinessException e) {
                    ret = "error";
                    log.error("更新学生卡号DWR异常发生，异常原因：" + e.getMessage());
                }
            }
        }
        
        return ret;
    }
    
    /**
     * 验证驾驶证号唯一性
     * @param studentCode
     * @return
     */
    public boolean checkDriverLicense(String driverLicense) {
        boolean ret = false;
        
        try {
            int cnt = service.getCount("DriverManage.checkDriverLicense", driverLicense.trim());
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("验证驾驶证号唯一性DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
    }
    
    /**
     * 验证身份证号唯一性
     * @param studentCode
     * @return
     */
    public boolean checkStewardCardID(String stewardCardId) {
        boolean ret = false;
        
        try {
            int cnt = service.getCount("StewardManage.checkStewardCardId", stewardCardId.trim());
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("验证身份证号唯一性DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
    }
    
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
