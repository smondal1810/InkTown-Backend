package com.dev.inktown.mapper;

import com.dev.inktown.entity.Customer;
import com.dev.inktown.entity.Order;
import com.dev.inktown.entity.OrderUpdateLog;
import com.dev.inktown.model.NewOrderRequestDto;
import com.dev.inktown.model.OrderOutputModel;


public class ObjectMapper {

    private ObjectMapper() {

    }

    public static Customer CustomerMapperFromNewOrderRequestDto(NewOrderRequestDto requestDto) {
        Customer cust = new Customer();
        cust.setCustomerName(requestDto.getCustomerName());
        cust.setPhoneNo(requestDto.getCustomerPhoneNo());
        cust.setUniqueUserId(requestDto.getUniqueUserId());
        if (requestDto.getCustomerEmail() != null) {
            cust.setCustomerEmail(requestDto.getCustomerEmail());
        }
        return cust;
    }

    public static Order orderMapperFromNewOrderRequestDto(NewOrderRequestDto reqDto) {
        Order order = new Order();
        order.setSquareFeet(reqDto.getSquareFeet());
//          order.setOrderStatus(reqDto.getOrderStatus());
        order.setOrderStatus(0); //DESIGN_PENDING
        if (reqDto.getOrderDesc() != null) {
            order.setOrderDesc(reqDto.getOrderDesc());
        }
        order.setLength(reqDto.getLength());
        order.setWidth(reqDto.getWidth());
        order.setIsEyelet(reqDto.getIsEyelet());
        order.setQuality(reqDto.getQuality());
        order.setQuantity(reqDto.getQuantity());
        order.setOrderName(reqDto.getOrderName());
        order.setIsUrgent(reqDto.getIsUrgent());
        return order;
    }

    public static OrderUpdateLog orderUpdateLogFromOrder(Order order) {
        OrderUpdateLog orderUpdateLog = new OrderUpdateLog();
        orderUpdateLog.setOrderId((order.getOrderId()));
        orderUpdateLog.setUpdatedBy(order.getAssignedTo());
        orderUpdateLog.setCurrentOrderStatus(order.getOrderStatus());
        return orderUpdateLog;
    }

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
}
