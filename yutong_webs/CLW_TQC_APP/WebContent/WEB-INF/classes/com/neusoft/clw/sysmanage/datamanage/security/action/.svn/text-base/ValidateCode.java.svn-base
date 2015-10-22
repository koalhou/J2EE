package com.neusoft.clw.sysmanage.datamanage.security.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.struts2.interceptor.SessionAware;

import com.neusoft.clw.common.action.BaseAction;

public class ValidateCode extends BaseAction implements SessionAware {
    /** 序列号 */
    private static final long serialVersionUID = -3550031793209755331L;

    /** 验证图片宽度 */
    private static int WIDTH = 60;

    /** 验证图片高度 */
    private static int HEIGHT = 20;

    /** 验证图片字符个数 */
    private static int LENGTH = 4;

    /** 干扰线个数 */
    private static final int DISTURB_LINE_NUM = 30;

    /** 干扰点个数 */
    private static final int DISTURB_POINT_NUM = 50;

    /** action输出流 */
    private ByteArrayInputStream inputStream;

    /** session */
    private Map session;

    /**
     * 按照给定范围返回随机rgb颜色
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {//
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 生成验证字符
     * @return 验证字符
     */
    private static char[] generateCheckCode() {
        // 定义验证码的字符表
        // String chars =
        // "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String chars = "0123456789";
        Random random = new Random();
        char[] rands = new char[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            // int rand = random.nextInt(62);
            int rand = random.nextInt(10);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    /**
     * 画出验证字符
     * @param g 画板
     * @param rands 验证字符
     */
    private void drawRands(Graphics g, char[] rands) {
        g.setFont(new Font("Times New Roman", Font.BOLD | Font.BOLD, 18));

        // 在不同的高度上输出验证码的每个字符
        g.setColor(getRandColor(1, 100));
        g.drawString("" + rands[0], 1, 17);

        g.setColor(getRandColor(1, 100));
        g.drawString("" + rands[1], 16, 17);
        g.setColor(getRandColor(1, 100));
        g.drawString("" + rands[2], 31, 17);
        g.setColor(getRandColor(1, 100));
        g.drawString("" + rands[3], 46, 17);
        // g.setColor(getRandColor(1,100));
        // g.drawString("" + rands[4], 61, 17);
        // g.setColor(getRandColor(1,100));
        // g.drawString("" + rands[5], 76, 17);
    }

    /**
     * 画出验证图片背景
     * @param g 画板
     */
    private void drawBackground(Graphics g) {
        Random random = new Random();

        // 画背景
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 加干扰点
        /*
         * for (int i = 0; i < DISTURB_POINT_NUM; i++) { int x =
         * random.nextInt(WIDTH); int y = random.nextInt(HEIGHT);
         * g.setColor(getRandColor(150, 200)); g.drawOval(x, y, 1, 0); }
         */

        // 加干扰线
        /*
         * for (int i = 0; i < DISTURB_LINE_NUM; i++) {
         * g.setColor(getRandColor(100, 150)); int x = 0; int y =
         * random.nextInt(HEIGHT); int xl = random.nextInt(WIDTH); int yl =
         * random.nextInt(HEIGHT); g.drawLine(x, y, xl, yl); }
         */
    }

    /**
     * 生成验证图片action
     * @return
     * @throws Exception
     */
    public String creatValidatePic() throws Exception {

        // 在内在中创建图象
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        // 产生随机的认证码
        char[] rands = generateCheckCode();

        // 产生图像
        drawBackground(g);

        drawRands(g, rands);

        // 结束图像的绘制过程，完成图像
        g.dispose();

        session.put("randCheckCode", new String(rands));

        ByteArrayInputStream input = null;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageOutputStream imageOut = ImageIO
                    .createImageOutputStream(output);
            ImageIO.write(image, "JPEG", imageOut);
            imageOut.close();
            input = new ByteArrayInputStream(output.toByteArray());
        } catch (Exception e) {
            log.debug("验证码图片产生出现错误：" + e.toString());
            return ERROR;
        }
        this.setInputStream(input);
        return SUCCESS;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    /**
     * @return Returns the session.
     */
    public Map getSession() {
        return session;
    }

    /**
     * @param session The session to set.
     */
    public void setSession(Map session) {
        this.session = session;
    }
}
