package com.example.fanp.domain.local.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fanp.domain.local.repository.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> all();

    @Query("SELECT COUNT(*) from user")
    int count();

    @Insert
    void insert(User... users);
}
