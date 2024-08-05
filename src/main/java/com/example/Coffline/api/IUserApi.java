package com.example.Coffline.api;

import com.example.Coffline.request.UserRequest;
import com.example.Coffline.response.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface IUserApi {
    ResponseEntity<BaseResponse<Void>> register(UserRequest registerRequest);
}
