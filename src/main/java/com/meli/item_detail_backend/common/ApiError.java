package com.meli.item_detail_backend.common;

import lombok.Data;

@Data
public class ApiError {

    private int code;
    private String message;

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
