package app.illl.xmsger.utility;

public class AqiUtils {

    private AqiUtils() {
    }

    private static final int LEVEL_GOOD = 50;
    private static final int LEVEL_MODERATE = 100;

    public static Boolean isUnhealthy(int aqi) {
        return aqi >= LEVEL_MODERATE;
    }

    public static Boolean isGood(int aqi) {
        return aqi <= LEVEL_GOOD;
    }

}
