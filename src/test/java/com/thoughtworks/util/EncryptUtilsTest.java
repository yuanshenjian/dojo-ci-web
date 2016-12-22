package com.thoughtworks.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.TestCase.assertFalse;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class EncryptUtilsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtilsTest.class);

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void should_encrypt_to_unique_password() throws Exception {
        String password = "000";
        String encryptPassword1 = EncryptUtils.encryptPassword(password);
        String encryptPassword2 = EncryptUtils.encryptPassword(password);
        LOGGER.info(encryptPassword1);
        LOGGER.info(encryptPassword2);
        assertFalse(encryptPassword1.equals(encryptPassword2));
    }
}