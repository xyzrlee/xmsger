package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.AirData;
import app.illl.xmsger.datasource.repository.AirDataRepository;
import app.illl.xmsger.struct.AirDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Component
public class AirDataServiceImpl implements AirDataService {

    private final AirDataRepository airDataRepository;

    @Override
    @Transactional
    public void saveAirData(String city, ZonedDateTime time, AirDescription airDescription) {
        AirData airData = new AirData();
        airData.setMessageTime(time);
        airData.setCity(city);
        airData.setDescription(airDescription);
        airDataRepository.save(airData);
    }

    @Override
    @Async
    @Transactional
    public void saveAirDataAsync(String city, ZonedDateTime time, AirDescription airDescription) {
        this.saveAirData(city, time, airDescription);
    }
}
