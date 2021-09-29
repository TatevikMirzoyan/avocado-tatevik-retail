package com.avocado.tatevik.retail.service.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageResponseWrapper<T> {

    private Long total;

    private Integer totalPages;

    private List<T> data;
}
