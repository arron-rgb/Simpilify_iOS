package neu.edu.info6350.repository;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import neu.edu.info6350.model.Expense;

/**
 * @author arronshentu
 */
@Mapper
public interface ExpenseMapper extends BaseMapper<Expense> {}
