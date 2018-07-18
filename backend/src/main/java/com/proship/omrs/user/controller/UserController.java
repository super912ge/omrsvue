package com.proship.omrs.user.controller;

import com.google.common.collect.Lists;
import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.user.entity.VerificationToken;
import com.proship.omrs.user.param.UserBrief;
import com.proship.omrs.user.repository.UserBriefRepository;
import com.proship.omrs.user.repository.UserRepository;
import com.proship.omrs.utils.event.OnRegistrationCompleteEvent;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping(value="/user/")
@CrossOrigin
public class UserController extends BaseController<User,Long>{

    @Autowired
    public UserController(UserRepository repo) {
        super(repo);
    }

    @Autowired
    private UserService userService;


    @Autowired private UserBriefRepository userBriefRepository;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value="/register", method= RequestMethod.POST,consumes = {"application/json"})
    public ResponseEntity<User> register (@RequestBody User user , WebRequest request){

        Logger logger = LoggerFactory.getLogger(this.getClass());

        user = userService.registerNewUser(user);

        String appUrl = request.getContextPath();

        try {
            eventPublisher.publishEvent( new OnRegistrationCompleteEvent(user,request.getLocale(),appUrl));
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @RequestMapping(value = "/registrationConfirm",method = RequestMethod.GET)
    public ResponseEntity<String> confirmRegistration (WebRequest request, @RequestParam("token")String token){
        //Locale locale = request.getLocale();
        VerificationToken verificationToken = userService.findToken(token);
        if(verificationToken==null){
            String message = "Bad token";
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
        if((verificationToken.getExpiryDate().getTime()- Calendar.getInstance().getTime().getTime())<=0){
            String message = "Token already expired. ";
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
        User user = verificationToken.getUser();

        user.setActive(true);

        userService.saveRegisteredUser(user);

        return new ResponseEntity<>("Activation succeeded.",HttpStatus.OK);

    }

    @RequestMapping(value = "all",method = RequestMethod.GET)
    public ResponseEntity<List<UserBrief>> listAllUsers() {
        Iterable<UserBrief> all = userBriefRepository.findAll();
        return new ResponseEntity<>(Lists.newArrayList(all),HttpStatus.OK);
    }

}
