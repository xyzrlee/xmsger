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

import app.illl.xmsger.constant.Telegram;
import app.illl.xmsger.datasource.entity.TelegramSendToken;
import app.illl.xmsger.datasource.service.TelegramSendTokenService;
import app.illl.xmsger.service.telegram.SendMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = Telegram.PATH_SENDIT)
@Slf4j
@RequiredArgsConstructor
public class SendIt {

    private final SendMessageService sendMessageService;
    private final TelegramSendTokenService telegramSendTokenService;

    @PostMapping
    public void hook(@PathVariable("token") String token, @RequestBody String text) {
        if (null == token) return;
        TelegramSendToken telegramSendToken = telegramSendTokenService.getByToken(token);
        if (null == telegramSendToken) return;
        sendMessageService.sendPlainText(telegramSendToken.getChatId(), text);
    }

}
