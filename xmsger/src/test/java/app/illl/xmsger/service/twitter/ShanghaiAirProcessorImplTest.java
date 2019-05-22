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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShanghaiAirProcessorImplTest {

    @Autowired
    private MockMvc mvc;

    private static final String[] TWEETS = {
            "{\"username\":\"CGShanghaiAir\",\"text\":\"05-21-2019 19:00; PM2.5; 46.0; 127; Unhealthy for Sensitive Groups (at 24-hour exposure at this level)\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1130806037542367232\",\"created\":\"May 21, 2019 at 08:02PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"05-21-2019 20:00; PM2.5; 57.0; 152; Unhealthy (at 24-hour exposure at this level)\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1130821136839667712\",\"created\":\"May 21, 2019 at 09:02PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"05-21-2019 21:00; PM2.5; 46.0; 127; Unhealthy for Sensitive Groups (at 24-hour exposure at this level)\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1130836236749295617\",\"created\":\"May 21, 2019 at 10:02PM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"05-21-2019 23:00; PM2.5; 48.0; 132; Unhealthy for Sensitive Groups (at 24-hour exposure at this level)\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1130866436904108032\",\"created\":\"May 22, 2019 at 12:02AM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"05-22-2019 00:00; PM2.5; 43.0; 120; Unhealthy for Sensitive Groups (at 24-hour exposure at this level)\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1130881536880857090\",\"created\":\"May 22, 2019 at 01:02AM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"05-22-2019 01:00; PM2.5; 35.0; 100; Moderate (at 24-hour exposure at this level)\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1130896637021237248\",\"created\":\"May 22, 2019 at 02:02AM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"05-22-2019 02:00; PM2.5; 33.0; 96; Moderate (at 24-hour exposure at this level)\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1130911737811619840\",\"created\":\"May 22, 2019 at 03:02AM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"05-22-2019 03:00; PM2.5; 32.0; 94; Moderate (at 24-hour exposure at this level)\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1130926837272461313\",\"created\":\"May 22, 2019 at 04:02AM\"}",
            "{\"username\":\"CGShanghaiAir\",\"text\":\"05-22-2019 04:00; PM2.5; 37.0; 105; Unhealthy for Sensitive Groups (at 24-hour exposure at this level)\",\"link\":\"http://twitter.com/CGShanghaiAir/status/1130941938427785218\",\"created\":\"May 22, 2019 at 05:02AM\"}"
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