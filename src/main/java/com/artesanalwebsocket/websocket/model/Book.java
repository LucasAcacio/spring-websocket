package com.artesanalwebsocket.websocket.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash
public class Book implements Serializable {

    private String ev;
    private String feed;
    @Id
    private String symb;
    private Double bid_px;
    private Integer bid_qty;
    private String bid_datetime;
    private Double offer_px;
    private Integer offer_qty;
    private String offer_datetime;

    public Book(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BookJson bookJson = objectMapper.readValue(json, BookJson.class);
            this.symb = bookJson.getSymb();
            this.ev = bookJson.getEv();
            this.feed = bookJson.getFeed();
            if (!bookJson.getBidEntries().isEmpty()) {
                this.bid_qty = bookJson.getBidEntries().get(0).getQty();
                this.bid_px = bookJson.getBidEntries().get(0).getPx();
                this.bid_datetime = bookJson.getBidEntries().get(0).getDatetime();
            }
            if (!bookJson.getOfferEntries().isEmpty()) {
                this.offer_qty = bookJson.getOfferEntries().get(0).getQty();
                this.offer_px = bookJson.getOfferEntries().get(0).getPx();
                this.offer_datetime = bookJson.getOfferEntries().get(0).getDatetime();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
