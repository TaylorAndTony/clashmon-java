package com.mine.clashmon.controller;

import com.mine.clashmon.mapper.IncrementMapper;
import com.mine.clashmon.mapper.RawLogMapper;
import com.mine.clashmon.mapper.UrlMapper;
import com.mine.clashmon.pojo.IncrementLog;
import com.mine.clashmon.pojo.RawLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private UrlMapper urlMapper;
    @Autowired
    private RawLogMapper rawLogMapper;
    @Autowired
    private IncrementMapper incrementMapper;

    @DeleteMapping("delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        urlMapper.deleteUrl(id);
        return Map.of(
                "success", true
        );
    }

    @GetMapping("data/raw/{id}")
    public Map<String, Object> getRawData(@PathVariable Long id) {
        var allLogs = rawLogMapper.listRawLogs(id);
        List<LocalDateTime> dates = allLogs.stream().map(RawLog::getTs).toList();
        List<Long> uploadValues = allLogs.stream().map(RawLog::getUsedUpload).toList();
        List<Long> downloadValues = allLogs.stream().map(RawLog::getUsedDownload).toList();
        List<Long> totalValues = allLogs.stream().map(RawLog::getTotal).toList();
        return Map.of(
                "success", true,
                "data", Map.of(
                        "dates", dates,
                        "uploadValues", uploadValues,
                        "downloadValues", downloadValues,
                        "totalValues", totalValues
                )
        );
    }

    @GetMapping("data/increment/{id}")
    public Map<String, Object> getIncrementData(@PathVariable Long id) {
        var allIncrementLogs = incrementMapper.listIncrementLogs(id);
        List<LocalDateTime> dates = allIncrementLogs.stream().map(IncrementLog::getTs).toList();
        List<Long> uploadValues = allIncrementLogs.stream().map(IncrementLog::getIncrementUpload).toList();
        List<Long> downloadValues = allIncrementLogs.stream().map(IncrementLog::getIncrementDownload).toList();
        return Map.of(
                "success", true,
                "data", Map.of(
                        "dates", dates,
                        "uploadValues", uploadValues,
                        "downloadValues", downloadValues
                )
        );
    }
}
