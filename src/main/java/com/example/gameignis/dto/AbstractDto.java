package com.example.gameignis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AbstractDto {

    private Instant createdDate;

    private Instant changedDate;

    private String creator;

    private String changer;

    private String channel;
}


