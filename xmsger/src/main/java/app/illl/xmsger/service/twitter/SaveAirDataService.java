package app.illl.xmsger.service.twitter;

import app.illl.xmsger.datasource.entity.AirData;
import app.illl.xmsger.datasource.repository.AirDataRepository;
import app.illl.xmsger.struct.AirDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SuppressWarnings("WeakerAccess")
@Component
public class SaveAirDataService {

    @Autowired
    private AirDataRepository airDataRepository;

    @Transactional
    private void saveAirData(String city, LocalDateTime time, AirDescription airDescription) {
        AirData airData = new AirData();
        airData.setMessageTime(time);
        airData.setCity(city);
        airData.setDescription(airDescription);
        airDataRepository.save(airData);
    }

    @Async
    public void saveAirDataAsync(String city, LocalDateTime time, AirDescription airDescription) {
        this.saveAirData(city, time, airDescription);
    }

}
