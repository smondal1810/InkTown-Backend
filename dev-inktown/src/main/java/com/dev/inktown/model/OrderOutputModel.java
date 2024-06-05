package com.dev.inktown.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderOutputModel {
    String orderId;
    String orderName;
    String orderDesc;
    Long squareFeet;
    int orderStatus;
    Boolean isUrgent;
    String createdBy;
    String assignedTo;
    LocalDateTime createdAt;
    LocalDateTime lastModifiedAt;
    String userNameOfEmp;
    String userNameOfCustomer;

}
