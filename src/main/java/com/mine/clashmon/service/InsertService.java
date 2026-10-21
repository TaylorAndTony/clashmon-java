package com.mine.clashmon.service;


import com.mine.clashmon.mapper.IncrementMapper;
import com.mine.clashmon.mapper.RawLogMapper;
import com.mine.clashmon.mapper.UrlMapper;
import com.mine.clashmon.pojo.IncrementLog;
import com.mine.clashmon.pojo.RawLog;
import com.mine.clashmon.util.ClashResult;
import org.springframework.stereotype.Service;

@Service
public class InsertService {
    private UrlMapper urlMapper;
    private RawLogMapper rawLogMapper;
    private IncrementMapper incrementMapper;

    public InsertService(UrlMapper urlMapper, RawLogMapper rawLogMapper, IncrementMapper incrementMapper){
        this.urlMapper = urlMapper;
        this.rawLogMapper = rawLogMapper;
        this.incrementMapper = incrementMapper;
    }

    public void insertClashResult(long urlId, ClashResult clashResult){
        RawLog newLog = new RawLog();
        newLog.setUrlId(urlId);
        newLog.setUsedUpload(clashResult.getUpload());
        newLog.setUsedDownload(clashResult.getDownload());
        newLog.setTotal(clashResult.getTotal());
        rawLogMapper.insertRawLog(newLog);
        RawLog lastLog = rawLogMapper.getLastRawLog(urlId);

        Long uploadChanged = lastLog.getUsedUpload() - rawLogMapper.getLastRawLog(urlId).getUsedUpload();
        Long downloadChanged = lastLog.getUsedDownload() - rawLogMapper.getLastRawLog(urlId).getUsedDownload();

        IncrementLog incrementLog = new IncrementLog();
        incrementLog.setUrlId(urlId);
        incrementLog.setIncrementUpload(Math.max(uploadChanged, 0L));
        incrementLog.setIncrementDownload(Math.max(downloadChanged, 0L));
        incrementMapper.insertIncrementLog(incrementLog);
    }
}
