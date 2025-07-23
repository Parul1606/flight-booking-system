package com.fbs.central_api.service;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.enums.UserType;
import com.fbs.central_api.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
this class is going to contain all user related logics
*/
@Service
public class UserService {

    DBApiConnector dbApiConnector;

    @Autowired
    public UserService(DBApiConnector dbApiConnector){
        this.dbApiConnector = dbApiConnector;
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
}
