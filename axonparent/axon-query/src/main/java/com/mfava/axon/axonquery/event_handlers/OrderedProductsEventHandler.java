package com.mfava.axon.axonquery.event_handlers;

import com.mfava.axon.axonquery.model.OrderedProduct;
import com.mfava.axon.axonquery.query.FindOrderedProductsByOrderIdQuery;
import com.mfava.axon.contract.events.OrderPlacedEvent;
import com.mfava.axon.axonquery.query.FindAllOrderedProductsQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author michaelfava
 */
@Service
public class OrderedProductsEventHandler {

    private final Map<String, OrderedProduct> orderedProducts = new HashMap<>();

    @EventHandler
    public void on(OrderPlacedEvent event) {
        String orderId = event.getOrderId();
        orderedProducts.put(orderId, new OrderedProduct(orderId, event.getProduct()));
    }

    @QueryHandler
    public List<OrderedProduct> handle(FindAllOrderedProductsQuery query) {
        return new ArrayList<>(orderedProducts.values());
    }

    @QueryHandler
    public OrderedProduct handle(FindOrderedProductsByOrderIdQuery query) {
        return orderedProducts.get(query.getOrderId());
    }

}
