package com.godbyul.modern_api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.godbyul.modern_api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

    @Schema(description = "사용자 ID", example = "1")
    private Long id;
    @Schema(description = "사용자 이름", example = "홍길동")
    private String name;
    @Schema(description = "이메일 주소", example = "hong@example.com")
    private String email;

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
