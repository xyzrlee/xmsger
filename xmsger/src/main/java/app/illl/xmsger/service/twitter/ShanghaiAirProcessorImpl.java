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
import app.illl.xmsger.struct.AirDescription;
import app.illl.xmsger.struct.twitter.CGShanghaiAir;
import app.illl.xmsger.struct.twitter.IftttTweet;
import app.illl.xmsger.utility.AqiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.time.Duration;
import java.time.ZonedDateTime;

@TweetProcessor(TweetProcessorId.C_G_SHANGHAI_AIR)
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
        AirDescription airDescription = new AirDescription();
        airDescription.setFineParticulateMatter(cgShanghaiAir.getFineParticulateMatter());
        airDescription.setAqi(cgShanghaiAir.getAqi());
        airDescription.setComment(cgShanghaiAir.getComment());
        airDataService.saveAirDataAsync(ZoneNames.ASIA_SHANGHAI, cgShanghaiAir.getTime(), airDescription);
    }

    @Async
    private void sendWarnMessage(CGShanghaiAir cgShanghaiAir) {
        log.trace("sendMessageService:{}", sendMessageService);
        if (!AqiUtils.isUnhealthy(cgShanghaiAir.getAqi())) {
            return;
        }
        Boolean disableNotification = isDoNotDisturbTime();
        StringBuilder stringBuilder = new StringBuilder("shanghai air pollution notice");
        Long durationHours = getDurationHours(cgShanghaiAir);
        if (durationHours > 1) {
            stringBuilder.append(", lasted for ").append(durationHours).append(" hours");
        }
        stringBuilder.append(".");
        stringBuilder.append('\n').append(cgShanghaiAir.toDetailMessage());
        String message = stringBuilder.toString();
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

    private Long getDurationHours(CGShanghaiAir cgShanghaiAir) {
        ZonedDateTime zonedDateTime = cgShanghaiAir.getTime().minusHours(30);
        Iterable<AirData> airDataIterable = airDataService.getLatestData(ZoneNames.ASIA_SHANGHAI, zonedDateTime, 26);
        ZonedDateTime startTime = cgShanghaiAir.getTime();
        int healthyCount = 0;
        for (AirData airData : airDataIterable) {
            if (AqiUtils.isHealthy(airData.getDescription())) {
                healthyCount += 1;
                if (healthyCount >= 2) {
                    break;
                }
            } else {
                healthyCount = 0;
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
