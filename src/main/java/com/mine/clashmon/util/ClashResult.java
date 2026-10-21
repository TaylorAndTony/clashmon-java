package com.mine.clashmon.util;

public class ClashResult {
    private long upload;
    private long download;
    private long total;

    public ClashResult(long upload, long download, long total) {
        this.upload = upload;
        this.download = download;
        this.total = total;
    }

    @Override
    public String toString() {
        return "ClashResult{" +
                "upload=" + upload +
                ", download=" + download +
                ", total=" + total +
                '}';
    }

    public long getUpload() {
        return upload;
    }

    public void setUpload(long upload) {
        this.upload = upload;
    }

    public long getDownload() {
        return download;
    }

    public void setDownload(long download) {
        this.download = download;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
