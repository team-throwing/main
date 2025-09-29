package mission04.sanseong.b;

import mission04.sanseong.b.dto.UserDto;
import mission04.sanseong.b.user.User;

public class UserToUserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User source) {
        return new UserDto(source.getUsername());
    }
}
