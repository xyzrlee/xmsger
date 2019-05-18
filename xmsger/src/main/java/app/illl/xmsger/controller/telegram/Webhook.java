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

package app.illl.xmsger.controller.telegram;

import app.illl.xmsger.constant.Telegram;
import app.illl.xmsger.service.telegram.RegisterIdService;
import app.illl.xmsger.service.telegram.SendMessageService;
import app.illl.xmsger.struct.telegram.type.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = Telegram.PATH_WEBHOOK)
@Slf4j
@RequiredArgsConstructor
public class Webhook {

    private final SendMessageService sendMessageService;
    private final RegisterIdService registerIdService;

    @PostMapping
    public void hook(@RequestBody Update update) {
        if (update == null) return;
        if (update.getMessage() == null) return;
        if (update.getMessage().getText() == null) return;
        registerIdService.registerIdAsync(update.getMessage());
        sendMessageService.replyPlainText(
                update.getMessage().getChat().getId(),
                update.getMessage().getText(),
                update.getMessage().getMessageId()
        );
    }

}
