package com.dev.inktown.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_update_log")
@Getter
@Setter
public class OrderUpdateLog {

    @Id
    @UuidGenerator
    @Column(unique = true, nullable = false)
    String id;
    String orderId;
    int currentOrderStatus;
    String updatedBy;
    @CreationTimestamp
    LocalDateTime createdAt;



}
