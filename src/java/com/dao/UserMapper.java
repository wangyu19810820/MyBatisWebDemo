package com.dao;

import com.model.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();
}
