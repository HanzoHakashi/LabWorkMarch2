package com.example.LabWorkMarch_2.service;

import com.example.LabWorkMarch_2.dao.TaskDao;
import com.example.LabWorkMarch_2.dao.UserDao;
import com.example.LabWorkMarch_2.dto.TaskDto;
import com.example.LabWorkMarch_2.entity.Task;
import com.example.LabWorkMarch_2.exeption.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskDao taskDao;


    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public void addTask(String header, String desc,LocalDate toDate, Long idOfAuthor){
        Task task = new Task();
        task.setHeader(header);
        task.setDescription(desc);
        task.setIdOfAuthor(idOfAuthor);
        task.setToDate(toDate);
        taskDao.addTask(task);
    }

    public void changeStatus(String status, Long idOfAuthor, Long id){
        if (!Arrays.asList("NEW", "IN WORK", "COMPLETED").contains(status.toUpperCase())) {
            System.out.println("Invalid status value: " + status);
            return;}
        taskDao.changeStatus(status, idOfAuthor,id);
    }

    public List<TaskDto> getTasksOfUser(Long idOfAuthor){
        var taskList = taskDao.getTasksOfUser(idOfAuthor);
        return taskList.stream().map(TaskDto::from).collect(Collectors.toList());
    }

    public TaskDto checkDescByID(Long id){
        var task = taskDao.checkDescByID(id)
                .orElseThrow(()->new ResourceNotFoundException("Can't find task with this " + id));
        return TaskDto.from(task);
    }

}
