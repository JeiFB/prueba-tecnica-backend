package com.tcc.taskmanager.infraestructure.output.jpa.repository;

import com.tcc.taskmanager.infraestructure.output.jpa.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.tcc.taskmanager.domain.models.TaskPriority;
import com.tcc.taskmanager.domain.models.TaskStatus;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
 
@Repository
public interface ITaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query("SELECT t FROM TaskEntity t WHERE (:status IS NULL OR t.status = :status) AND (:priority IS NULL OR t.priority = :priority) AND (:fromDate IS NULL OR t.dueDate >= :fromDate) AND (:toDate IS NULL OR t.dueDate <= :toDate)")
    List<TaskEntity> findByFilters(@Param("status") TaskStatus status, @Param("priority") TaskPriority priority, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

    @Query("SELECT t FROM TaskEntity t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<TaskEntity> findByTitleContainingIgnoreCase(@Param("searchTerm") String searchTerm, Pageable pageable);
} 