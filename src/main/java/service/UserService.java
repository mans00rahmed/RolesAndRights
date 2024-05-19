package service;

import Repository.UserRepository;
import dto.UserDto;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToDto).orElse(null);
    }

    public UserDto createUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        user = userRepository.save(user);
        return convertToDto(user);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEmail(userDto.getEmail());
            user.setEnabled(userDto.isEnabled());
            user.setFirstName(userDto.getFirstName());
            user.setUsing2fa(userDto.isUsing2fa());
            user.setLastName(userDto.getLastName());
            user.setPassword(userDto.getPassword());
            user.setSecret(userDto.getSecret());
            user = userRepository.save(user);
            return convertToDto(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setEnabled(user.isEnabled());
        userDto.setFirstName(user.getFirstName());
        userDto.setUsing2fa(user.isUsing2fa());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setSecret(user.getSecret());
        return userDto;
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setEnabled(userDto.isEnabled());
        user.setFirstName(userDto.getFirstName());
        user.setUsing2fa(userDto.isUsing2fa());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setSecret(userDto.getSecret());
        return user;
    }
}