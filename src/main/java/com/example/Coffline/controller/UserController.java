package com.example.Coffline.controller;

import com.example.Coffline.api.IUserApi;
import com.example.Coffline.modal.UserModal;
import com.example.Coffline.request.UserRequest;
import com.example.Coffline.response.BaseResponse;
import com.example.Coffline.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController implements IUserApi {

    private UserService userService;

    private ModelMapper modelMapper;

    @PostMapping("/register")
    @Override
    public ResponseEntity<BaseResponse<Void>> register(@Valid @RequestBody UserRequest userRequest) {
        userService.registerUser(modelMapper.map(userRequest, UserModal.class));
        BaseResponse<Void> response = new BaseResponse<Void>(HttpStatus.OK.value(), "Kayıt Başarılı",System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
