package app.illl.xmsger.controller.twitter;

import app.illl.xmsger.constant.Twitter;
import app.illl.xmsger.struct.twitter.IftttTwitterMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Twitter.PATH_TRUMP)
@Slf4j
public class Trump {

    @PostMapping
    public void post(@RequestBody IftttTwitterMessage iftttTwitterMessage) {
        if (iftttTwitterMessage == null) return;
        if (iftttTwitterMessage.getText() == null) return;
        log.debug("text:{}", iftttTwitterMessage.getText());
    }

}
