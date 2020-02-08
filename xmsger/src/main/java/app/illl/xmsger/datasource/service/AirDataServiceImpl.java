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
import app.illl.xmsger.datasource.repository.AirDataRepository;
import app.illl.xmsger.struct.AirPollutant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class AirDataServiceImpl implements AirDataService {

    private final AirDataRepository airDataRepository;

    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_MESSAGE_TIME = "messageTime";

    @Override
    @Transactional
    public void saveAirData(String city, ZonedDateTime time, Integer aqi, List<AirPollutant> airPollutants, String message) {
        AirData airData = new AirData();
        airData.setMessageTime(time);
        airData.setCity(city);
        airData.setAqi(aqi);
        airData.setPollutants(airPollutants);
        airData.setMessage(message);
        airDataRepository.save(airData);
    }

    @Override
    @Async
    @Transactional
    public void saveAirDataAsync(String city, ZonedDateTime time, Integer aqi, List<AirPollutant> airPollutants, String message) {
        this.saveAirData(city, time, aqi, airPollutants, message);
    }

    @Override
    public Iterable<AirData> getLatestData(String city, Integer size) {
        Specification<AirData> specification =
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(COLUMN_CITY), city);
        Sort sort = Sort.by(Sort.Order.desc(COLUMN_MESSAGE_TIME));
        Pageable pageable = PageRequest.of(0, size, sort);
        return airDataRepository.findAll(specification, pageable);
    }

    @Override
    public Page<AirData> getDataBeforeTime(ZonedDateTime zonedDateTime, Integer page, Integer size) {
        Specification<AirData> specification =
                (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(COLUMN_MESSAGE_TIME), zonedDateTime);
        Pageable pageable = PageRequest.of(page, size);
        return airDataRepository.findAll(specification, pageable);
    }

    public void deleteBeforeTime(ZonedDateTime zonedDateTime, Integer size) {
        while (true) {
            Page<AirData> dataPage = getDataBeforeTime(zonedDateTime, 0, size);
            if (dataPage.isEmpty()) break;
            airDataRepository.deleteAll(dataPage);
        }
    }
}
