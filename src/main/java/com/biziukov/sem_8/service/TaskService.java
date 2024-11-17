package com.biziukov.sem_8.service;

import jakarta.transaction.Transactional;
import com.biziukov.sem_8.aspect.TrackUserAction;
import com.biziukov.sem_8.domain.Task;
import com.biziukov.sem_8.domain.TaskStatus;
import com.biziukov.sem_8.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    @TrackUserAction
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @TrackUserAction
    public Task addTask(Task task) {
        return repository.save(task);
    }

    @TrackUserAction
    @Transactional
    public Task updateTaskStatus(Long id, Task newTask) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(newTask.getStatus());
            return repository.save(task);
        } else {
            throw new IllegalArgumentException("Task with id: " + id + " not found.");
        }
    }

    @TrackUserAction
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    @TrackUserAction
    public List<Task> getTasksByStatus(TaskStatus status) {
        return repository.findAllByStatus(status);
    }


}