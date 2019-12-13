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

package app.illl.xmsger.service.twitter;

import app.illl.xmsger.struct.twitter.CGShanghaiAir;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.ZonedDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShanghaiAirProcessorImplTest {

    @Autowired
    private MockMvc mvc;

    private static final String[] TWEETS = {
            "{\"username\":\"CGShanghaiAir\",\"text\":\"Shanghai - 2019-12-12 1PM - PM2.5 - 186 AQI - Moderate\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1205006034806300672\",\"created\":\"December 12, 2019 at 02:06PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"Shanghai - " + ZonedDateTime.now().minusHours(10).format(CGShanghaiAir.FORMATTER) + " - PM2.5 - 186 AQI - Moderate\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1205006034806300672\",\"created\":\"December 12, 2019 at 02:06PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"Shanghai - " + ZonedDateTime.now().minusHours(9).format(CGShanghaiAir.FORMATTER) + " - PM2.5 - 186 AQI - Moderate\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1205006034806300672\",\"created\":\"December 12, 2019 at 02:06PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"Shanghai - " + ZonedDateTime.now().minusHours(8).format(CGShanghaiAir.FORMATTER) + " - PM2.5 - 186 AQI - Moderate\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1205006034806300672\",\"created\":\"December 12, 2019 at 02:06PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"Shanghai - " + ZonedDateTime.now().minusHours(7).format(CGShanghaiAir.FORMATTER) + " - PM2.5 - 186 AQI - Moderate\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1205006034806300672\",\"created\":\"December 12, 2019 at 02:06PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"Shanghai - " + ZonedDateTime.now().minusHours(6).format(CGShanghaiAir.FORMATTER) + " - PM2.5 - 186 AQI - Moderate\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1205006034806300672\",\"created\":\"December 12, 2019 at 02:06PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"Shanghai - " + ZonedDateTime.now().minusHours(5).format(CGShanghaiAir.FORMATTER) + " - PM2.5 - 186 AQI - Moderate\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1205006034806300672\",\"created\":\"December 12, 2019 at 02:06PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"Shanghai - " + ZonedDateTime.now().minusHours(4).format(CGShanghaiAir.FORMATTER) + " - PM2.5 - 186 AQI - Moderate\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1205006034806300672\",\"created\":\"December 12, 2019 at 02:06PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"Shanghai - " + ZonedDateTime.now().minusHours(3).format(CGShanghaiAir.FORMATTER) + " - PM2.5 - 186 AQI - Moderate\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1205006034806300672\",\"created\":\"December 12, 2019 at 02:06PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"Shanghai - " + ZonedDateTime.now().minusHours(2).format(CGShanghaiAir.FORMATTER) + " - PM2.5 - 186 AQI - Moderate\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1205006034806300672\",\"created\":\"December 12, 2019 at 02:06PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"Shanghai - " + ZonedDateTime.now().minusHours(1).format(CGShanghaiAir.FORMATTER) + " - PM2.5 - 186 AQI - Moderate\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1205006034806300672\",\"created\":\"December 12, 2019 at 02:06PM\"}"
    };

    @Test
    public void process() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.post("/twitter/tweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TWEETS[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void testDuration() throws Exception {
        for (String tweet : TWEETS) {
            this.mvc.perform(
                    MockMvcRequestBuilders.post("/twitter/tweet")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(tweet)
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())
            ;
        }
    }

}