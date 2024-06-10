package com.juanje.ecommerce.backend.domain.model;

import lombok.Data;

//esta clase contiene información sobre el pago
@Data
public class DataPayment {
    private String method;
    private String amount;
    private String currency;
    private String description;
}
