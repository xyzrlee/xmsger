package app.illl.xmsger.utility;

public class AqiUtils {

    private AqiUtils() {
    }

    public static Boolean isUnhealthy(Integer aqi) {
        if (null == aqi) return false;
        return aqi >= 100;
    }

    public static Boolean isHealthy(Integer aqi) {
        return !isUnhealthy(aqi);
    }

}
