package com.taskmanager.task_api.repository;

import com.taskmanager.task_api.entity.Task;
import com.taskmanager.task_api.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //Metodo para filtrar tareas por estado
    List<Task> findByStatus(String status);

    //Metodo para buscar tareas por titulo
    List<Task> findByTitleContainingIgnoreCase(String title);

    //Query personalizada para obtener tareas ordenadas por fecha de creacion
    @Query("SELECT t FROM Task t ORDER BY t.createdAt DESC")
    List<Task> findAllOrderByCreatedAtDesc();

    // Query para contar tareas por estado
    @Query("SELECT COUNT(t) FROM Task t WHERE t.status = :status")
    Long countByStatus(@Param("status") TaskStatus status);
}
