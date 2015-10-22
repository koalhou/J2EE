package com.yutong.axxc.tqc.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yutong.axxc.tqc.common.CachedCommon;
import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.entity.Child;
import com.yutong.axxc.tqc.entity.UserSeesion;
import com.yutong.axxc.tqc.entity.account.UserInfo;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.mapper.MybatisDAO;
import com.yutong.axxc.tqc.tools.MD5SequenceGenerator;

@Service
public class AccountService {
	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	protected MybatisDAO dao;
	@Autowired
	protected RuleService ruleService;
	@Autowired
	private EtagService etagService;

	@Transactional
	public int resetPwd(Map<String, String> pwd_conditions) {
		return dao.save("Account.resetPwd", pwd_conditions);
	}

	@Transactional
	public String bindPhone(String userID, String phone) {
		int ret;
		UserInfo user = new UserInfo();
		user.setEmp_id(userID);
		List<Child> children = dao.getList("Child.getBoundChildren4Reg", phone);
		if (children != null && children.size() > 0) {
			for (Child child : children) {
				if (StringUtils.hasText(child.getPid())) {
					logger.info("学生:{} 绑定到用户：{}", child.getId(), child.getPid());
					throw new ApplicationException(ErrorConstant.ERROR10020, Response.Status.BAD_REQUEST);
				}
			}
			for (Child child : children) {
				logger.info("绑定学生:{} 到用户：{}", child.getId(), userID);
				// 检测孩子是否已存在
				int count = dao.get("Child.countStudent", child.getId());
				if (count == 0) {
					ret = dao.save("Account.addStudent", child.getId());
					logger.info("增加关联学生：{}[{}]", child, ret);
					ret=ruleService.initRule(user.getEmp_id(), child.getId());
					logger.info("插入规则：{}[{}]", child, ret);
				}
				child.setPid(userID);
				child.setAddr("1");
				ret = dao.save("Account.addParentStudent", child);
				logger.info("插入家长学生关联：{}[{}]", child, ret);
			}
			return "1";
		}

		return "0";
	}

	public List<UserInfo> getUserByNamePwd(Map<String, String> login_conditions) {
		return dao.getList("Account.login", login_conditions);
	}
	
	public List<UserInfo> getUserByAccount(Map<String, String>	check_conditions) {
		return dao.getList("Account.checkAccount", check_conditions);
	}
	
	public List<UserInfo> getUserByNamePhone(Map<String, String> user) {
		return dao.getList("Account.checkIfPhoneBinded", user);
	}
	
	@Transactional
	public Map<String, String> bindPhone(Map<String, String> userInfo, int expireIn){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, expireIn);
		userInfo.put("ExpireTime",String.valueOf(cal.getTime()));
		dao.save("Account.updateBindPhoneEmp", userInfo);
		dao.save("Account.updateBindPhoneBsp", userInfo);
		return userInfo;
	}
	
	@Transactional
	public UserInfo genSession(UserInfo usrInfo, int expireIn, String login_pwd) {
		String accessToken = new MD5SequenceGenerator().generate16ByUuid();
		String refreshToken = new MD5SequenceGenerator().generate16ByUuid();
		usrInfo.setAccessToken(accessToken);
		usrInfo.setRefreshToken(refreshToken);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, expireIn);
		usrInfo.setExpireTime(cal.getTime());
		usrInfo.setAble(1);
		if (dao.save("Account.updateToken", usrInfo) == 0) {
			dao.save("Account.addToken", usrInfo);
		}
		logger.info("用户{}生成session" ,usrInfo);

		//缓存session
		UserSeesion session = new UserSeesion();
		session.setAccessToken(accessToken);
		session.setId(usrInfo.getEmp_code());
		session.setExpireTime(cal.getTime());
		session.setRefreshToken(refreshToken);
		String etag = CachedCommon.CACHED_TOKEN_KEY + accessToken;
		if(!etagService.put(etag, expireIn, session)){
			logger.error("缓存失败:{}", etag);
		}
		return usrInfo;
	}
	@Transactional
	public void insertLoginLog(List<UserInfo> usrInfoList) {
		dao.update("Account.AddLoginLog", usrInfoList.get(0).getEmp_code());
	}
	@Transactional
	public void insertAutoLoginLog(UserInfo usrInfoList) {
		dao.update("Account.AddAutoLoginLog", usrInfoList.getEmp_code());
	}
	@Transactional
	public int refreshToken(UserInfo usrInfo, int expireIn) {

		return dao.update("Account.refreshToken", usrInfo);
	}

	@Transactional
	public void logout(String token) {
		dao.update("Account.deleteToken", token);
		
	}

	@Transactional
	public int bindClient(String id, String clientid) {
		UserInfo user = new UserInfo();
		user.setEmp_id(id);
		user.setClientID(clientid);
		return dao.update("Account.bindClient", user);
	}

	@Transactional
	public int updatePwd(UserInfo user) {
		return dao.update("Account.updatePwd", user);

	}

	@Transactional
	public int saveParents(UserInfo user) {
		return dao.update("Account.updateUser", user);
	}

	@Transactional
	public int saveChildInfo(Child child) {
		return dao.update("Child.updateChildInfo", child);
	}

	@Transactional
	public int addRelation(String my, String userID, String sid) {
		int ret=0;
		if (!StringUtils.hasText(sid)) {
			List<Child> children = dao.getList("Child.getChildren", my);
			if (children != null && children.size() > 0) {
				for (Child child : children) {
					child.setPid(userID);
					child.setAddr("0");
					ret = dao.save("Account.addParentStudent", child);
					logger.info("插入家长学生关联：{}", ret);
					ret=ruleService.initRule(userID, child.getId());
					logger.info("插入规则：{}", ret);
				}
				return 1;
			}
		} else {
			Child ch = new Child();
			ch.setId(sid);
			ch.setPid(userID);
			ch.setAddr("0");
			ret = dao.save("Account.addParentStudent", ch);
			ret=ruleService.initRule(userID, ch.getId());
			logger.info("插入规则：{}", ret);
			logger.info("插入家长学生关联：{}", ret);
		}
		return ret;
	}

	@Transactional
	public int delRelation(String my, String otherUID, String childId) {
		int ret=0;
		if (!StringUtils.hasText(childId)) {
			List<Child> children = dao.getList("Child.getChildren", my);
			if (children != null && children.size() > 0) {
				for (Child child : children) {
					child.setPid(otherUID);
					ret = dao.delete("Child.deleteParentStudent", child);
					logger.info("删除家长学生关联：{}", ret);
					ret=ruleService.delRule(otherUID, child.getId());
					logger.info("插入规则：{}", ret);
				}
				return 1;
			}
		} else {
			Child ch = new Child();
			ch.setId(childId);
			ch.setPid(otherUID);
			ret = dao.delete("Child.deleteParentStudent", ch);
			logger.info("删除家长学生关联：{}", ret);
			ret=ruleService.delRule(otherUID, ch.getId());
			logger.info("删除规则：{}", ret);
			return 1;
		}
		return ret;
	}

	@Transactional
	public int setRelation(Child ch) {
		return dao.update("Child.updateRelation", ch);

	}

}
