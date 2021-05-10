package com.doctor.backend.dto;

import com.doctor.backend.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private String email;

    private String password;

    private String role;


    public static User toEntity(UserDto userDto) {
        if (null == userDto)
            return null;

        return new User(null, userDto.getEmail(), userDto.getPassword(), userDto.getRole());
    }

    public static UserDto fromEntity(User user) {
        if (null == user)
            return null;

        return new UserDto(user.getEmail(), user.getPassword(), user.getRole());
    }


}
