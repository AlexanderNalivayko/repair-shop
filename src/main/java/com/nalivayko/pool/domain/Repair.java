package com.nalivayko.pool.domain;

public class Repair {

    private Long id;
    private Integer userId;
    private RepairStatus status;
    private String item;
    private String description;
    private String pictureUrl;
    private Long price;
    private String masterNotes;
    private String userFeedback;

    private Repair(Long id, int userId, RepairStatus status, String item, String description,
                   String pictureUrl, Long price, String masterNotes, String userFeedback) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.item = item;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.price = price;
        this.masterNotes = masterNotes;
        this.userFeedback = userFeedback;
    }

    private Repair() {
    }

    public static class Builder {
        private Long id;
        private Integer userId;
        private RepairStatus status;
        private String item;
        private String description;
        private String pictureUrl;
        private Long price;
        private String masterNotes;
        private String userFeedback;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder setStatus(RepairStatus status) {
            this.status = status;
            return this;
        }

        public Builder setItem(String item) {
            this.item = item;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
            return this;
        }

        public Builder setPrice(Long price) {
            this.price = price;
            return this;
        }

        public Builder setMasterNotes(String masterNotes) {
            this.masterNotes = masterNotes;
            return this;
        }

        public Builder setUserFeedback(String userFeedback) {
            this.userFeedback = userFeedback;
            return this;
        }

        public Repair build() {
            return new Repair(id, userId, status, item, description, pictureUrl, price,
                    masterNotes, userFeedback);
        }
    }

    public Long getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public RepairStatus getStatus() {
        return status;
    }

    public String getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public Long getPrice() {
        return price;
    }

    public String getMasterNotes() {
        return masterNotes;
    }

    public String getUserFeedback() {
        return userFeedback;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", item='" + item + '\'' +
                ", description='" + description + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", price=" + price +
                ", masterNotes='" + masterNotes + '\'' +
                ", userFeedback='" + userFeedback + '\'' +
                '}';
    }
}
