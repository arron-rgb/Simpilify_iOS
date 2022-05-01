package neu.edu.info6350.exception;

/**
 * @author arronshentu
 */
public interface Messages {
  String USERNAME_CANNOT_BE_BLANK = "Username cannot be blank";
  String USER_DOES_NOT_EXIST = "User does not exist";
  String LINK_IS_NOT_VALID = "Link is not valid";
  String USER_EXIST = "Email ID already exists";
  String USERNAME_EMPTY = "Username cannot be empty";
  String USER_HAS_ALREADY_ACTIVATED = "User has already activated";
  String PASSWORD_DOES_NOT_EQUAL = "Password doesn't match";
  String USER_ID_MATCH_ERROR = "User ID doesn't match";
  String TODO_DOES_NOT_EXIST = "Todo doesn't exist";
  String EXPENSE_DOES_NOT_EXIST = "Expense doesn't exist";
  String EMAIL_REQUIRED = "Email address is required";
  String PASSWORD_REQUIRED = "Password is required";
  String CANNOT_INVITE_YOURSELF = "You cannot invite yourself";
  String INVITE_SOMEONE = "success";
  String NOT_VERIFIED = "This user is still not verified";
  String DATA_AUTH = "No permission to view";
  String ALREADY_JOINED_YOUR_GROUP = "This user has already joined your group";
  String EXPIRED_TOKEN = "Expired token";
  String INVALID_TOKEN = "Invalid token";
  String JOINED_THIS_GROUP = "You have joined this group";
  String YOU_BELONG_TO_ANOTHER_GROUP = "You belong to another group";
  String LINK_EXISTS = "link exists";
  String USER_CANNOT_JOIN_TWO_GROUPS = "User cannot join two groups";
  String GROUP_DOES_NOT_EXIST = "Group does not exist";
  String GROUP_ID_CANNOT_BE_NULL = "Group id cannot be null";
  String DON_T_BELONG_TO_A_GROUP = "You don't belong to a group";
  String TODO_ID_CANNOT_BE_NULL = "Todo id cannot be null";
}
