package com.thoughtworks.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UUIDUtilsTest {

    @Test
    public void test_should_create_uuid() throws Exception {
        String uuid = UUIDUtils.createUUID();
        assertEquals(uuid.length(), 32);
    }
}
