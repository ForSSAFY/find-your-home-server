package com.ssafy.findyourhome.dao;

import com.ssafy.findyourhome.domain.Qna;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnaDao {

    @Select("SELECT * FROM qna WHERE id = #{id}")
    Qna findById(int id);

    @Select("SELECT * FROM qna")
    List<Qna> findAll();

    @Insert("INSERT INTO qna (user_id, title, content) VALUES (#{userId}, #{title}, #{content})")
    void insert(Qna qna);

    @Update("UPDATE qna SET title = #{title}, content = #{content} WHERE id = #{id}")
    void update(Qna qna);

    @Delete("DELETE FROM qna WHERE id = #{id}")
    void delete(int id);
}
