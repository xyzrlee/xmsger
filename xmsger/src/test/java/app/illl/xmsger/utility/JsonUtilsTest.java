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