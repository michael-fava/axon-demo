package com.mfava.axon.axonapi.controller;

import com.mfava.axon.axonquery.model.OrderedProduct;
import com.mfava.axon.axonquery.query.FindOrderedProductsByOrderIdQuery;
import com.mfava.axon.contract.command.ConfirmOrderCommand;
import com.mfava.axon.contract.command.PlaceOrderCommand;
import com.mfava.axon.contract.command.ShipOrderCommand;
import com.mfava.axon.axonquery.query.FindAllOrderedProductsQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author michaelfava
 */
@RestController
public class OrderController {

    private final CommandGateway commandGateway;

    private final QueryGateway queryGateway;

    public OrderController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }


    @PostMapping("/ship-order")
    public void shipOrder() {
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, "Deluxe Chair"));
        commandGateway.send(new ConfirmOrderCommand(orderId));
        commandGateway.send(new ShipOrderCommand(orderId));
    }


    @GetMapping("/all-orders")
    public List<OrderedProduct> findAllOrderedProducts() {
        return queryGateway.query(new FindAllOrderedProductsQuery(),
                ResponseTypes.multipleInstancesOf(OrderedProduct.class)).join();
    }

    @GetMapping("/all-orders/{id}")
    public OrderedProduct findAllOrderedProducts(@PathVariable String id) {
        return queryGateway.query(new FindOrderedProductsByOrderIdQuery(id),
                ResponseTypes.instanceOf(OrderedProduct.class)).join();
    }

}
