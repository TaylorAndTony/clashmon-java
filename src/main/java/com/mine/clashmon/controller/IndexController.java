package com.mine.clashmon.controller;


import com.mine.clashmon.mapper.IncrementMapper;
import com.mine.clashmon.mapper.RawLogMapper;
import com.mine.clashmon.mapper.UrlMapper;
import com.mine.clashmon.pojo.IndexModel;
import com.mine.clashmon.pojo.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UrlMapper urlMapper;
    @Autowired
    private RawLogMapper rawLogMapper;
    @Autowired
    private IncrementMapper incrementMapper;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index(Model model) {
        final List<IndexModel> indexModels = new ArrayList<>();
        final List<Url> urls = urlMapper.listUrl();
        for (Url u : urls) {
            IndexModel indexModel = new IndexModel();
            indexModel.setUrl(u);
            indexModel.setRawLogs(rawLogMapper.listRawLogs(u.getId()));
            indexModel.setIncrementLogs(incrementMapper.listIncrementLogs(u.getId()));
            indexModels.add(indexModel);
        }
        model.addAttribute("urlList", urls); // 添加URL列表
        model.addAttribute("models", indexModels); // 添加原始日志列表

        return "index";
    }
    @GetMapping("/manage")
    public String manage(Model model,
                         @RequestParam(required = false) String link,
                         @RequestParam(required = false) String desc) {
        model.addAttribute("test", "test"); // 添加测试数据
        model.addAttribute("urlList", urlMapper.listUrl()); // 添加URL列表

        if (link != null && link != "") {
            logger.info("link: {}", link);
            var allUrls = urlMapper.listUrl();
            for (Url u : allUrls) {
                if (u.getUrl().equals(link)) {
                    logger.warn("Url already exists");
                    return "redirect:/manage";
                }
            }
            Url url = new Url();
            url.setUrl(link);
            url.setDescription(desc);
            url.setCreatedAt(java.time.LocalDateTime.now()); // Set current timestamp
            url.setModifiedAt(java.time.LocalDateTime.now()); // Set current timestamp
            urlMapper.insertUrl(url);
            logger.info("Url inserted successfully");
            return "redirect:/manage";
        }
        return "manage";
    }

}
