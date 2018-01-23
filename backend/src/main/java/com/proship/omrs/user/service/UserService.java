package com.proship.omrs.user.service;


import com.proship.omrs.role.entity.Role;
import com.proship.omrs.role.entity.UserRole;
import com.proship.omrs.role.repository.UserRoleRepository;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.entity.VerificationToken;
import com.proship.omrs.user.repository.TokenRepository;
import com.proship.omrs.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public User registerNewUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordHash(user.getPassword());
        user.setPasswordSalt(user.getPassword().substring(0,40));
        user.setUuid(UUID.randomUUID().getLeastSignificantBits());
        user.setRequisitionMailRecipient(true);
        user.setActive(false);
        user.setIncentiveBracketSettingId((long)2);

//        List<Role> roleList = user.getRoleList();

        User newUser = userRepository.save(user);

        user.setId(newUser.getId());

        user.updateUserRoleListByRoleList();

        userRoleRepository.save(user.getUserRoleList());
        return newUser;
    }

    public VerificationToken createVerificationToken(User user, String token){
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setExpiryDate();
        verificationToken.setToken(token);
        return tokenRepository.save(verificationToken);

    }

    public VerificationToken findToken(String token){
        return tokenRepository.findByToken(token);
    }
    public void saveRegisteredUser(User user){
        userRepository.save(user);
    };
}
