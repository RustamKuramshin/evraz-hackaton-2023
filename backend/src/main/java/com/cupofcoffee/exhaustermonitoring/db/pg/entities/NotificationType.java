package com.cupofcoffee.exhaustermonitoring.db.pg.entities;


public enum NotificationType {

    BROKEN("Выход из строя"),
    OVERHEATED("Перегрев"),
    OIL_STARVATION("Масляное голодание");

    private String description;

    NotificationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
