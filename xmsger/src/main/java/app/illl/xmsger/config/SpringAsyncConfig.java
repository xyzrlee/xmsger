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

package app.illl.xmsger.config;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
@Slf4j
@ConditionalOnProperty(name = "app.illl.xmsger.async.enable", havingValue = "true", matchIfMissing = true)
public class SpringAsyncConfig implements AsyncConfigurer, InitializingBean, DisposableBean {

    @Setter(AccessLevel.PROTECTED)
    private ExecutorService executor;

    private void initializeExecutor() {
        this.setExecutor(Executors.newCachedThreadPool());
    }

    private void terminateExecutor() {
        if (executor.isShutdown()) return;
        this.executor.shutdown();
        try {
            this.executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("", e);
            Thread.currentThread().interrupt();
        } finally {
            if (!this.executor.isTerminated()) {
                this.executor.shutdownNow();
            }

        }
    }

    @Override
    public Executor getAsyncExecutor() {
        return this.executor;
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void afterPropertiesSet() throws Exception {
        this.initializeExecutor();
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void destroy() throws Exception {
        this.terminateExecutor();
    }

}
