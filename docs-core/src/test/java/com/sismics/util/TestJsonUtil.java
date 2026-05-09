package com.sismics.util;

import jakarta.json.JsonValue;
import org.junit.Assert;
import org.junit.Test;

public class TestJsonUtil {

    @Test
    public void nullableStringTest() {
        JsonValue nullResult = JsonUtil.nullable((String) null);
        Assert.assertEquals(JsonValue.NULL, nullResult);

        JsonValue stringResult = JsonUtil.nullable("test string");
        Assert.assertNotNull(stringResult);
        Assert.assertEquals(JsonValue.ValueType.STRING, stringResult.getValueType());
    }

    @Test
    public void nullableIntegerTest() {
        JsonValue nullResult = JsonUtil.nullable((Integer) null);
        Assert.assertEquals(JsonValue.NULL, nullResult);

        JsonValue intResult = JsonUtil.nullable(42);
        Assert.assertNotNull(intResult);
        Assert.assertEquals(JsonValue.ValueType.NUMBER, intResult.getValueType());
        Assert.assertEquals("42", intResult.toString());

        JsonValue zeroResult = JsonUtil.nullable(0);
        Assert.assertEquals("0", zeroResult.toString());

        JsonValue negativeResult = JsonUtil.nullable(-100);
        Assert.assertEquals("-100", negativeResult.toString());
    }

    @Test
    public void nullableLongTest() {
        JsonValue nullResult = JsonUtil.nullable((Long) null);
        Assert.assertEquals(JsonValue.NULL, nullResult);

        JsonValue longResult = JsonUtil.nullable(123456789L);
        Assert.assertNotNull(longResult);
        Assert.assertEquals(JsonValue.ValueType.NUMBER, longResult.getValueType());
        Assert.assertEquals("123456789", longResult.toString());

        JsonValue largeResult = JsonUtil.nullable(Long.MAX_VALUE);
        Assert.assertEquals(String.valueOf(Long.MAX_VALUE), largeResult.toString());

        JsonValue zeroResult = JsonUtil.nullable(0L);
        Assert.assertEquals("0", zeroResult.toString());
    }

    @Test
    public void nullableStringEdgeCasesTest() {
        JsonValue emptyResult = JsonUtil.nullable("");
        Assert.assertNotNull(emptyResult);
        Assert.assertEquals(JsonValue.ValueType.STRING, emptyResult.getValueType());

        JsonValue specialResult = JsonUtil.nullable("Hello, World! Test");
        Assert.assertNotNull(specialResult);
    }
}
