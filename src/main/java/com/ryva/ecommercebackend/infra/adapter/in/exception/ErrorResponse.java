package com.ryva.ecommercebackend.infra.adapter.in.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private Integer statusCode;
}