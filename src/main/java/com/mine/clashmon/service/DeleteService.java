package com.mine.clashmon.service;

import com.mine.clashmon.mapper.IncrementMapper;
import com.mine.clashmon.mapper.RawLogMapper;
import com.mine.clashmon.mapper.UrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteService {
    @Autowired
    private UrlMapper urlMapper;
    @Autowired
    private RawLogMapper rawLogMapper;
    @Autowired
    private IncrementMapper incrementMapper;

    @Transactional
    public void forceDelete(Long id) {
        urlMapper.deleteRawLogByUrlId(id);
        urlMapper.deleteIncrementLogByUrlId(id);
        urlMapper.deleteUrlById(id);
    }
}
