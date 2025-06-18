package com.meli.item_detail_backend.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private ApiError error;
    private LocalDateTime timestamp;

    public ApiResponse(T data){

        this.success = true;
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.error = null;
    }

    public ApiResponse(ApiError error){
        this.success = false;
        this.data = null;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }
}
