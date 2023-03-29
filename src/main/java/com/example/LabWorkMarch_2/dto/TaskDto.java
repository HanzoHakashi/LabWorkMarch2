package com.example.LabWorkMarch_2.dto;

import com.example.LabWorkMarch_2.entity.Task;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TaskDto {
    public static TaskDto from(Task task){
        return builder()
                .id(task.getId())
                .header(task.getHeader())
                .description(task.getDescription())
                .toDate(task.getToDate())
                .idOfAuthor(task.getIdOfAuthor())
                .status(task.getStatus())
                .build();
    }
    private Long id;
    private String header;
    private String description;
    private LocalDate toDate;
    private Long idOfAuthor;//fk
    private String status;
}
