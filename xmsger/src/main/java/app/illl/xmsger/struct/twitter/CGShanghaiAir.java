/*
 *     XMsger
 *     Copyright (C) 2019  Ricky Li
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package app.illl.xmsger.struct.twitter;

import app.illl.xmsger.constant.ZoneIds;
import app.illl.xmsger.struct.AirPollutant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Data
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CGShanghaiAir implements Serializable {

    private static final long serialVersionUID = 4590068789519494506L;

    private CGShanghaiAir() {
    }

    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd ha");

    private ZonedDateTime time;
    private String pollutantType;
    private BigDecimal concentration;
    private Integer aqi;
    private String definition;
    private String city;

    public static CGShanghaiAir of(String text) {
        String[] fields = StringUtils.splitByWholeSeparator(text, " - ");
        CGShanghaiAir cgShanghaiAir = new CGShanghaiAir();
        cgShanghaiAir.setCity(fields[0].trim());
        cgShanghaiAir.setTime(ZonedDateTime.of(LocalDateTime.parse(fields[1].trim(), FORMATTER), ZoneIds.ASIA_SHANGHAI));
        cgShanghaiAir.setPollutantType(fields[2].trim());
        cgShanghaiAir.setConcentration(null);
        cgShanghaiAir.setAqi(Integer.valueOf(StringUtils.split(fields[3].trim(), ' ')[0]));
        cgShanghaiAir.setDefinition(fields[4].trim());
        return cgShanghaiAir;
    }

    public String toDetailMessage() {
        return String.format(
                "%s: %s, AQI: %s, %s",
                getPollutantType(), getConcentration(), getAqi(), getDefinition()
        );
    }

    public List<AirPollutant> getAirPollutants() {
        List<AirPollutant> pollutants = new LinkedList<>();
        AirPollutant airPollutant = new AirPollutant();
        airPollutant.setType(this.getPollutantType());
        airPollutant.setConcentration(this.getConcentration());
        pollutants.add(airPollutant);
        return pollutants;
    }

    @JsonProperty("time")
    public String timeToJson() {
        return time.toString();
    }

    @JsonProperty("time")
    public void timeFromJson(String str) {
        this.time = ZonedDateTime.parse(str);
    }

}
