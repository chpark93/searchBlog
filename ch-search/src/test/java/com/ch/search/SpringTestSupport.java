package com.ch.search;

import com.ch.ChSearchApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ChSearchApplication.class)
public abstract class SpringTestSupport {

}
