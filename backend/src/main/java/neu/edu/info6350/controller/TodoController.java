package neu.edu.info6350.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import neu.edu.info6350.exception.MyRuntimeException;
import neu.edu.info6350.model.Todo;
import neu.edu.info6350.model.User;
import neu.edu.info6350.service.TodoService;
import neu.edu.info6350.service.UserService;

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

  @GetMapping("/all")
  List<Todo> getAll() {
    User info = userService.getInfo();
    return todoService.list(Wrappers.<Todo>lambdaQuery().eq(Todo::getUserId, info.getId()));
  }

  @DeleteMapping("/remove")
  void remove(String id) {
    todoService.remove(Wrappers.<Todo>lambdaQuery().eq(Todo::getId, id));
  }

  @PostMapping("/create")
  Todo create(Todo todo) {
    todo.setUserId(userService.getId());
    todo.setCreatedTime(LocalDateTime.now());
    todo.setUpdatedTime(LocalDateTime.now());
    todoService.save(todo);
    return todo;
  }

  @PutMapping("/update")
  Todo update(Todo todo) {
    Todo one = todoService.getOne(Wrappers.<Todo>lambdaQuery().eq(Todo::getUserId, userService.getId()));
    if (StringUtils.equals(one.getId(), todo.getId())) {
      throw new MyRuntimeException("todo");
    }

    // todo.setUserId(userService.getId());
    todo.setUpdatedTime(LocalDateTime.now());
    todoService.save(todo);
    return todo;
  }

}
