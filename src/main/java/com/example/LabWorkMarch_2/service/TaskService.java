package com.example.LabWorkMarch_2.service;

import com.example.LabWorkMarch_2.dao.TaskDao;
import com.example.LabWorkMarch_2.dao.UserDao;
import com.example.LabWorkMarch_2.dto.TaskDto;
import com.example.LabWorkMarch_2.entity.Task;
import com.example.LabWorkMarch_2.exeption.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskDao taskDao;

    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public void addTask(String header, String desc, String email){
        Task task = new Task();
        task.setHeader(header);
        task.setDescription(desc);
        task.setEmailOfAuthor(email);
        task.setToDate(LocalDate.now());
        taskDao.addTask(task);
    }

    public List<TaskDto> getTasksOfUser(String email){
        var taskList = taskDao.getTasksOfUser(email);
        return taskList.stream().map(TaskDto::from).collect(Collectors.toList());
    }

    public TaskDto checkDescByID(Long id){
        var task = taskDao.checkDescByID(id)
                .orElseThrow(()->new ResourceNotFoundException("Can't find task with this " + id));
        return TaskDto.from(task);
    }

}
