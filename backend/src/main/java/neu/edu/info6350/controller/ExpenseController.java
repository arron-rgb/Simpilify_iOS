package neu.edu.info6350.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import neu.edu.info6350.model.Expense;
import neu.edu.info6350.model.User;
import neu.edu.info6350.service.ExpenseService;
import neu.edu.info6350.service.UserService;

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

  @GetMapping("/all")
  List<Expense> getAll() {
    User info = userService.getInfo();
    return expenseService.list(Wrappers.<Expense>lambdaQuery().eq(Expense::getUserId, info.getId()));
  }

  @DeleteMapping("/remove")
  void remove(String id) {
    expenseService.remove(Wrappers.<Expense>lambdaQuery().eq(Expense::getId, id));
  }

  @PostMapping("/create")
  Expense create(Expense expense) {
    expense.setUserId(userService.getId());
    expenseService.save(expense);
    return expense;
  }

}
