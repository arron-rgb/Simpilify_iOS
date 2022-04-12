package neu.edu.info6350.model;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author arronshentu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_todo")
public class Todo {


  String id;
  @NotEmpty
  String name;
  @NotEmpty
  String description;
  Boolean status;
  String userId;

  LocalDateTime createdTime;
  LocalDateTime updatedTime;
}
