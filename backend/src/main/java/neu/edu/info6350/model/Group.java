package neu.edu.info6350.model;

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
@TableName("t_group")
public class Group {

  String id;
  String name;
  String userId;

  // LocalDateTime createdTime;
  // LocalDateTime updatedTime;
}
