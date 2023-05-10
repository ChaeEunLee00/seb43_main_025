package com.codestates.auth.utils;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorResponder { // HTTP 응답 에러처리
    public static void sendErrorResponse(HttpServletResponse response, HttpStatus httpStatus) throws IOException {

        Gson gson = new Gson();
        ErrorResponse errorResponse = ErrorResponse.of(httpStatus);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(httpStatus.value());
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class)); //문자스트림 처리•전송
    }
}
