package neu.edu.info6350.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import neu.edu.info6350.model.Todo;
import neu.edu.info6350.repository.TodoMapper;
import neu.edu.info6350.service.TodoService;

/**
 * @author arronshentu
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements TodoService {}
