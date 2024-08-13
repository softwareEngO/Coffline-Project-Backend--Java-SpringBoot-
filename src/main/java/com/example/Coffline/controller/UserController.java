package com.example.Coffline.controller;

import com.example.Coffline.api.IUserApi;
import com.example.Coffline.modal.LoginModal;
import com.example.Coffline.modal.RegisterModal;
import com.example.Coffline.request.LoginRequest;
import com.example.Coffline.request.RegisterRequest;
import com.example.Coffline.response.BaseResponse;
import com.example.Coffline.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController implements IUserApi {

    private UserService userService;

    private ModelMapper modelMapper;

    private final MessageSource messageSource;
    @PostMapping("/register")
    @Override
    public ResponseEntity<BaseResponse<Void>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        userService.registerUser(modelMapper.map(registerRequest, RegisterModal.class));
        BaseResponse<Void> response = new BaseResponse<Void>(HttpStatus.OK.value(), messageSource.getMessage("coffline.message.registeredSuccessfully", null, LocaleContextHolder.getLocale()),System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<BaseResponse<Void>> login(@Valid @RequestBody LoginRequest loginRequest) {
        userService.login(modelMapper.map(loginRequest, LoginModal.class));
        BaseResponse<Void> response = new BaseResponse<Void>(HttpStatus.OK.value(), messageSource.getMessage("coffline.message.loginSuccessfull", null, LocaleContextHolder.getLocale()),System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
