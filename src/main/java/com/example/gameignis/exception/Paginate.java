package com.example.gameignis.exception;

import lombok.Data;
import lombok.ToString;

/**
 * Created by mugilarasan on 12/06/2025.
 */
@Data
@ToString
public class Paginate {

    private Boolean isList;
    private Integer pageSize;
    private Integer pageNo;
    private Integer totalPages;
    private Integer totalElements;
}