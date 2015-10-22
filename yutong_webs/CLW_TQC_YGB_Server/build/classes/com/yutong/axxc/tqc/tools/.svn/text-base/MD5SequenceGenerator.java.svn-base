
package com.yutong.axxc.tqc.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.cxf.rs.security.oauth2.provider.OAuthServiceException;

/**
 
 */
public class MD5SequenceGenerator {
	/**
	 * 生成32位MD5串.
	 * 
	 * @param input
	 *            被加密字符数组.
	 * @return 32位MD5串
	 * @throws OAuthServiceException
	 */
	public String generate32bit(byte[] input) throws OAuthServiceException {
		if (null == input) {
			throw new OAuthServiceException(
					"You have to pass input to Token Generator");
		} else {
			try {
				MessageDigest algorithm = MessageDigest.getInstance("MD5");
				algorithm.reset();
				algorithm.update(input);
				byte[] messageDigest = algorithm.digest();
				StringBuffer hexString = new StringBuffer();
				for (int i = 0; i < messageDigest.length; i++) {
					hexString.append(Integer
							.toHexString(0xFF & messageDigest[i]));
				}

				return hexString.toString();
			} catch (NoSuchAlgorithmException e) {
				throw new OAuthServiceException("server_error", e);
			}
		}
	}

	/**
	 * 生成16位MD5串.
	 * 
	 * @param input
	 *            被加密字符数组.
	 * @return 32位MD5串
	 * @throws OAuthServiceException
	 */
	public String generate16bit(byte[] input) throws OAuthServiceException {
		if (null == input) {
			throw new OAuthServiceException(
					"You have to pass input to Token Generator");
		} else {
			try {
				MessageDigest algorithm = MessageDigest.getInstance("MD5");
				algorithm.reset();
				algorithm.update(input);
				byte[] messageDigest = algorithm.digest();
				StringBuffer hexString = new StringBuffer();
				for (int i = 0; i < messageDigest.length; i++) {
					hexString.append(Integer
							.toHexString(0xFF & messageDigest[i]));
				}

				return hexString.toString().substring(8, 24);
			} catch (NoSuchAlgorithmException e) {
				throw new OAuthServiceException("server_error", e);
			}
		}
	}

	/**
	 * 通过UUID生成16位MD5字符串.
	 * 
	 * @return 16位MD5字符串.
	 * @throws OAuthServiceException
	 */
	public String generate16ByUuid() throws OAuthServiceException {
		return new MD5SequenceGenerator().generate16bit((UUID.randomUUID()
				.toString().getBytes()));
	}

	/**
	 * 通过UUID生成32位MD5字符串.
	 * 
	 * @return 32位MD5字符串.
	 * @throws OAuthServiceException
	 */
	public String generate32ByUuid() throws OAuthServiceException {
		return new MD5SequenceGenerator().generate32bit((UUID.randomUUID()
				.toString().getBytes()));
	}
}
