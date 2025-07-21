package com.fbs.db_api.dto;

import com.fbs.db_api.models.AppUser;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllUsersDto {
    List<AppUser> appUsers;
}
