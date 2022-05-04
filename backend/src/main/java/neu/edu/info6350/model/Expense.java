package neu.edu.info6350.model;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author arronshentu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_expense")
public class Expense {

  String id;
  String name;
  String description;
  String category;
  // String costInDollar;
  Double costInDollar;
  String userId;
  LocalDateTime expenseDate;

  // LocalDateTime createdTime;
  // LocalDateTime updatedTime;

}
