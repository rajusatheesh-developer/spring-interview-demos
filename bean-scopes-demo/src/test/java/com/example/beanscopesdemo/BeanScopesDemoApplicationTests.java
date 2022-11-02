package com.example.beanscopesdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class BeanScopesDemoApplicationTests {

    @Autowired
    SingletonScope singletonScope1;

    @Autowired
    SingletonScope singletonScope2;

    @Autowired
    PrototypeScope prototypeScope1;

    @Autowired
    PrototypeScope prototypeScope2;

    @Test
    void testSingletonScope() {
        singletonScope1.setName("One");
        assertThat("singleton",singletonScope1.getName(),equalTo(singletonScope2.getName()));
    }
    @Test
    void testPrototypeScope() {
        prototypeScope1.setName("One");
        assertThat("prototype",prototypeScope1.getName(),not(prototypeScope2.getName()));

        prototypeScope1.setName("One");
        prototypeScope2.setName("Two");
        assertThat("prototype1",prototypeScope1.getName(),equalTo("One"));
        assertThat("prototype2",prototypeScope2.getName(),equalTo("Two"));
    }


}
