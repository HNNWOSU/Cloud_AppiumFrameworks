package BaseTestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

   static ExtentReports extent;

    public static ExtentReports getReporterObject() {

        String path = System.getProperty("user.dir")+"/src/main/Reports/index.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

        reporter.config().setReportName("Mobile Automation Test Results");
        reporter.config().setDocumentTitle("CreateFuture Test Results");


        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Henry CreateFuture");

        return extent;

    }
}
