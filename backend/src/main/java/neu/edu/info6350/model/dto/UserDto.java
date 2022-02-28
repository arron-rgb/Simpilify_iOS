package neu.edu.info6350.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author arronshentu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  String firstName;
  String lastName;
  String email;
  String password;
  String rePassword;

}
