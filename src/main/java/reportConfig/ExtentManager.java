package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import commons.GlobalConstant;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstant.getGlobalConstants().getProjectPath() + "/extentReport/ExtentReport.html");
        reporter.config().setReportName("LiveGuru HTML Report");
        reporter.config().setDocumentTitle("LiveGuru HTML Report");
        reporter.config().setTimelineEnabled(true);
        reporter.config().setEncoding("utf-8");
        reporter.config().setTheme(Theme.DARK);

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Project", "LiveGuru");
        extentReports.setSystemInfo("Team", "AUTOMATION");
        extentReports.setSystemInfo("JDK version", GlobalConstant.getGlobalConstants().getJavaVersion());
        return extentReports;
    }
}
