package com.neusoft.clw.common.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;

public class BytetoImage {
	public static byte[] toByteArray(File imageFile) throws Exception {
		BufferedImage img = ImageIO.read(imageFile);
		ByteArrayOutputStream buf = new ByteArrayOutputStream(
				(int) imageFile.length());
		try {
			ImageIO.write(img, "jpg", buf);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return buf.toByteArray();
	}

	public static void main(String[] args) throws Exception {
		byte[] b = toByteArray(new File("d:/Wallpapers/003.jpg"));
		ByteArrayInputStream in = new ByteArrayInputStream(b);
		BufferedImage image = ImageIO.read(in);
		File newFile = new File("d:/Wallpapers/1.jpg");
		ImageIO.write(image, "jpg", newFile);
	}
}