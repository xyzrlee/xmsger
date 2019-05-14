package app.illl.xmsger.controller.telegram;

import app.illl.xmsger.constant.Telegram;
import app.illl.xmsger.service.telegram.RegisterIdService;
import app.illl.xmsger.service.telegram.SendMessageService;
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
    private SendMessageService sendMessageService;
    @Autowired
    private RegisterIdService registerIdService;

    @PostMapping
    public void hook(@RequestBody Update update) {
        if (update == null) return;
        if (update.getMessage() != null) {
            registerIdService.registerId(update.getMessage());
            sendMessageService.replyPlainText(
                    update.getMessage().getChat().getId(),
                    update.getMessage().getText(),
                    update.getMessage().getMessageId()
            );
        }
    }

}
