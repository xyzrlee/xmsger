package app.illl.xmsger.datasource.service;

import app.illl.xmsger.struct.AirDescription;

import java.time.ZonedDateTime;

public interface AirDataService {
    void saveAirData(String city, ZonedDateTime time, AirDescription airDescription);
    void saveAirDataAsync(String city, ZonedDateTime time, AirDescription airDescription);
}
