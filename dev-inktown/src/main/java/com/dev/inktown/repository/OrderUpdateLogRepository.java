package com.dev.inktown.repository;

import com.dev.inktown.entity.OrderUpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderUpdateLogRepository extends JpaRepository<OrderUpdateLog,String>{
    List<OrderUpdateLog> findByUpdatedBy(String userId);
    List<OrderUpdateLog> findByOrderIdOrderByCreatedAtDesc(String orderId);

}
