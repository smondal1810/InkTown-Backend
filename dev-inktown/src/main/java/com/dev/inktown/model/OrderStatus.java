package com.dev.inktown.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    DESIGN_PENDING(0),
    DESIGN_PROGRESS(1),
    PRINT_PENDING(2),
    PRINT_PROGRESS(3),
    PACKAGING_PENDING(4),
    PACKAGING_PROGRESS(5),
    READY_FOR_DELIVERY(6),
    DISPATCHED(7),
    DELIVERED(8);
    private final int internalId;

    OrderStatus(int id) {
        this.internalId = id;
    }

}
