package com.biziukov.sem_8.repository;

import com.biziukov.sem_8.domain.Task;
import com.biziukov.sem_8.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByStatus(TaskStatus status);
}