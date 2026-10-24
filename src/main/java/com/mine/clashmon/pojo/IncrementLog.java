package com.mine.clashmon.pojo;

import java.time.LocalDateTime;

public class IncrementLog {

    /**
     * CREATE TABLE IF NOT EXISTS `increment_log` (
     *     `url_id` BIGINT NOT NULL,
     *     `ts` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
     *     `increment` INT NOT NULL,
     *     PRIMARY KEY (`url_id`, `ts`),
     *     CONSTRAINT `fk_incrlog_url_id` FOREIGN KEY (`url_id`) REFERENCES `url`(`id`) ON DELETE RESTRICT
     *     ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
     *
     */
    private Long urlId;
    private LocalDateTime ts;
    private Long incrementUpload;
    private Long incrementDownload;
    private Url url;
    @Override
    public String toString() {
        return "IncrementLog{" +
                "urlId=" + urlId +
                ", ts=" + ts +
                ", incrementUpload=" + incrementUpload +
                ", incrementDownload=" + incrementDownload +
                ", url=" + url +
                '}';
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
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

    public LocalDateTime getTsUtc8() {
        return ts.plusHours(8);
    }

    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }

    public Long getIncrementUpload() {
        return incrementUpload;
    }

    public void setIncrementUpload(Long incrementUpload) {
        this.incrementUpload = incrementUpload;
    }

    public Long getIncrementDownload() {
        return incrementDownload;
    }

    public void setIncrementDownload(Long incrementDownload) {
        this.incrementDownload = incrementDownload;
    }
}
