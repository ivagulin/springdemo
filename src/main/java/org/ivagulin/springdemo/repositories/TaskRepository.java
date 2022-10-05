package org.ivagulin.springdemo.repositories;

import org.ivagulin.springdemo.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
	Page<Task> findAll(Pageable page);
}
