package com.valleon.applyforme.model.response;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
public class ApplyForMeResponse {
    private Collection<?> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
