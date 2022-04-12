package neu.edu.info6350.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import neu.edu.info6350.exception.PermissionException;
import neu.edu.info6350.exception.SchemeException;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import neu.edu.info6350.exception.MyRuntimeException;
import neu.edu.info6350.model.Todo;
import neu.edu.info6350.model.User;
import neu.edu.info6350.service.TodoService;
import neu.edu.info6350.service.UserService;

import static neu.edu.info6350.exception.Messages.*;
import static neu.edu.info6350.exception.Messages.DATA_AUTH;

/**
 * @author arronshentu
 */
@RequestMapping("todos")
@RestController
public class TodoController {

  @Resource
  UserService userService;
  @Resource
  TodoService todoService;

  @GetMapping("")
  List<Todo> getAll() {
    User info = userService.getInfo();
    return todoService.list(Wrappers.<Todo>lambdaQuery().eq(Todo::getUserId, info.getId()));
  }

  @DeleteMapping("/{id}")
  void remove(@PathVariable String id) {
    if(todoService.getById(id) == null){
      throw new MyRuntimeException(TODO_DOES_NOT_EXIST);
    }
    if(!todoService.getById(id).getUserId().equals(userService.getId()) ){
      throw new PermissionException(DATA_AUTH);
    }
    todoService.remove(Wrappers.<Todo>lambdaQuery().eq(Todo::getId, id));
  }

  @PostMapping("")
  Todo create(@RequestBody @Validated Todo todo, Errors errors) {
    if (errors.hasErrors()) {
      throw new SchemeException(
        Objects.requireNonNull(errors.getFieldError()).getField() + " is not a well-formed field");
    }
    todo.setUserId(userService.getId());
    todo.setCreatedTime(LocalDateTime.now());
    todo.setUpdatedTime(LocalDateTime.now());
    todoService.save(todo);
    return todo;
  }

  @PutMapping("")
  Todo update(Todo todo) {
    Todo one = todoService.getOne(Wrappers.<Todo>lambdaQuery().eq(Todo::getId, todo.getId()));
    if(!StringUtils.equals(todo.getUserId(), one.getUserId())){
      throw new PermissionException(DATA_AUTH);
    }
    todo.setUpdatedTime(LocalDateTime.now());
    todoService.save(todo);
    return todo;
  }

  @GetMapping("/{id}")
  Todo get(@PathVariable String id) {
    Todo one = todoService.getOne(Wrappers.<Todo>lambdaQuery().eq(Todo::getId, id));
    if(Objects.isNull(one)){
      throw new MyRuntimeException(TODO_DOES_NOT_EXIST);
    }
    return one;
  }

}
