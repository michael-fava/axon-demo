package com.mfava.axon.contract.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author michaelfava
 */
@Data
public class ShipOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
}
