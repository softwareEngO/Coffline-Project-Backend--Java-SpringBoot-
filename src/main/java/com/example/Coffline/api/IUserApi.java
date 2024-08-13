package com.example.Coffline.api;

import com.example.Coffline.request.LoginRequest;
import com.example.Coffline.request.RegisterRequest;
import com.example.Coffline.response.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface IUserApi {
    ResponseEntity<BaseResponse<Void>> register(RegisterRequest registerRequest);

    ResponseEntity<BaseResponse<Void>> login(LoginRequest loginRequest);
}
