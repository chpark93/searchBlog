package com.ch.rank;

import com.ch.ChRankApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ChRankApplication.class)
public abstract class SpringTestSupport {

}
