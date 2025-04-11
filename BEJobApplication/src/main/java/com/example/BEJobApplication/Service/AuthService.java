//package com.example.bejobapplication.Service;
//
//import com.example.bejobapplication.DTO.LoginResponse;
//import com.example.bejobapplication.Entity.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class AuthService {
//    public LoginResponse authenticate(AuthenticationRequest request, HttpServletResponse response) {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//        boolean authentication = passwordEncoder.matches(request.getPassword(), user.getPassword());
//        if(!authentication) {
//            throw new AppException(ErrorCode.UNAUTHENTICATED);
//        }
//        UserResponse userResponse = UserMapper.toUserResponse(user);
//        var token = JwtUtils.generateToken(user);
//        JwtUtils.addTokenToCookie(response, token);
//        return AuthenticationResponse.builder().token(token).authenticated(true).user(userResponse).build();
//    }
//}
