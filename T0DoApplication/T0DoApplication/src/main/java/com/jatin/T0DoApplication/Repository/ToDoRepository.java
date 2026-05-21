package com.jatin.T0DoApplication.Repository;

import com.jatin.T0DoApplication.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo,Long> {
}
