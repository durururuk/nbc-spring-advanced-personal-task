package com.sparta.nbcspringadvancedpersonaltask.repository;

import com.sparta.nbcspringadvancedpersonaltask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
