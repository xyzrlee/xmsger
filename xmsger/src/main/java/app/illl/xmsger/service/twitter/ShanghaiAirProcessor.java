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

import app.illl.xmsger.cache.TelegramRegisteredChatCache;
import app.illl.xmsger.service.telegram.SendMessageService;
import app.illl.xmsger.struct.AirDescription;
import app.illl.xmsger.struct.twitter.CGShanghaiAir;
import app.illl.xmsger.struct.twitter.IftttTweet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.time.ZoneId;

@TweetProcessor("CGShanghaiAir")
@Slf4j
public class ShanghaiAirProcessor implements IftttTweetProcessor {

    @Autowired
    private SaveAirDataService saveAirDataService;
    @Autowired
    private TelegramRegisteredChatCache telegramRegisteredChatCache;
    @Autowired
    private SendMessageService sendMessageService;

    @Override
    public void process(IftttTweet iftttTweet) {
        CGShanghaiAir cgShanghaiAir = CGShanghaiAir.of(iftttTweet.getText());
        this.saveAirData(cgShanghaiAir);
        this.sendWarnMessage(cgShanghaiAir);
    }

    @Async
    private void saveAirData(CGShanghaiAir cgShanghaiAir) {
        AirDescription airDescription = new AirDescription();
        airDescription.setFineParticulateMatter(cgShanghaiAir.getFineParticulateMatter());
        airDescription.setAqi(cgShanghaiAir.getAqi());
        airDescription.setComment(cgShanghaiAir.getComment());
        saveAirDataService.saveAirDataAsync("Shanghai", cgShanghaiAir.getTime(), airDescription);
    }

    @Async
    private void sendWarnMessage(CGShanghaiAir cgShanghaiAir) {
        if (cgShanghaiAir.getAqi() < 100) return;
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("+08:00"));
        Boolean disableNotification = dateTime.getHour() < 10 || dateTime.getHour() > 20;
        String message = String.format(
                "shanghai air pollution:\nPM 2.5: %s, AQI: %s, %s",
                cgShanghaiAir.getFineParticulateMatter(), cgShanghaiAir.getAqi(), cgShanghaiAir.getComment()
        );
        log.debug("warnMessage:{}", message);
        for (Integer chatId : telegramRegisteredChatCache.getChatIds()) {
            sendMessageService.sendPlainText(chatId, message, disableNotification);
        }
    }


}
