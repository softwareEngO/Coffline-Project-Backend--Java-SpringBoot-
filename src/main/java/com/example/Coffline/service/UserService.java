package com.example.Coffline.service;

import com.example.Coffline.entity.User;
import com.example.Coffline.exception.InactiveUserException;
import com.example.Coffline.exception.UserExistException;
import com.example.Coffline.exception.UserNotFoundException;
import com.example.Coffline.modal.LoginModal;
import com.example.Coffline.modal.RegisterModal;
import com.example.Coffline.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private final MessageSource messageSource;

    @Transactional
    public void registerUser(RegisterModal registerModal) {

        Optional<User> user = userRepository.findByEmail(registerModal.getEmail());
        if (user.isPresent()) {
            throw new UserExistException(messageSource.getMessage("coffline.error.userExist", null, LocaleContextHolder.getLocale()));
        }

        registerModal.setIsActive(false);
        userRepository.save(modelMapper.map(registerModal, User.class));
    }


    public void login(LoginModal loginModal){
        User user = userRepository.findByEmail(loginModal.getEmail())
                .orElseThrow(() -> new UserNotFoundException(messageSource.getMessage("coffline.error.userNotFound", null, LocaleContextHolder.getLocale())));

        if (!user.isActive()) {
            throw new InactiveUserException(messageSource.getMessage("coffline.error.userIsNotActive", null, LocaleContextHolder.getLocale()));
        }

        //generateToken burada

    }
}
