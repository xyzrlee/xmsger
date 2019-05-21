package app.illl.xmsger.utility;

public class AqiUtils {

    private AqiUtils() {
    }

    public static Boolean isUnhealthy(Integer aqi) {
        return aqi >= 100;
    }

}
