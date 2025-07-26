package com.fbs.central_api.service;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.enums.UserType;
import com.fbs.central_api.exceptions.InvalidCredentials;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.utility.AuthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
this class is going to contain all user related logics
*/
@Service
public class UserService {

    DBApiConnector dbApiConnector;
    AuthUtility authUtility;

    @Autowired
    public UserService(DBApiConnector dbApiConnector,
                       AuthUtility authUtility){
        this.dbApiConnector = dbApiConnector;
        this.authUtility = authUtility;
    }

    /*
    work of this function is get all system admins from our users table
     */
    public List<AppUser> getAllSystemAdmins(){
        // to get all the system appadmin from the user table we need to call dbApiConnector
        return dbApiConnector.callGetAllUsersByUserType(UserType.SYSTEM_ADMIN.toString());
    }

    public AppUser updateUserDetails(AppUser user){
        // dbapi connector
        return dbApiConnector.callUpdateUserEndpoint(user);
    }

    /**
     * work of this method is to bring user by email
     * @param email
     * @return
     */
    public AppUser getUserByEmail(String email){
        // Internally this method will bring user on the basis of emial
        // For now lets check database api connector are we having any method which will bring user by email
        return dbApiConnector.callGetUserByEmailEndpoint(email);
    }

    // writing valid credentials method
    /*
    This function should be able to validate the credentials of the user.
    1. This function will first get the user record from the user table on the basis of email
    2. After getting the user this function will compare the password that password is correct or not.
    3. If correct we will return true else we will return false.
     */
    public String isValidCredentials(String email, String password){
        // 1. We need to develop one method which will bring user from the user table on the basis of email -> Done
        AppUser user = this.getUserByEmail(email);
        // 2. Validate do we got the user object as null ? Is Yes throw exception else Move forward (H.W)
        if(user.getPassword().equals(password)){
            // if password is correct i am going to return token
            // If incorrect I am going to return null value.
            return authUtility.generateToken(user.getEmail(), user.getPassword(), user.getUserType());
        }
        throw new InvalidCredentials("Email or password is wrong");
    }

    public boolean validateToken(String token){
        String payload = authUtility.decryptJwtToken(token);
        String [] details = payload.split(":");
        String email = details[0];
        String password = details[1];
        // I want to validate email & password is it belonging to correct user or not
        // Auth Utility is going to call UserService to validate email and password belongs to correct user or not
        try{
            isValidCredentials(email, password);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public AppUser getUserFromToken(String token){
        String payload = authUtility.decryptJwtToken(token);
        String email = payload.split(":")[0];
        return this.getUserByEmail(email);
    }
}
