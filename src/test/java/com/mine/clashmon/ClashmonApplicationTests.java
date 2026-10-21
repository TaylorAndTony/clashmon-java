package com.mine.clashmon;

import com.mine.clashmon.mapper.IncrementMapper;
import com.mine.clashmon.mapper.RawLogMapper;
import com.mine.clashmon.mapper.UrlMapper;
import com.mine.clashmon.util.ClashResult;
import com.mine.clashmon.util.Converter;
import com.mine.clashmon.util.Web;
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

    @Test
    void contextLoads() {
        ClashResult clashResult = Web.getClashResult("https://5y4th.no-mad-sub.one/link/8jo9Kiw2MwAVmuzM?clash=3&extend=1");
        if (clashResult == null) {
            System.err.println("Failed to get clash result");
            return;
        }
        String upload = Converter.convertToReadableBytes(clashResult.getUpload());
        String download = Converter.convertToReadableBytes(clashResult.getDownload());
        String total = Converter.convertToReadableBytes(clashResult.getTotal());
        System.err.println("upload: " + upload + ", download: " + download + ", total: " + total);
    }
}
