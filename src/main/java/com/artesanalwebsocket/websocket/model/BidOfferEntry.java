package com.artesanalwebsocket.websocket.model;

import lombok.Data;

@Data
public class BidOfferEntry {
    private Double px;
    private Integer qty;
    private String datetime;
}
