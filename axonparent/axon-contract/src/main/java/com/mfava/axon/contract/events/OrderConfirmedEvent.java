package com.mfava.axon.contract.events;

import lombok.Data;

/**
 * @author michaelfava
 */
@Data
public class OrderConfirmedEvent {

    private final String orderId;
}
