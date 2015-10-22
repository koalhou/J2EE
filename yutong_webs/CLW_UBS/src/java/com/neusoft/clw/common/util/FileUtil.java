/*******************************************************************************
 * @(#)FileUtil.java Oct 23, 2007
 *
 * Copyright 2007 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 * @author <a href="mailto:hegq@neusoft.com">puras.he </a>
 * @version $Revision 1.1 $ Oct 23, 2007 12:59:21 PM
 */
public class FileUtil {
    // private static Log log = LogFactory.getLog(FileUtil.class);

    private static Logger log = Logger.getLogger(FileUtil.class);

    public static void copy(File src, String dst) {
        int BUFFER_SIZE = 16 * 1024;
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(src),
                        BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(dst),
                        BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                int len = 0;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
            } finally {
                if (null != in) {
                    try {
                        in.close();
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
                if (null != out) {
                    try {
                        out.close();
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos);
    }

    public static String makeFileName(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String today = sdf.format(date);
        today += new Random().nextInt(1000) + 1000;
        return today;
    }

    public static String makeFileNameByToday() {
        return makeFileName(new Date());
    }

    public static void removeFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void main(String[] args) {
        makeFileName(new Date());
        // removeFile(Constant.NEWS_UPLOAD_PATH + "delete.jpg");
    }
}
