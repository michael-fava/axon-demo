package com.mfava.axon.axonquery.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author michaelfava
 */
@Data
@AllArgsConstructor
public class FindOrderedProductsByOrderIdQuery {

    private String orderId;

}
