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
@Table(name = "customers")
@Getter
@Setter
public class Customer {

    @Id
    @UuidGenerator
    String customerId;

    @Column(nullable = false, unique = true)
    String uniqueUserId;

    @Column(nullable = false)
    String customerName;

    @Column()
    String customerEmail;

    @Column(nullable = false, unique = true)
    String phoneNo;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime lastModifiedAt;
}
