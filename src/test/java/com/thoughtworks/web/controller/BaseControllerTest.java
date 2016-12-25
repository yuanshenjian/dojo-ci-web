package com.thoughtworks.web.controller;

import com.thoughtworks.exception.GlobalExceptionHandlerControllerAdvice;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseControllerTest {
    MockMvc mockMvc;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(getMockController())
                .setHandlerExceptionResolvers(globalExceptionControllerAdvice())
                .build();
    }

    public abstract Object getMockController();

    public HandlerExceptionResolver globalExceptionControllerAdvice() {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("exceptionHandler", GlobalExceptionHandlerControllerAdvice.class);
        WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(applicationContext);
        return webMvcConfigurationSupport.handlerExceptionResolver();
    }
}
