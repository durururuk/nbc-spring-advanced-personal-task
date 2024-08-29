package com.sparta.nbcspringadvancedpersonaltask.domain.manager.entity;

import com.sparta.nbcspringadvancedpersonaltask.domain.todo.entity.Todo;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "USER_TODO")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserTodo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UTId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TODO_ID")
    Todo todo;
}
