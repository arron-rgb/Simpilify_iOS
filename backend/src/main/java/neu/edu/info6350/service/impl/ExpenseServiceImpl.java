package neu.edu.info6350.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import neu.edu.info6350.model.Expense;
import neu.edu.info6350.repository.ExpenseMapper;
import neu.edu.info6350.service.ExpenseService;

/**
 * @author arronshentu
 */
@Service
public class ExpenseServiceImpl extends ServiceImpl<ExpenseMapper, Expense> implements ExpenseService {}
