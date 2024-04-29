package com.artesanalwebsocket.websocket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BookJson implements Serializable {
    private String ev;
    private String feed;
    @Id
    @Indexed
    private String symb;

    @JsonProperty("bid")
    private List<BidOfferEntry> bidEntries;

    @JsonProperty("offer")
    private List<BidOfferEntry> offerEntries;
}
