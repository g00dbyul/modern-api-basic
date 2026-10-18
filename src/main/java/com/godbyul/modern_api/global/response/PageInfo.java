package com.godbyul.modern_api.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageInfo {

    private int number;
    private int size;
    private long totalElements;
    private int totalPages;
}
