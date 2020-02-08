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

package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.AirData;
import app.illl.xmsger.struct.AirPollutant;
import org.springframework.data.domain.Page;

import java.time.ZonedDateTime;
import java.util.List;

public interface AirDataService {

    void saveAirData(String city, ZonedDateTime time, Integer aqi, List<AirPollutant> airPollutants, String message);

    void saveAirDataAsync(String city, ZonedDateTime time, Integer aqi, List<AirPollutant> airPollutants, String message);

    Iterable<AirData> getLatestData(String city, Integer count);

    Page<AirData> getDataBeforeTime(ZonedDateTime zonedDateTime, Integer page, Integer size);

    void deleteBeforeTime(ZonedDateTime zonedDateTime, Integer size);
}
