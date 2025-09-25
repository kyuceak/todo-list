package com.kutay.todolist.service;

import com.kutay.todolist.DTO.TodoRequestDTO;
import com.kutay.todolist.DTO.TodoResponseDTO;
import com.kutay.todolist.model.Todo;
import com.kutay.todolist.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TodoServiceImpl implements TodoService{


    private final TodoRepository todoRepository;


    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository)
    {
        this.todoRepository = todoRepository;
    }


    @Override
    public List<TodoResponseDTO> findAll() {



        return todoRepository.findAll()
                .stream()
                .map(todo -> new TodoResponseDTO(
                        todo.getId(),
                        todo.isCompleted(),
                        todo.getDescription(),
                        todo.getTitle()))
                .toList();
    }

    @Override
    @Transactional
    public TodoResponseDTO save(TodoRequestDTO dto) {

        Todo todo = new Todo();

        todo.setTitle(dto.title());
        todo.setDescription(dto.description());
        todo.setCompleted(dto.completed());

        Todo saved = todoRepository.save(todo);


       return new TodoResponseDTO(saved.getId(),saved.isCompleted(),saved.getDescription(), saved.getTitle());
    }

    @Override
    public TodoResponseDTO findById(Long id) {

        Optional<Todo> result = todoRepository.findById(id);

        Todo todo = null;

        if(result.isPresent()){
           todo = result.get();
        }else {
            throw new RuntimeException("cant find the employee.");
        }

        return new TodoResponseDTO(todo.getId(), todo.isCompleted(),todo.getDescription(),todo.getTitle());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public List<TodoResponseDTO> searchByTitle(String title) {

        return todoRepository.findAllByTitle(title)
                .stream()
                .map(todo -> new TodoResponseDTO(
                        todo.getId(),
                        todo.isCompleted(),
                        todo.getDescription(),
                        todo.getTitle())).toList();
    }


    @Override
    public List<TodoResponseDTO> getCompleted() {
        return todoRepository.fetchAllCompleted().stream()
                .map(todo -> new TodoResponseDTO(
                        todo.getId(),
                        todo.isCompleted(),
                        todo.getDescription(),
                        todo.getTitle())).toList();
    }

    @Override
    public List<TodoResponseDTO> getUncompleted() {
        return todoRepository.fetchAllunCompleted().stream()
                .map(todo -> new TodoResponseDTO(
                        todo.getId(),
                        todo.isCompleted(),
                        todo.getDescription(),
                        todo.getTitle())).toList();
    }
}
