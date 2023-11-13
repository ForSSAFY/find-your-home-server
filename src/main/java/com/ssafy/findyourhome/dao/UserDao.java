package com.ssafy.findyourhome.dao;

import com.ssafy.findyourhome.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("SELECT * FROM `user` WHERE id = #{id}")
    User findById(int id);

    @Select("SELECT * FROM `user`")
    List<User> findAll();

    @Insert("INSERT INTO `user` (username, password) VALUES (#{username}, #{password})")
    void insert(User user);

    @Update("UPDATE `user` SET password = #{password} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM `user` WHERE id = #{id}")
    void delete(int id);
}
