package com.artesanalwebsocket.websocket.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash
public class Book implements Serializable {

    private String ev;
    private String feed;
    @Id
    @Indexed
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
            Book book = Book.builder()
                    .symb(bookJson.getSymb())
                    .ev(bookJson.getEv())
                    .feed(bookJson.getFeed())
                    .bid_qty((!bookJson.getBidEntries().isEmpty()) ? bookJson.getBidEntries().get(0).getQty() : null)
                    .bid_px((!bookJson.getBidEntries().isEmpty()) ? bookJson.getBidEntries().get(0).getPx() : null)
                    .bid_datetime((!bookJson.getBidEntries().isEmpty()) ? bookJson.getBidEntries().get(0).getDatetime() : null)
                    .offer_qty((!bookJson.getOfferEntries().isEmpty()) ? bookJson.getOfferEntries().get(0).getQty() : null)
                    .offer_px((!bookJson.getOfferEntries().isEmpty()) ? bookJson.getOfferEntries().get(0).getPx() : null)
                    .offer_datetime((!bookJson.getOfferEntries().isEmpty()) ? bookJson.getOfferEntries().get(0).getDatetime() : null)
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
