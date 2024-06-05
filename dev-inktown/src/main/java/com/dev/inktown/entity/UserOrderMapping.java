package com.dev.inktown.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_order_mapping")
@Getter
@Setter
public class UserOrderMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String mappingId;

    @Column(nullable = false)
    String userId;
    @Column(nullable = false)
    String orderId;
}
