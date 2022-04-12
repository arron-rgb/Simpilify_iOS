package neu.edu.info6350.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author arronshentu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  @NotEmpty(message = "first name cannot be empty")
  String firstName;
  @NotEmpty(message = "last name cannot be empty")
  String lastName;
  @Email(message = "email must be in email format")
  String email;
  @NotEmpty(message = "password cannot be empty")
  String password;
  @NotEmpty(message = "re-password cannot be empty")
  String rePassword;

}
