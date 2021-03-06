package org.baeldung.boot;

import org.baeldung.session.exception.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource("classpath:exception.properties")
public class ApplicationTests {
    @Test
    public void contextLoads() {
    }
}
