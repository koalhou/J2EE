/**
 * 
 */
package com.neusoft.clw.info.util.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author zhu.lei
 *
 */
public class ExceptionHelpers { 
    
    public static String getStringOfExceptionStack ( Exception exp) {
        String strExceptionStack = null;

        if (exp != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            
            try {
                exp .printStackTrace(pw);
                strExceptionStack = sw.toString();
            }
            finally {
                try {
                    sw.close();
                    pw.close();
                } catch (IOException e) {
                    // ignore
                	e.getMessage();
                }
            }
        }
        return strExceptionStack;
    }
}
