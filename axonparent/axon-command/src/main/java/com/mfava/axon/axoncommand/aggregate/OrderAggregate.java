package com.mfava.axon.axoncommand.aggregate;

import com.mfava.axon.contract.command.ConfirmOrderCommand;
import com.mfava.axon.contract.command.PlaceOrderCommand;
import com.mfava.axon.contract.command.ShipOrderCommand;
import com.mfava.axon.contract.events.OrderConfirmedEvent;
import com.mfava.axon.contract.events.OrderPlacedEvent;
import com.mfava.axon.contract.events.OrderShippedEvent;
import com.mfava.axon.axoncommand.exception.UnconfirmedOrderException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * @author michaelfava
 */
@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderAggregate {


    @AggregateIdentifier
    private String orderId;

    private boolean orderConfirmed;




    @CommandHandler
    public OrderAggregate(PlaceOrderCommand command) {
        AggregateLifecycle.apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) throws UnconfirmedOrderException {
        if (!orderConfirmed) {
            throw new UnconfirmedOrderException();
        }
        AggregateLifecycle.apply(new OrderShippedEvent(orderId));
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        AggregateLifecycle.apply(new OrderConfirmedEvent(orderId));
    }


    @EventSourcingHandler
    public void on(OrderPlacedEvent event) {
        this.orderId = event.getOrderId();
        orderConfirmed = false;
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        orderConfirmed = true;
    }


}
