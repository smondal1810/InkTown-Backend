package com.dev.inktown.service;

import com.dev.inktown.entity.OrderUpdateLog;
import com.dev.inktown.repository.OrderUpdateLogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderUpdateLogService {


    private final OrderUpdateLogRepository orderUpdateLogRepository;

    public OrderUpdateLogService(OrderUpdateLogRepository orderUpdateLogRepository) {
        this.orderUpdateLogRepository = orderUpdateLogRepository;
    }


    @Transactional
    public void saveOrderLog(OrderUpdateLog logObject){
        orderUpdateLogRepository.save(logObject);
    }
    public List<OrderUpdateLog> getOrderUpdateLogListForUser(String userId){
        return orderUpdateLogRepository.findByUpdatedBy(userId);
    }

    public List<OrderUpdateLog> getOrderUpdateLogListForOrder(String orderId){
        return orderUpdateLogRepository.findByOrderIdOrderByCreatedAtDesc(orderId);
    }
}
