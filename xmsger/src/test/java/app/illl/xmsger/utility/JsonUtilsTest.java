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

package app.illl.xmsger.utility;

import app.illl.xmsger.exception.InternalServerErrorException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

@Slf4j
public class JsonUtilsTest {

    private final TestEntity testEntity = new TestEntity("AAA", new BigDecimal("213.45"), 937);
    private final String json = "{\"a\":\"AAA\",\"b\":213.45,\"c\":937}";
    private final String failJson = "{\"a\":\"AAA\",\"b\":213.45,\"c\":937,\"d\":9832}";

    @Test
    public void toJson() {
        String j = JsonUtils.toJson(testEntity);
        log.info("json:{}", j);
        Assert.assertEquals(json, j);
    }

    @Test(expected = InternalServerErrorException.class)
    public void fromJsonFailed() {
        TestEntity entity = JsonUtils.fromJson(failJson, TestEntity.class);
    }

    @Test
    public void fromJson() {
        TestEntity entity = JsonUtils.fromJson(json, TestEntity.class);
        Assert.assertEquals(testEntity, entity);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @EqualsAndHashCode
    private static class TestEntity {

        private String a;
        private BigDecimal b;
        private Integer c;

        public TestEntity() {
        }
    }

}