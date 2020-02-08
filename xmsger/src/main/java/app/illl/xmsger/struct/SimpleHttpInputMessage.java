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

package app.illl.xmsger.struct;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SimpleHttpInputMessage implements HttpInputMessage {

    private HttpHeaders httpHeaders;
    private InputStream inputStream;

    public SimpleHttpInputMessage(HttpInputMessage httpInputMessage, String message) {
        this.httpHeaders = httpInputMessage.getHeaders();
        this.inputStream = IOUtils.toInputStream(message, StandardCharsets.UTF_8);
    }

    @Override
    public InputStream getBody() throws IOException {
        return this.inputStream;
    }

    @Override
    public HttpHeaders getHeaders() {
        return this.httpHeaders;
    }
}
