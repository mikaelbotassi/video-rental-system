package com.locadoar.backend.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestDto implements Serializable {
    private int page;
    private int size;
}
