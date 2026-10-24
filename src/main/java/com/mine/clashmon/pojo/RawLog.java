package com.mine.clashmon.pojo;

import com.mine.clashmon.util.Converter;

import java.time.LocalDateTime;

public class RawLog {

    /**
     *
     CREATE TABLE IF NOT EXISTS `raw_log` (
     `url_id` BIGINT NOT NULL,
     `ts` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
     `used` INT NOT NULL,
     `total` INT NOT NULL,
     PRIMARY KEY (`url_id`, `ts`),
     CONSTRAINT `fk_rawlog_url_id` FOREIGN KEY (`url_id`) REFERENCES `url`(`id`) ON DELETE RESTRICT
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
     */

    private Long urlId;
    private LocalDateTime ts;
    private Long usedUpload;
    private Long usedDownload;
    private Long total;
    private Url url;

    public String getUploadReadable(){
        return Converter.convertToReadableBytes(usedUpload);
    }
    public String getDownloadReadable(){
        return Converter.convertToReadableBytes(usedDownload);
    }
    public String getTotalReadable(){
        return Converter.convertToReadableBytes(total);
    }

    public Long getUsedUpload() {
        return usedUpload;
    }

    public void setUsedUpload(Long usedUpload) {
        this.usedUpload = usedUpload;
    }

    public Long getUsedDownload() {
        return usedDownload;
    }

    public void setUsedDownload(Long usedDownload) {
        this.usedDownload = usedDownload;
    }

    @Override
    public String toString() {
        return "RawLog{" +
                "urlId=" + urlId +
                ", ts=" + ts +
                ", total=" + total +
                ", url=" + url +
                '}';
    }

    public Long getUrlId() {
        return urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }

    public LocalDateTime getTs() {
        return ts;
    }

    public LocalDateTime getTsUtc8(){
        return ts.plusHours(8);
    }

    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }
}
