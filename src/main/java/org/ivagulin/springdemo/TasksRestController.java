package org.ivagulin.springdemo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.ivagulin.springdemo.entities.Task;
import org.ivagulin.springdemo.repositories.TaskRepository;
import org.ivagulin.springdemo.rest.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/tasks")
public class TasksRestController {
	@Autowired
	TaskRepository taskRepository;
	
	@GetMapping
	public List<TaskDTO> getTasks(@RequestParam Optional<Integer> page) {
		Page<Task> tasks = taskRepository.findAll(PageRequest.of(page.orElse(0), 100, Sort.by("rank")));
		return tasks.stream()
				.map(t -> new TaskDTO(t.getName()))
				.collect(Collectors.toList());
	}
	
	@PostMapping
	public void postTask(@RequestBody TaskDTO taskDTO) {
		Task newTask = new Task();
		newTask.setName(taskDTO.getName());
		taskRepository.save(newTask);
	}
	
	@GetMapping(path = "empty")
	public TaskDTO emptyTask() {
		return new TaskDTO();
	}
}
