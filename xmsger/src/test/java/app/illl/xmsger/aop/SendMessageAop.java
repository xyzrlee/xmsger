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

package app.illl.xmsger.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SendMessageAop {

    @Around(
            "execution(* app.illl.xmsger.service.telegram.SendMessageService.*(..))"
                    + " && args(chatId,text,..)"
    )
    public void doAround(final ProceedingJoinPoint proceedingJoinPoint
            , Integer chatId, String text
    ) throws Throwable {
        if (NumberUtils.compare(chatId, 10) <= 0) {
            Signature signature = proceedingJoinPoint.getSignature();
            log.info("{} skipped using Aspect, text: {}", signature.getName(), text);
        } else {
            proceedingJoinPoint.proceed();
        }
    }

}
