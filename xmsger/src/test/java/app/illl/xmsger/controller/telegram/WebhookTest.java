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

package app.illl.xmsger.controller.telegram;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class WebhookTest {

    private static final String TEST0 = "{\"update_id\":123,\"message\":{\"message_id\":301,\"from\":{\"id\":1,\"is_bot\":false,\"first_name\":\"A\",\"last_name\":\"B\",\"username\":\"C\",\"language_code\":\"zh-hans\"},\"chat\":{\"id\":1,\"first_name\":\"A\",\"last_name\":\"B\",\"username\":\"C\",\"type\":\"private\"},\"date\":1558679842,\"text\":\"sadf\"}}";

    @Autowired
    private MockMvc mvc;

    @Test
    public void hook() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.post("/telegram/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TEST0)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }
}