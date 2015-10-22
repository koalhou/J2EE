
package com.yutong.axxc.tqc.entity.usr;

import javax.xml.bind.annotation.XmlElement;

/**
 
 * @version $Revision 1.0 $ 2013-4-13 下午2:05:43
 */
public class UserModifyPwdReq {

	/**
	 * 功能模块ID.
	 */
	@XmlElement(name = "old_pwd")
	private String oldPwd;


	/**
	 * 功能模块ID.
	 */
	@XmlElement(name = "new_pwd")
	private String newPwd;


	/**
	 * @return Returns the oldPwd.
	 */
	public String getOldPwd() {
		return oldPwd;
	}


	/**
	 * @param oldPwd The oldPwd to set.
	 */
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}


	/**
	 * @return Returns the newPwd.
	 */
	public String getNewPwd() {
		return newPwd;
	}


	/**
	 * @param newPwd The newPwd to set.
	 */
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

}
