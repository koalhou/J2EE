
package com.yutong.axxc.parents.tools;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.cxf.common.util.Base64Exception;
import org.apache.cxf.common.util.Base64Utility;


public class ImageEncoderUtils {
	/**
	 * 将图片转换为base64串
	 * 
	 * @param path
	 *            图片路径
	 * @return base64串
	 * @throws IOException
	 */
	public static String getImageBinary(String path) throws IOException {
		File f = new File(path);
		BufferedImage bi;
		bi = ImageIO.read(f);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, "jpg", baos);
		byte[] bytes = baos.toByteArray();

		return Base64Utility.encode(bytes).trim();
	}

	/**
	 * 由base64串转换为图片
	 * 
	 * @param base64String
	 * @param filename
	 *            输出路径
	 * @throws Base64Exception
	 * @throws IOException
	 */
	public static void base64StringToImage(String base64String, String filename)
			throws Base64Exception, IOException {
		byte[] bytes1 = Base64Utility.decode(base64String);
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
		BufferedImage bi1 = ImageIO.read(bais);
		File w2 = new File(filename);// 可以是jpg,png,gif格式
		ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动
	}
}
