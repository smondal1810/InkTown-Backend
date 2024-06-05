package com.dev.inktown.repository;

import com.dev.inktown.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByAssignedTo(String userId);
    List<Order> findByCreatedBy(String userId);

    List<Order> findAllByOrderStatus(int orderStatus);

    List<Order> findAllByOrderStatusAndCreatedBy(Integer orderStatus, String userId);
}
