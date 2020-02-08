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
public class DefaultAnalyseProcessorImplTest {

    private static final String TEST0 = "{\"username\":\"realDonaldTrump\",\"text\":\"A great win for Brooks. Congratulations to a great champion! https://t.co/74z0n2icuA\",\"link\":\"http://twitter.com/realDonaldTrump/status/1130296183795126277\",\"created\":\"May 20, 2019 at 10:16AM\"}";
    private static final String TEST1 = "{\"username\":\"realDonaldTrump\",\"text\":\"test1: A great win for Brooks. Congratulations to a great champion! https://t.co/74z0n2icuA\",\"link\":\"http://twitter.com/realDonaldTrump/status/1130296183795126277\",\"created\":\"May 20, 2019 at 10:16AM\"}";
    private static final String TEST2 = "{\"username\":\"realDonaldTrump\",\"text\":\"test2: A great win for Brooks. Congratulations to a great champion! https://t.co/74z0n2icuA\",\"link\":\"http://twitter.com/realDonaldTrump/status/1130296183795126277\",\"created\":\"May 20, 2019 at 10:16AM\"}";
    private static final String TEST3 = "{\"username\":\"realDonaldTrump\",\"text\":\"test3: A great win for Brooks. Congratulations to a great champion! https://t.co/74z0n2icuA\",\"link\":\"http://twitter.com/realDonaldTrump/status/1130296183795126277\",\"created\":\"May 20, 2019 at 10:16AM\"}";
    private static final String TEST4 = "{\"username\":\"realDonaldTrump\",\"text\":\"test4: A great win for Brooks. Congratulations to a great champion! https://t.co/74z0n2icuA\",\"link\":\"http://twitter.com/realDonaldTrump/status/1130296183795126277\",\"created\":\"May 20, 2019 at 10:16AM\"}";

    @Autowired
    private MockMvc mvc;

    @Test
    public void processWithoutKeyword() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.post("/twitter/tweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TEST0)
        )
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void processWithKeyword() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.post("/twitter/tweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TEST1)
        )
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void processWithKeywordIgnoreCase() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.post("/twitter/tweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TEST2)
        )
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void processWithKeywordDeactived() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.post("/twitter/tweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TEST3)
        )
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void processWithKeywordForAnotherUser() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.post("/twitter/tweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TEST4)
        )
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

}