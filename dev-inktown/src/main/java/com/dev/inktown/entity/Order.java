package com.dev.inktown.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @UuidGenerator
    @Column(unique = true, nullable = false)
    String orderId;

    @Column(nullable = false)
    String orderName;

    @Column(columnDefinition = "Text")
    String orderDesc;

    @Column(nullable = false)
    Long squareFeet;

    @Column(nullable = false)
    Long length;

    @Column(nullable = false)
    Long width;

    @Column
    Boolean isEyelet;

    @Column(nullable = false)
    Long quantity;

    @Column(nullable = false)
    int quality;

    @Column(nullable = false)
    int orderStatus;

    @Column
    Boolean isUrgent;

    @Column(nullable = false)
    String createdBy;

    @Column
    String assignedTo;

    @CreationTimestamp
    LocalDateTime createdAt;


    @UpdateTimestamp
    LocalDateTime lastModifiedAt;

}
