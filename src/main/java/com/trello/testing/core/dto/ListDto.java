package com.trello.testing.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("idBoard")
    private String idBoard;

    @JsonProperty("name")
    private String name;
}
