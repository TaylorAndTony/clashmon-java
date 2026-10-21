package com.mine.clashmon.mapper;

import com.mine.clashmon.pojo.Url;

import java.util.List;

public interface UrlMapper {
    List<Url> listUrl();
    int insertUrl(Url url);
    int updateUrl(Url url);
    int deleteUrl(Long id);
}
