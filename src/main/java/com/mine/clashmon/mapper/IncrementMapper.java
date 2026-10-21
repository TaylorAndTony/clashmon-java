package com.mine.clashmon.mapper;

import com.mine.clashmon.pojo.IncrementLog;

import java.util.List;

public interface IncrementMapper {
    List<IncrementLog> listIncrementLogs(Long id);
    int insertIncrementLog(IncrementLog incrementLog);
    int deleteIncrementLog(Long id);
    IncrementLog getLastIncrement(Long id);
}
