package com.example.Coffline.service;

import com.example.Coffline.entity.User;
import com.example.Coffline.exception.UserExistException;
import com.example.Coffline.modal.UserModal;
import com.example.Coffline.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Transactional
    public void registerUser(UserModal userModal) {

        Optional<User> user = userRepository.findByEmail(userModal.getEmail());
        if (user.isPresent()) {
            throw new UserExistException("Aynı e-posta ile sisteme kayıtlı kullanıcı bulunmaktadır.");
        }

        userModal.setIsActive(false);
        userRepository.save(modelMapper.map(userModal, User.class));
    }
}
