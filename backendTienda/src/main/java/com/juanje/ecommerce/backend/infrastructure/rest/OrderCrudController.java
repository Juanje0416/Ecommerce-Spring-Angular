package com.juanje.ecommerce.backend.infrastructure.rest;

import com.juanje.ecommerce.backend.application.OrderService;
import com.juanje.ecommerce.backend.domain.model.Order;
import com.juanje.ecommerce.backend.domain.model.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/ordercrud")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class OrderCrudController {

    private final OrderService orderService;

    public OrderCrudController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
        public ResponseEntity<Order> save(@RequestBody Order order){
            System.out.println(order.getOrderState());
            if (order.getOrderState().toString().equals(OrderState.CANCELLED.toString()) ){
                order.setOrderState(OrderState.CANCELLED);
            }else{
                order.setOrderState(OrderState.CONFIRMED);
            }
            //con este if lo que hacemos es validar en el caso de que me venga cancelado le enviamos cancelado a la orden para que lo guarde en mi BBDD
            return ResponseEntity.ok(orderService.save(order));
        }

        @PostMapping("/update/state/order")
        public ResponseEntity updateStateById(@RequestParam Integer id, @RequestParam String state){
            orderService.updateStateById(id, state);
            return ResponseEntity.ok().build();
        }

        @GetMapping
        public ResponseEntity<Iterable<Order>> findAll(){
            return ResponseEntity.ok(orderService.findAll());
        }

        @GetMapping("{variable}")
        public ResponseEntity<Order> findById(@PathVariable("variable") Integer id){
            return  ResponseEntity.ok(orderService.findById(id));
        }

        @GetMapping("/by-user/{id}")
        public ResponseEntity<Iterable<Order>> findByUserId(@PathVariable("id") Integer userId){
            return ResponseEntity.ok(orderService.findByUserId(userId));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id){
            orderService.deleteById(id);
            return ResponseEntity.ok().build();
        }

}

