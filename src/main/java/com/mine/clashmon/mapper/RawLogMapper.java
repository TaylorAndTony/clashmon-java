package com.mine.clashmon.mapper;

import com.mine.clashmon.pojo.RawLog;
import java.util.List;

public interface RawLogMapper {
    List<RawLog> listRawLogs(Long id);
    int insertRawLog(RawLog rawLog);
    int deleteRawLog(Long id);
    RawLog getLastRawLog(Long id);
}
