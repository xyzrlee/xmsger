package app.illl.xmsger.utility;

import app.illl.xmsger.struct.AirDescription;

public class AqiUtils {

    private AqiUtils() {
    }

    public static Boolean isUnhealthy(Integer aqi) {
        if (null == aqi) return false;
        return aqi >= 100;
    }

    public static Boolean isUnhealthy(AirDescription airDescription) {
        if (null == airDescription) return false;
        return isUnhealthy(airDescription.getAqi());
    }

}
