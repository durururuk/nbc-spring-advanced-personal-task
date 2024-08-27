package com.sparta.nbcspringadvancedpersonaltask.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Table(name = "USER_TODO")
@Entity
@Setter
@NoArgsConstructor
public class UserTodo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UT_ID")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User user;

    @ManyToOne
    @JoinColumn(name = "TODO_ID")
    Todo todo;



}
