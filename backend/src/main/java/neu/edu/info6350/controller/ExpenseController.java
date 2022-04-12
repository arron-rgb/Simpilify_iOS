package neu.edu.info6350.controller;

import java.util.List;

import javax.annotation.Resource;

import neu.edu.info6350.exception.MyRuntimeException;
import neu.edu.info6350.exception.PermissionException;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import neu.edu.info6350.model.Expense;
import neu.edu.info6350.model.User;
import neu.edu.info6350.service.ExpenseService;
import neu.edu.info6350.service.UserService;

import static neu.edu.info6350.exception.Messages.DATA_AUTH;
import static neu.edu.info6350.exception.Messages.EXPENSE_DOES_NOT_EXIST;

/**
 * @author arronshentu
 */
@RequestMapping("expenses")
@RestController
public class ExpenseController {
  @Resource
  UserService userService;
  @Resource
  ExpenseService expenseService;

  @GetMapping("")
  List<Expense> getAll() {
    User info = userService.getInfo();
    return expenseService.list(Wrappers.<Expense>lambdaQuery().eq(Expense::getUserId, info.getId()));
  }

  @DeleteMapping("/{id}")
  void remove(@PathVariable String id) {
    if(expenseService.getById(id) == null){
      throw new MyRuntimeException(EXPENSE_DOES_NOT_EXIST);
    }
    if(!expenseService.getById(id).getUserId().equals(userService.getId()) ){
      throw new PermissionException(DATA_AUTH);
    }
    expenseService.remove(Wrappers.<Expense>lambdaQuery().eq(Expense::getId, id));
  }

  @PostMapping("")
  Expense create(@RequestBody Expense expense) {
    expense.setUserId(userService.getId());
    expenseService.save(expense);
    return expense;
  }

  @GetMapping("/{id}")
  Expense get(@PathVariable String id) {
    if(expenseService.getById(id) == null){
      throw new MyRuntimeException(EXPENSE_DOES_NOT_EXIST);
    }
    if(!expenseService.getById(id).getUserId().equals(userService.getId()) ){
      throw new PermissionException(DATA_AUTH);
    }
    return expenseService.getById(id);
  }

  @PutMapping("")
  Expense update(@RequestBody Expense expense) {
    if(!expense.getUserId().equals(userService.getId())) {
      throw new RuntimeException(DATA_AUTH);
    }
    expenseService.updateById(expense);
    return expense;
  }

}
