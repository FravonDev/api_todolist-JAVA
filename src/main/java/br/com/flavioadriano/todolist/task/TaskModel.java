package br.com.flavioadriano.todolist.task;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(length = 50)
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate startAt;
    private LocalDate endAt;

    @CreationTimestamp
    private LocalDate createdAt;
    private UUID idUser;
}
