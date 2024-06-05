package com.dev.inktown.controller;

import com.dev.inktown.entity.Order;
import com.dev.inktown.entity.OrderUpdateLog;
import com.dev.inktown.model.DisplayStatusResp;
import com.dev.inktown.model.NewOrderRequestDto;
import com.dev.inktown.model.OrderOutputModel;
import com.dev.inktown.model.UpdateOrderStatusReqDto;
import com.dev.inktown.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Running");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderOutputModel>> getAllOrder() {
        List<OrderOutputModel> orderList = orderService.getAllOrder();
        return ResponseEntity.ok(orderList);
    }

    @GetMapping(value = "/getById/{orderId}")
    public Order getOrderById(@PathVariable("orderId") String orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody NewOrderRequestDto reqDto) {
        log.info(String.valueOf(reqDto));
        Order createdOrder = orderService.createOrder(reqDto);
        return ResponseEntity.ok(createdOrder);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<Order> updateOrderStatus(@RequestBody UpdateOrderStatusReqDto reqDto) {
        log.info("prev123" + reqDto.getStatus());
        return ResponseEntity.ok(orderService.updateOrderStatus(reqDto));
    }

    @GetMapping("/getOrdersByStatus")
    public ResponseEntity<List<OrderOutputModel>> getOrdersByStatus(@RequestParam(name = "orderStatus") Integer orderStatus) {
        List<OrderOutputModel> savedOrder = orderService.getOrdersByStatus(orderStatus);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/getDisplayStatusList")
    public List<DisplayStatusResp> getDisplayStatusList() {
        return orderService.getDisplayStatusList();
    }

    @GetMapping("/getOrderTimeDetail/{orderId}")
    public ResponseEntity<String> getOrderTimeDetail(@PathVariable String orderId){
        return ResponseEntity.ok(orderService.getOrderTimeDetail(orderId));

    }
    @GetMapping("/getOrderLog/{orderId}")
    public ResponseEntity<List<OrderUpdateLog>> getOrderLog(@PathVariable String orderId){
        return ResponseEntity.ok(orderService.getOrderLog(orderId));
    }

    // write a order controller name /getOrdersByStatusAndUserId like this
     @GetMapping("/getOrdersByStatusAndUserId")
     public ResponseEntity<List<OrderOutputModel>> getOrdersByStatusAndUserId(@RequestParam(name = "orderStatus") Integer orderStatus, @RequestParam(name = "userId") String userId) {
         List<OrderOutputModel> savedOrder = orderService.getOrdersByStatusAndUserId(orderStatus, userId);
         return ResponseEntity.ok(savedOrder);
     }


}
