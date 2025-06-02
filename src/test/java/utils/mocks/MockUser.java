package utils.mocks;

import com.thalesbensi.CoursesManagementAPI.api.dto.response.user.UserMinResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import com.thalesbensi.CoursesManagementAPI.domain.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

public class MockUser {

    public static Long ID = 1L;
    public static String NAME = "John Doe";
    public static String EMAIL = "john.doe@gmail.com";
    public static String PASSWORD = "password";
    public static UserRole USER_ROLE = UserRole.TEACHER;

    public static User mockUser() {
        return new User(ID, NAME, EMAIL, PASSWORD, USER_ROLE);
    }

    public static List<User> mockUserList() {
        User user = mockUser();
        List<User> userList = new ArrayList<>();
        userList.add(user);
        return userList;
    }

    public static UserMinResponseDTO mockUserMinResponseDTO() {
        return new UserMinResponseDTO(NAME,EMAIL,USER_ROLE);
    }

    public static List<UserMinResponseDTO> mockUserMinResponseDTOList() {
        UserMinResponseDTO userMinResponseDTO = mockUserMinResponseDTO();
        List<UserMinResponseDTO> userMinResponseDTOList = new ArrayList<>();
        userMinResponseDTOList.add(userMinResponseDTO);
        return userMinResponseDTOList;
    }
}