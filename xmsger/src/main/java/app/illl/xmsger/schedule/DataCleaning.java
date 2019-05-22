package app.illl.xmsger.schedule;

import app.illl.xmsger.datasource.service.AirDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
public class DataCleaning {

    private final AirDataService airDataService;

    @Scheduled(cron = "0 0 * * * *")
    void cleanAirData() {
        ZonedDateTime time = ZonedDateTime.now().minusDays(5);
        airDataService.deleteBeforeTime(time, 50);
    }

}
