package com.dev.inktown.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewOrderRequestDto {
    private String customerName;
    private String customerPhoneNo;
    private String customerEmail;
    private String orderDesc;
    private Long squareFeet;
//    private OrderStatus orderStatus;
    private String orderName;
    private Boolean isUrgent;
    private String uniqueUserId;
    private Long length;
    private Long width;
    private Boolean isEyelet;
    private Long quantity;
    private Integer quality;
}
