package com.example.Coffline.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class BaseResponse<T> {

    @NonNull
    private Integer statusCode;

    @NonNull
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @NonNull
    private Long timestamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;
}
