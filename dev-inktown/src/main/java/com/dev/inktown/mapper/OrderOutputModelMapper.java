package com.dev.inktown.mapper;

import com.dev.inktown.entity.Order;
import com.dev.inktown.model.DisplayStatusResp;
import com.dev.inktown.model.OrderOutputModel;
import com.dev.inktown.model.OrderStatus;

public class OrderOutputModelMapper {

    private OrderOutputModelMapper(){}

    public static OrderOutputModel orderToOrderOutputModelMapper(Order order) {
        return OrderOutputModel.builder()
                .orderId(order.getOrderId())
                .orderName(order.getOrderName())
                .orderDesc(order.getOrderDesc())
                .orderStatus(order.getOrderStatus())
                .assignedTo(order.getAssignedTo())
                .squareFeet(order.getSquareFeet())
                .createdAt(order.getCreatedAt())
                .isUrgent(order.getIsUrgent())
                .lastModifiedAt(order.getLastModifiedAt())
                .createdBy(order.getCreatedBy())
                .build();
    }

    public static OrderStatus findOrderStatus(int orderStatusCode) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.getInternalId() == orderStatusCode) {
                return orderStatus;
            }
        }
        return null;

    }
}
