package app.illl.xmsger.controller.telegram;

import app.illl.xmsger.constant.Telegram;
import app.illl.xmsger.service.telegram.SendMessageService;
import app.illl.xmsger.struct.telegram.SendMessage;
import app.illl.xmsger.struct.telegram.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = Telegram.PATH_WEBHOOK)
@Slf4j
public class Webhook {

    @Autowired
    SendMessageService sendMessageService;

    @PostMapping
    public void hook(@RequestBody Update update) {
        log.info("update:{}", update);
        SendMessage<String> sendMessage = new SendMessage<>();
        sendMessage.setChatId(update.getMessage().getChat().getId());
        sendMessage.setText(update.getMessage().getText());
        sendMessageService.sendMessage(sendMessage);
    }

}
