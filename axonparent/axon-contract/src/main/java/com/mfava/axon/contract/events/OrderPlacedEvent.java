package com.mfava.axon.contract.events;

import lombok.Data;

/**
 * @author michaelfava
 */
@Data
public class OrderPlacedEvent {

    private final String orderId;

    private final String product;

}
