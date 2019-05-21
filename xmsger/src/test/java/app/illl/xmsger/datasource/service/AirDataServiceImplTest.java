package app.illl.xmsger.datasource.service;

import app.illl.xmsger.struct.AirDescription;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class AirDataServiceImplTest {

    @Autowired
    private AirDataService airDataService;

    private AirDescription airDescription;
    private String city;
    private ZonedDateTime zonedDateTime;

    @Before
    public void beforeTest() {
        this.city = "Shanghai";
        this.zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        this.airDescription = new AirDescription();
        this.airDescription.setAqi(150);
        this.airDescription.setFineParticulateMatter(BigDecimal.TEN);
        this.airDescription.setComment("ajoiwbnreoal;nasd🐷awegwer");
    }

    @Test
    public void saveAirData() {
        this.airDataService.saveAirData(city, zonedDateTime, airDescription);
    }

    @Test
    public void saveAirDataAsync() {
        this.airDataService.saveAirDataAsync(city, zonedDateTime, airDescription);
    }
}