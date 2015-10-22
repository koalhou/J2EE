package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.neusoft.clw.vncs.reportAnalysis.AnalysisReportThread;

public class TestFagServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private static Logger log = Logger.getLogger("TestFagServlet");

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("疲劳驾驶算法测试开始");
        AnalysisReportThread newAnalysis = new AnalysisReportThread("LZYTATE69B1016529","2011-07-09", null);
//        AnalysisReportThread newAnalysis = new AnalysisReportThread("LZYTATE62B1014850","2011-07-02", null);
        newAnalysis.start();
        log.info("疲劳驾驶算法测试结束");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
