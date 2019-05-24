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