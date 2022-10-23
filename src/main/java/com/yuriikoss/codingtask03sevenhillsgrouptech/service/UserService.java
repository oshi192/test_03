package com.yuriikoss.codingtask03sevenhillsgrouptech.service;

import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.User;
import com.yuriikoss.codingtask03sevenhillsgrouptech.exeption.UserNotFoundException;
import com.yuriikoss.codingtask03sevenhillsgrouptech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long findBonusAmount(Long userId) {
        return userRepository.getBonusByUserId(userId);
    }

    public void updateBonus(User user, int bonus) {
        user.setBonusAmount(user.getBonusAmount() + bonus);
        userRepository.save(user);
    }

    public User findUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void removeUser(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException exception) {
            throw new UserNotFoundException(userId);
        }
    }
}
