package com.mine.clashmon.pojo;

import java.time.LocalDateTime;
import java.util.List;

public class Url {
    /**
     * CREATE TABLE IF NOT EXISTS `url` (
     * `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
     * `url` VARCHAR(1024) NOT NULL,
     * `description` VARCHAR(512) NOT NULL,
     * `created_at` DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3),
     * `modified_at` DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
     */
    private Long id;
    private String url;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
