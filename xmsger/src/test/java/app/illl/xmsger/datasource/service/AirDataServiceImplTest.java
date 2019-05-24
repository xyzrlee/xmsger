package app.illl.xmsger.datasource.service;

import app.illl.xmsger.constant.ZoneNames;
import app.illl.xmsger.datasource.entity.AirData;
import app.illl.xmsger.struct.AirPollutant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class AirDataServiceImplTest {

    @Autowired
    private AirDataService airDataService;

    private String city;
    private ZonedDateTime zonedDateTime;
    private Integer aqi;
    private List<AirPollutant> airPollutants;
    private String message;

    @Before
    public void beforeTest() {
        this.city = ZoneNames.ASIA_SHANGHAI;
        this.zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        this.aqi = 120;
        this.airPollutants = new LinkedList<>();
        AirPollutant airPollutant = new AirPollutant();
        airPollutant.setType("PM2.5");
        airPollutant.setConcentration(new BigDecimal("50"));
        this.airPollutants.add(airPollutant);
        this.message = "{\"comment\":\"Moderate (at 24-hour exposure at this level)🐷\",\"PM2.5\":21.0,\"AQI\":70}";
    }

    @Test
    public void saveAirData() {
        this.airDataService.saveAirData(city, zonedDateTime, aqi, airPollutants, message);
        Assert.assertTrue(true);
    }

    @Test
    public void saveAirDataAsync() {
        this.airDataService.saveAirDataAsync(city, zonedDateTime, aqi, airPollutants, message);
        Assert.assertTrue(true);
    }

    @Test
    public void getDataBeforeTime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        for (int page = 0; ; page++) {
            log.info("page:{}", page);
            Page<AirData> airDataPage = airDataService.getDataBeforeTime(zonedDateTime, page, 2);
            if (airDataPage.isEmpty()) break;
        }
        Assert.assertTrue(true);
    }

    @Test
    public void deleteBeforeTime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        airDataService.deleteBeforeTime(zonedDateTime, 5);
        Assert.assertTrue(true);
    }
}