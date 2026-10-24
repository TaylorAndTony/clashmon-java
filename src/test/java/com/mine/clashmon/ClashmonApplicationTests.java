package com.mine.clashmon;

import com.mine.clashmon.util.ClashResult;
import com.mine.clashmon.util.Web;
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

    @Test
    void checkTraffic(){
        ClashResult clashResult = Web.getClashResult(
                "https://47.76.155.27/iv/verify_mode.htm?token=aa5d734437042dcfa3cd7eee7b1cf4f0&sid=nano"
        );
        logger.info(clashResult.toString());
    }
}
