package com.mine.clashmon.pojo;

import java.util.List;

public class IndexModel {
    private Url url;
    private List<RawLog> rawLogs;
    private List<IncrementLog> incrementLogs;

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public List<RawLog> getRawLogs() {
        return rawLogs;
    }

    public void setRawLogs(List<RawLog> rawLogs) {
        this.rawLogs = rawLogs;
    }

    public List<IncrementLog> getIncrementLogs() {
        return incrementLogs;
    }

    public void setIncrementLogs(List<IncrementLog> incrementLogs) {
        this.incrementLogs = incrementLogs;
    }
}
