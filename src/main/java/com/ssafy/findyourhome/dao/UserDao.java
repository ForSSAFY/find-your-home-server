package com.ssafy.findyourhome.dao;

import com.ssafy.findyourhome.domain.User;
import com.ssafy.findyourhome.dto.user.LoginRes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    @Select("SELECT * FROM `user` WHERE id = #{id}")
    User findById(int id);

    @Select("SELECT * FROM `user` WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT username, nickname FROM `user` WHERE username = #{username}")
    LoginRes findLoginResByUsername(String username);

    @Select("SELECT * FROM `user`")
    List<User> findAll();

    @Insert("INSERT INTO `user` (username, password, nickname, email) VALUES (#{username}, #{password}, #{nickname}, #{email})")
    void insert(User user);

    @Update("UPDATE `user` SET password = #{password} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM `user` WHERE id = #{id}")
    void delete(int id);
}
