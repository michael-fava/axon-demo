package com.mfava.axon.contract.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author michaelfava
 */
@Data
public class PlaceOrderCommand {


    @TargetAggregateIdentifier
    private final String orderId;

    private final String product;
}
