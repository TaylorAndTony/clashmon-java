package com.mine.clashmon.mapper;

import com.mine.clashmon.pojo.Url;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UrlMapper {
    List<Url> listUrl();
    int insertUrl(Url url);
    int updateUrl(Url url);
    int updateUrlByIdUrlDesc(@Param("id") Long id, @Param("url") String url, @Param("desc") String desc);
    int deleteUrl(Long id);
    int deleteRawLogByUrlId(Long id);
    int deleteIncrementLogByUrlId(Long id);
    int deleteUrlById(Long id);
}
