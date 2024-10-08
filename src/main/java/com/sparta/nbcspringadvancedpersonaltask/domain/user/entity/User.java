package com.sparta.nbcspringadvancedpersonaltask.domain.user.entity;

import com.sparta.nbcspringadvancedpersonaltask.domain.user.dto.UserRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.manager.entity.Timestamped;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.enums.UserRoleEnum;
import com.sparta.nbcspringadvancedpersonaltask.domain.manager.entity.UserTodo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends Timestamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String username;
    private String email;
    private String password;
    private UserRoleEnum role;

    public User(UserRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.role = requestDto.getRole();
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserTodo> UserTodoList = new ArrayList<>();




}

