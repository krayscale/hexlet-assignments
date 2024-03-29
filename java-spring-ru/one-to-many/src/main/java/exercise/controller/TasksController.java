package exercise.controller;

import java.util.List;
import java.util.stream.Collectors;

import exercise.dto.*;
import exercise.mapper.TaskMapper;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TaskRepository repository;

    @Autowired UserRepository userRepository;

    @Autowired
    private TaskMapper mapper;

    @GetMapping(path = "")
    public List<TaskDTO> index() {
        var tasks = repository.findAll();
        return tasks.stream()
                .map(p -> mapper.map(p))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public TaskDTO show(@PathVariable long id) {

        var task =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        return mapper.map(task);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDTO createDTO) {
        var task = mapper.map(createDTO);
        repository.save(task);
        return mapper.map(task);
    }

    @PutMapping(path = "/{id}")
    public TaskDTO update(@PathVariable long id, @Valid @RequestBody TaskUpdateDTO updateDTO) {
        var task =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        mapper.update(updateDTO, task);

        var user = userRepository.findById(updateDTO.getAssigneeId()).orElseThrow(() -> new ResourceNotFoundException("User with id " + updateDTO.getAssigneeId() + " not found"));;
        task.setAssignee(user);
        repository.save(task);
        return mapper.map(task);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }
}
