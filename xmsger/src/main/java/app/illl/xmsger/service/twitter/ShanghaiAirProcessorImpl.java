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
import app.illl.xmsger.constant.TweetProcessorId;
import app.illl.xmsger.constant.ZoneIds;
import app.illl.xmsger.constant.ZoneNames;
import app.illl.xmsger.datasource.entity.AirData;
import app.illl.xmsger.datasource.service.AirDataService;
import app.illl.xmsger.service.telegram.SendMessageService;
import app.illl.xmsger.struct.twitter.CGShanghaiAir;
import app.illl.xmsger.struct.twitter.IftttTweet;
import app.illl.xmsger.utility.AqiUtils;
import app.illl.xmsger.utility.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.time.Duration;
import java.time.ZonedDateTime;

@TweetProcessor(TweetProcessorId.CON_GEN_SHANGHAI_AIR)
@Slf4j
@RequiredArgsConstructor
public class ShanghaiAirProcessorImpl implements ShanghaiAirProcessor {

    private final AirDataService airDataService;
    private final TelegramRegisteredChatCache telegramRegisteredChatCache;
    private final SendMessageService sendMessageService;

    @Override
    public void process(IftttTweet iftttTweet) {
        CGShanghaiAir cgShanghaiAir = CGShanghaiAir.of(iftttTweet.getText());
        this.saveAirData(cgShanghaiAir);
        this.sendWarnMessage(cgShanghaiAir);
    }

    @Async
    private void saveAirData(CGShanghaiAir cgShanghaiAir) {
        airDataService.saveAirDataAsync(
                ZoneNames.ASIA_SHANGHAI,
                cgShanghaiAir.getTime(),
                cgShanghaiAir.getAqi(),
                cgShanghaiAir.getAirPollutants(),
                JsonUtils.toJson(cgShanghaiAir)
        );
    }

    @Async
    private void sendWarnMessage(CGShanghaiAir cgShanghaiAir) {
        log.trace("sendMessageService:{}", sendMessageService);
        if (!AqiUtils.isUnhealthy(cgShanghaiAir.getAqi())) {
            return;
        }
        Boolean disableNotification = isDoNotDisturbTime();
        String message = "shanghai air pollution notice"
                + this.getDurationMessage(cgShanghaiAir)
                + "." + '\n'
                + cgShanghaiAir.toDetailMessage();
        log.debug("warnMessage:{}", message);
        log.debug("sending to {} ids", telegramRegisteredChatCache.getChatIds().size());
        for (Integer chatId : telegramRegisteredChatCache.getChatIds()) {
            sendMessageService.sendPlainText(chatId, message, disableNotification);
        }
    }

    private Boolean isDoNotDisturbTime() {
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneIds.LOCAL);
        return dateTime.getHour() < 10 || dateTime.getHour() > 20;
    }

    private String getDurationMessage(CGShanghaiAir cgShanghaiAir) {
        long hours = this.getDurationHours(cgShanghaiAir);
        if (hours > 0) {
            if (hours <= 24) {
                return ", lasted for " + hours + " hours";
            }
            return ", lasted for more than 24 hours";
        }
        return "";
    }

    private Long getDurationHours(CGShanghaiAir cgShanghaiAir) {
        Iterable<AirData> airDataIterable = airDataService.getLatestData(ZoneNames.ASIA_SHANGHAI, 26);
        ZonedDateTime startTime = cgShanghaiAir.getTime();
        int healthyCount = 0;
        for (AirData airData : airDataIterable) {
            if (AqiUtils.isHealthy(airData.getAqi())) {
                healthyCount += 1;
            } else {
                healthyCount = 0;
            }
            if (healthyCount > 2 || Duration.between(airData.getMessageTime(), startTime).toHours() > 2) {
                break;
            }
            startTime = airData.getMessageTime();
        }
        log.trace("startTime:{}, endTime:{}", startTime, cgShanghaiAir.getTime());
        Duration duration = Duration.between(startTime, cgShanghaiAir.getTime());
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        if (minutes > 30) {
            hours += 1;
        }
        log.trace("hours:{}", hours);
        return hours;
    }

}
