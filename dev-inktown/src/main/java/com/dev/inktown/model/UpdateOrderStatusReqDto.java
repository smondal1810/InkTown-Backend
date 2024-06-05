package com.dev.inktown.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusReqDto {
    String userId;
    String orderId;
    OrderStatus status;
}
