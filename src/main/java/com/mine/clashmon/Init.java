package com.mine.clashmon;


import com.mine.clashmon.mapper.IncrementMapper;
import com.mine.clashmon.mapper.RawLogMapper;
import com.mine.clashmon.mapper.UrlMapper;
import com.mine.clashmon.service.InsertService;
import com.mine.clashmon.util.ClashResult;
import com.mine.clashmon.util.Web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class Init implements CommandLineRunner {
    @Autowired
    private InsertService insertService;
    @Autowired
    private UrlMapper urlMapper;
    @Autowired
    private RawLogMapper rawLogMapper;
    @Autowired
    private IncrementMapper incrementMapper;

    @Override
    public void run(String... args) throws Exception {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(() -> {
            for (var i : urlMapper.listUrl()) {
                long id = i.getId();
                String url = i.getUrl();
                ClashResult clashResult = Web.getClashResult(url);
                if (clashResult == null){
                    return;
                }
                insertService.insertClashResult(id, clashResult);
            }
        }, 0, 1, TimeUnit.HOURS);
    }
}
