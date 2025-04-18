package org.example.Services;

import org.example.Models.LoginRequest;
import org.example.Models.LoginResponse;
import org.example.Models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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
        String url =   "http://localhost:8090/api/User/GetAllUser";
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

}
