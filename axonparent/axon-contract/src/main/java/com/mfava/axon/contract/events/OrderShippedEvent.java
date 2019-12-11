package com.mfava.axon.contract.events;

import lombok.Data;

/**
 * @author michaelfava
 */
@Data
public class OrderShippedEvent {

    private final String orderId;
}
