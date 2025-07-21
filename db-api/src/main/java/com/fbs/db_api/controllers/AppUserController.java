package com.fbs.db_api.controllers;

import com.fbs.db_api.dto.AllUsersDto;
import com.fbs.db_api.models.AppUser;
import com.fbs.db_api.repositories.AppUserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * As this is simple crud endpoint controller and in db api we dont write any logics,
 * So, we don't require any service class we will be directly calling repository layer.    *
*/
@RestController
@RequestMapping("/api/v1/db/user")
public class AppUserController {

    AppUserRepo appUserRepo;

    public AppUserController(AppUserRepo appUserRepo){
        this.appUserRepo = appUserRepo;
    }

    @PostMapping("/save")
    public ResponseEntity createUser(@RequestBody AppUser user){
        AppUser userResp = appUserRepo.save(user);
        return new ResponseEntity(userResp, HttpStatus.CREATED);
    }

    @GetMapping("/get/{userType}")
    public ResponseEntity getAllUsersByUsersType(@PathVariable String userType){
        List<AppUser> users = appUserRepo.findByUserType(userType);
        AllUsersDto allUsersDto = new AllUsersDto();
        allUsersDto.setAppUsers(users);
        return new ResponseEntity(allUsersDto, HttpStatus.OK);
    }
}
