package app.illl.xmsger.controller.twitter;

import app.illl.xmsger.constant.Twitter;
import app.illl.xmsger.struct.twitter.IftttTweet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Twitter.PATH_TWEET)
@Slf4j
public class Tweet {

    @PostMapping
    public void post(@RequestBody IftttTweet iftttTweet) {
        if (iftttTweet == null) return;
        log.debug("tweet:{}", iftttTweet);
    }

}
