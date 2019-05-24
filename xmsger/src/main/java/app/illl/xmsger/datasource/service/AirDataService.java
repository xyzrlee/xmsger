package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.AirData;
import app.illl.xmsger.struct.AirPollutant;
import org.springframework.data.domain.Page;

import java.time.ZonedDateTime;
import java.util.List;

public interface AirDataService {

    void saveAirData(String city, ZonedDateTime time, Integer aqi, List<AirPollutant> airPollutants, String message);

    void saveAirDataAsync(String city, ZonedDateTime time, Integer aqi, List<AirPollutant> airPollutants, String message);

    Iterable<AirData> getLatestData(String city, ZonedDateTime zonedDateTime, Integer count);

    Page<AirData> getDataBeforeTime(ZonedDateTime zonedDateTime, Integer page, Integer size);

    void deleteBeforeTime(ZonedDateTime zonedDateTime, Integer size);
}
