package com.dev.inktown.service;

import com.dev.inktown.constant.StringConstant;
import com.dev.inktown.entity.Customer;
import com.dev.inktown.entity.Order;
import com.dev.inktown.entity.OrderUpdateLog;
import com.dev.inktown.mapper.ObjectMapper;
import com.dev.inktown.mapper.OrderOutputModelMapper;
import com.dev.inktown.model.*;
import com.dev.inktown.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class OrderService implements StringConstant {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerService customerService;

    @Autowired
    OrderUpdateLogService orderUpdateLogService;

    public Order getOrderById(String orderId) {
        Optional<Order> result = orderRepository.findById(orderId);
        return result.orElseGet(Order::new);
    }

    @Transactional
    public Order createOrder(NewOrderRequestDto orderRequestDto) {
        Order newOrder = ObjectMapper.orderMapperFromNewOrderRequestDto(orderRequestDto);
        newOrder.setAssignedTo(DEFAULT_ASSIGNEE);
        Customer customer = ObjectMapper.CustomerMapperFromNewOrderRequestDto(orderRequestDto);
        //call for save customer
        Customer savedCustomer = customerService.createCustomer(customer);
        newOrder.setCreatedBy(savedCustomer.getUniqueUserId());
        OrderUpdateLog orderUpdateLog = new OrderUpdateLog();
        //saving in order table
        var savedOrder = orderRepository.save(newOrder);
        //saving in updateLogtable
        orderUpdateLog.setOrderId(savedOrder.getOrderId());
        orderUpdateLog.setCurrentOrderStatus(savedOrder.getOrderStatus());
        orderUpdateLog.setUpdatedBy(DEFAULT_ASSIGNEE);
        orderUpdateLogService.saveOrderLog(orderUpdateLog);
        return savedOrder;
    }

    @Transactional
    public Order updateOrderStatus(UpdateOrderStatusReqDto updateOrderStatusReqDto) {
        Optional<Order> prevSavedOrder = orderRepository.findById(updateOrderStatusReqDto.getOrderId());
        if (prevSavedOrder.isPresent()) {
            //Order update
            Order order = prevSavedOrder.get();
            order.setOrderStatus(updateOrderStatusReqDto.getStatus().getInternalId());
            order.setAssignedTo(updateOrderStatusReqDto.getUserId());
            Order currSavedOrder = orderRepository.save(order);
            //Order update log
            OrderUpdateLog orderUpdateLog = ObjectMapper.orderUpdateLogFromOrder(currSavedOrder);
            orderUpdateLogService.saveOrderLog(orderUpdateLog);

            return currSavedOrder;

        }
        return new Order();
    }

    public List<DisplayStatusResp> getDisplayStatusList() {
        List<DisplayStatusResp> res = new ArrayList<>();
        for (OrderStatus os : OrderStatus.values()) {
            DisplayStatusResp obj = new DisplayStatusResp();
            obj.setOrderStatus(os.getInternalId());
            switch (os) {
                case DESIGN_PENDING -> obj.setDisplayValue("In Design queue");
                case DESIGN_PROGRESS -> obj.setDisplayValue("Design is in progress");
                case PRINT_PENDING -> obj.setDisplayValue("In Printing queue");
                case PRINT_PROGRESS -> obj.setDisplayValue("Printing in progress");
                case PACKAGING_PENDING -> obj.setDisplayValue("In Packaging queue");
                case PACKAGING_PROGRESS -> obj.setDisplayValue("Packaging in progress");
                case READY_FOR_DELIVERY -> obj.setDisplayValue("Waiting for delivery");
                case DISPATCHED -> obj.setDisplayValue("Delivery in progress");
                case DELIVERED -> obj.setDisplayValue("Delivery completed");
            }
            res.add(obj);
        }
        return res;
    }
    public List<OrderOutputModel> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().map(ObjectMapper::orderToOrderOutputModelMapper).toList();

    }

    public List<OrderOutputModel> getOrdersByStatus(Integer orderStatus) {
//        if(orderStatus == -1){
//            List<Order> allOrder = orderRepository.findAll();
//            return allOrder.stream().map(OrderOutputModelMapper::orderToOrderOutputModelMapper).toList();
//        }
        List<Order> savedOrder = orderRepository.findAllByOrderStatus(orderStatus);
        return savedOrder.stream().map(OrderOutputModelMapper::orderToOrderOutputModelMapper).toList();
    }
    public List<OrderUpdateLog> getOrderLog(String orderId){
        return orderUpdateLogService.getOrderUpdateLogListForOrder(orderId);
    }

    public String getOrderTimeDetail(String orderId){
        Order orderFromDb = getOrderById(orderId);
        LocalDateTime createdTime = orderFromDb.getCreatedAt();
        LocalDateTime lastModifiedtime = orderFromDb.getLastModifiedAt();
        LocalDateTime currTime = LocalDateTime.now();
        Duration elapsedTimeFromStart = Duration.between(createdTime,currTime);
        Duration elapsedTimeFromLastUpdate = Duration.between(lastModifiedtime,currTime);
        Map<String,String> res = new HashMap<>();
        res.put("orderId",orderId);
        res.put("timeElapsedFromStart",Long.toString(elapsedTimeFromStart.toMinutes()));
        res.put("timeElapsedFromLastUpdate",Long.toString(elapsedTimeFromLastUpdate.toMinutes()));
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(res);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public List<OrderOutputModel> getOrderListForEmployee(String userId){
        List<Order> orderList = orderRepository.findByAssignedTo(userId);
        return orderList.stream().map(ObjectMapper::orderToOrderOutputModelMapper).toList();
    }
    public List<OrderOutputModel> getOrderListForCust(String userId){
        List<Order> orderList = orderRepository.findByCreatedBy(userId);
        return orderList.stream().map(ObjectMapper::orderToOrderOutputModelMapper).toList();
    }

    public List<OrderOutputModel> getOrdersByStatusAndUserId(Integer orderStatus, String userId) {
        List<Order> savedOrder = orderRepository.findAllByOrderStatusAndCreatedBy(orderStatus,userId);
        return savedOrder.stream().map(OrderOutputModelMapper::orderToOrderOutputModelMapper).toList();
    }
}
