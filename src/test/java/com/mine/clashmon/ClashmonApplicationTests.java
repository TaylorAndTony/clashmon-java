package com.mine.clashmon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mine.clashmon.mapper.IncrementMapper;
import com.mine.clashmon.mapper.RawLogMapper;
import com.mine.clashmon.mapper.UrlMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClashmonApplicationTests {
    @Autowired
    private UrlMapper urlMapper;
    @Autowired
    private RawLogMapper rawLogMapper;
    @Autowired
    private IncrementMapper incrementMapper;

    private static final Logger logger = LoggerFactory.getLogger(ClashmonApplicationTests.class);

    @Test
    void contextLoads() {
        logger.debug("Context loads");
        logger.info("Context loads");
    }
}
