/*
 * XMsger
 * Copyright (C) 2020  Ricky Li
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package app.illl.xmsger.utility;

public class AqiUtils {

    private AqiUtils() {
    }

    private static final int LEVEL_GOOD = 50;
    private static final int LEVEL_MODERATE = 100;

    public static Boolean isUnhealthy(int aqi) {
        return aqi >= LEVEL_MODERATE;
    }

    public static Boolean isHealthy(int aqi) {
        return !isUnhealthy(aqi);
    }

    public static Boolean isGood(int aqi) {
        return aqi <= LEVEL_GOOD;
    }

}
