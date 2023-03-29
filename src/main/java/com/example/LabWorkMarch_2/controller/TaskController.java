package com.example.LabWorkMarch_2.controller;

import com.example.LabWorkMarch_2.dto.TaskDto;
import com.example.LabWorkMarch_2.service.TaskService;
import com.example.LabWorkMarch_2.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/taskList")
    public List<TaskDto> getTasksOfUser(Authentication authentication){
        Long idOfAuthor =  userService.findUserByEmail(authentication.getName());
        return taskService.getTasksOfUser(idOfAuthor);
    }

    @GetMapping("/description")
    public TaskDto checkDescByID(Long id){
        return taskService.checkDescByID(id);
    }

    @PostMapping("/newTask")
    public void newTask(@RequestParam String header,@RequestParam String desc, Authentication authentication){
        Long idOfAuthor =  userService.findUserByEmail(authentication.getName());
        taskService.addTask(header,desc,idOfAuthor);
    }
    @PostMapping("/newStatus")
    public void changeStatus(@RequestParam String status,@RequestParam Long idOfTask, Authentication authentication){
        Long idOfAuthor =  userService.findUserByEmail(authentication.getName());
        taskService.changeStatus(status,idOfAuthor,idOfTask);
    }
}
