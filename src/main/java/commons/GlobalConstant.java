package commons;

import lombok.Getter;

@Getter
public class GlobalConstant {
    private static GlobalConstant globalInstance;
    private GlobalConstant() {
    }

    public static synchronized GlobalConstant getGlobalConstants() {
        if (globalInstance == null) {
            globalInstance = new GlobalConstant();
        }
        return globalInstance;
    }
    private final long shortTimeout = 5;
    private final long longTimeout = 30;
    private final String liveGuruFEUrl = "http://live.techpanda.org/";
    private final String liveGuruBEUrl = "http://live.techpanda.org/index.php/backendlogin";
    private final String projectPath = System.getProperty("user.dir");
    private final String osName = System.getProperty("os.name");
    private final String javaVersion = System.getProperty("java.version");
}
