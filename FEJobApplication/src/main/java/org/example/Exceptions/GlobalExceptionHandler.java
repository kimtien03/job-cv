package org.example.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // 404 - Không tìm thấy URL
    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    public String handleNotFound(Exception ex, Model model) {
        logger.warn("404: {}", ex.getMessage());
        model.addAttribute("statusCode", 404);
        model.addAttribute("message", "Trang bạn tìm không tồn tại.");
        return "user/error";
    }

    // 400 - Lỗi yêu cầu sai (BadRequest)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleBadRequest(MethodArgumentNotValidException ex, Model model) {
        logger.warn("400: {}", ex.getMessage());
        model.addAttribute("statusCode", 400);
        model.addAttribute("message", "Yêu cầu không hợp lệ.");
        return "user/error";
    }

    // 403 - Truy cập bị từ chối
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(AccessDeniedException ex, Model model) {
        logger.warn("403: {}", ex.getMessage());
        model.addAttribute("statusCode", 403);
        model.addAttribute("message", "Bạn không có quyền truy cập trang này.");
        return "user/error";
    }

    // 500 - Lỗi hệ thống
    @ExceptionHandler(Exception.class)
    public String handleServerError(Exception ex, Model model) {
        logger.error("500: ", ex);
        model.addAttribute("statusCode", 500);
        model.addAttribute("message", "Đã xảy ra lỗi hệ thống.");
        return "user/error";
    }

}
