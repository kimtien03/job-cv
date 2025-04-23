package org.example.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JobApiService {

    private final RestTemplate restTemplate;

    @Value("${api.base-url}")
    private String apiBaseUrl;

    public JobApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Lấy danh sách tất cả người dùng
    public List<User> getAllUsers() {
        String url =   "http://localhost:8090/api/user/GetAllUser";
        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);
        return Arrays.asList(response.getBody());
    }

//    // Lấy thông tin người dùng theo ID
//    public User getUserById(int id) {
//        String url =   "http://localhost:8090/api/User/GetUserById/"+id;
//        return restTemplate.getForObject(url, User.class);
//    }
//
//    // Tạo mới người dùng
//    public User createUser(User user) {
//        String url =   "http://localhost:8090/api/User/GetAllUser";
//        return restTemplate.postForObject(url, user, User.class);
//    }
//
//    // Cập nhật thông tin người dùng
//    public void updateUser(int id, User user) {
//        String url =   "http://localhost:8090/api/User/GetAllUser";
//        restTemplate.put(url, user);
//    }

//    // Xóa người dùng
//    public void deleteUser(int id) {
//        String url =   "http://localhost:8090/api/User/Delete/"+id;
//        restTemplate.delete(url);
//    }

    public ResponseEntity<LoginResponse> login(LoginRequest  request) {
        try {
        String url = "http://localhost:8090/api/auth/login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(request, headers);
            return restTemplate.exchange(url, HttpMethod.POST, requestEntity, LoginResponse.class);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(new LoginResponse("Lỗi xác thực!", null, null), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(new LoginResponse("Lỗi kết nối máy chủ!", null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> register(UserCreate user) {
        try {

            String url = "http://localhost:8090/api/auth/register";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<UserCreate> requestEntity = new HttpEntity<>(user, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        } catch (HttpClientErrorException e) {
            // Nếu có lỗi từ API trả về (400, 401, v.v.), lấy message lỗi từ response
            String errorMessage = e.getResponseBodyAsString();
            System.out.println("errorMessage:" +errorMessage);// Lấy message lỗi từ response của server
            ErrorResponse ErrorResponse = new ErrorResponse();
            ErrorResponse.setError("Lỗi xác thực!");
            ErrorResponse.setMessage(errorMessage);
            return new ResponseEntity<>(ErrorResponse, e.getStatusCode());
        } catch (Exception e) {
            // Nếu có lỗi kết nối
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError("Lỗi kết nối máy chủ!");
            String rawMessage = e.getMessage();
            String extractedMessage = rawMessage;
            // Thử trích xuất message từ JSON nếu có
            Pattern pattern = Pattern.compile("\"message\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(rawMessage);
            if (matcher.find()) {
                extractedMessage = matcher.group(1);
            }
            errorResponse.setMessage(extractedMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<LoginResponse> loginWithGoogle(String  id_token) {
        try {
            String url = "http://localhost:8090/api/auth/logingoogle";
            String jsonBody = "{\"idToken\": \"" + id_token + "\"}";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
            return restTemplate.exchange(url, HttpMethod.POST, requestEntity, LoginResponse.class);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(new LoginResponse("Lỗi xác thực!", null, null), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(new LoginResponse("Lỗi kết nối máy chủ!", null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> resetPassword(String email, String newPassword) {
        try {
            String url = "http://localhost:8090/api/auth/reset-password";
            // Tạo jsonBody với email và mật khẩu mới
            String jsonBody = "{\"email\": \"" + email + "\", \"newPassword\": \"" + newPassword + "\"}";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
            // Gửi yêu cầu POST tới API và nhận phản hồi dưới dạng String
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
            return response;
        } catch (HttpClientErrorException e) {
            try {
                // Parse JSON lỗi nếu có
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(e.getResponseBodyAsString());
                String error = jsonNode.has("error") ? jsonNode.get("error").asText() : "Lỗi không xác định";
                String message = jsonNode.has("message") ? jsonNode.get("message").asText() : "Không có thông điệp lỗi";
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setError(error);
                errorResponse.setMessage(message);
                return new ResponseEntity<>(errorResponse, e.getStatusCode());
            } catch (Exception parseException) {
                return new ResponseEntity<>("Lỗi xác thực!", e.getStatusCode());
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi kết nối máy chủ!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
