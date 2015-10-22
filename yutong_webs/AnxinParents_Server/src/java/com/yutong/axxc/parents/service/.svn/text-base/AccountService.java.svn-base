package com.yutong.axxc.parents.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.entity.Child;
import com.yutong.axxc.parents.entity.UserSeesion;
import com.yutong.axxc.parents.entity.account.UserInfo;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.mapper.MybatisDAO;
import com.yutong.axxc.parents.tools.MD5SequenceGenerator;

@Service
public class AccountService {
	private static Logger logger = LoggerFactory
			.getLogger(AccountService.class);

	@Autowired
	protected MybatisDAO dao;
	@Autowired
	protected RuleService ruleService;
	@Autowired
	private EtagService etagService;

	@Transactional
	public UserInfo regist(UserInfo user) {
		logger.info("用户{}开始注册",user);
		Integer i = dao.get("Account.verifyPhone", user.getPhone());
		if (i == 1) {
			throw new ApplicationException(ErrorConstant.ERROR10021,
					Response.Status.BAD_REQUEST);
		}
		i = dao.get("Account.verifyAccountName", user.getName());
		if (i == 1) {
			throw new ApplicationException(ErrorConstant.ERROR10028,
					Response.Status.BAD_REQUEST);
		}
		int ret = -1;
		int userID = dao.get("Account.getSeq", "SEQ_CLW_BSP_ACCOUNT_T_ID");
		int userCode = dao.get("Account.getSeq", "seq_clw_bsp_account_t_code");
		user.setId(String.valueOf(userID));
		user.setNo(String.valueOf(userCode));
		ret = dao.save("Account.addAccount", user);
		logger.info("插入账户：{}[{}]", user,ret);
		List<UserInfo> parentInfo = dao.getList("Account.getParentsOrgInfo",
				user.getPhone());
		if (parentInfo != null && parentInfo.size() > 0) {
			user.setAddr(parentInfo.get(0).getAddr());
			user.setXinming(parentInfo.get(0).getXinming());
		}
		ret = dao.save("Account.addParents", user);
		logger.info("插入家长{}[{}]",user, ret);
		
		
		// 自动关联
		List<Child> children = dao.getList("Child.getBoundChildren4Reg",
				user.getPhone());
		if (children != null && children.size() > 0) {
			for (Child child : children) {
				if (StringUtils.hasText(child.getPid())) {
					throw new ApplicationException(ErrorConstant.ERROR10020,
							Response.Status.BAD_REQUEST);
				}
			}
			for (Child child : children) {
				// 检测孩子是否已存在
				int count = dao.get("Child.countStudent", child.getId());
				if (count == 0) {
					ret = dao.save("Account.addStudent", child.getId());
					logger.info("增加关联学生：{}[{}]", child,ret);
					ret=ruleService.initRule(user.getId(), child.getId());
					logger.info("插入规则：{}[{}]",child, ret);
				}
				child.setPid(String.valueOf(userID));
				child.setAddr("1");
				ret = dao.save("Account.addParentStudent", child);
				logger.info("插入家长学生关联：{}[{}]",child, ret);
				user.setMain("1");
			}
		}else{
			ret=ruleService.initRule(user.getId(), null);
			logger.info("插入规则：{}[{}]", user.getId(),ret);
		}
		return user;
	}

	@Transactional
	public int resetPwd(UserInfo user) {
		return dao.save("Account.resetPwd", user);
	}

	@Transactional
	public String bindPhone(String userID, String phone) {
		int ret;
		UserInfo user = new UserInfo();
		user.setId(userID);
		List<Child> children = dao.getList("Child.getBoundChildren4Reg", phone);
		if (children != null && children.size() > 0) {
			for (Child child : children) {
				if (StringUtils.hasText(child.getPid())) {
					logger.info("学生:{} 绑定到用户：{}", child.getId(), child.getPid());
					throw new ApplicationException(ErrorConstant.ERROR10020,
							Response.Status.BAD_REQUEST);
				}
			}
			for (Child child : children) {
				logger.info("绑定学生:{} 到用户：{}", child.getId(), userID);
				// 检测孩子是否已存在
				int count = dao.get("Child.countStudent", child.getId());
				if (count == 0) {
					ret = dao.save("Account.addStudent", child.getId());
					logger.info("增加关联学生：{}[{}]", child, ret);
					ret=ruleService.initRule(user.getId(), child.getId());
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

	public List<UserInfo> getUserByNamePwd(UserInfo user) {
		return dao.getList("Account.login", user);
	}

	@Transactional
	public UserInfo genSession(UserInfo usrInfo, int expireIn) {
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
		UserSeesion session=new UserSeesion();
		session.setAccessToken(accessToken);
		session.setId(usrInfo.getId());
		session.setExpireTime(cal.getTime());
		session.setRefreshToken(refreshToken);
		String etag=CachedCommon.CACHED_TOKEN_KEY+accessToken;
		if(!etagService.put(etag, CachedCommon.CACHED_MINTER_7D, session)){
			logger.error("缓存失败:{}", etag);
		}
		return usrInfo;
	}

	@Transactional
	public int refreshToken(UserInfo usrInfo, int expireIn) {

		return dao.update("Account.refreshToken", usrInfo);
	}

	@Transactional
	public void logout(String id) {
		UserInfo user = new UserInfo();
		user.setAble(0);
		user.setClientID("");
		user.setId(id);
		user.setExpireTime(new Date());
		dao.update("Account.updateToken", user);
		
	}

	@Transactional
	public int bindClient(String id, String clientid) {
		UserInfo user = new UserInfo();
		user.setId(id);
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
