package com.gmail.supersonicleader.repository.model;

import java.util.UUID;

public class Document {

    private Long id;
    private String description;
    private UUID uniqueNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(UUID uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

}
