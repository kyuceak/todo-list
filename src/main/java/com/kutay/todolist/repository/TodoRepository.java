package com.kutay.todolist.repository;


import com.kutay.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByTitle(String title);

    @Modifying // required because the query is not a select
    @Query("UPDATE Todo t SET t.completed = :completed WHERE t.id = :id") // JPQL
    int updateCompleted(@Param("id") Long id , @Param("completed") boolean completed);

    @Query("SELECT t FROM Todo t WHERE t.completed = true")
    List<Todo> fetchAllCompleted();

    @Query("SELECT t FROM Todo t WHERE t.completed = false")
    List<Todo> fetchAllunCompleted();


}
