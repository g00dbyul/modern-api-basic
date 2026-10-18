package com.godbyul.modern_api.user.controller;

import com.godbyul.modern_api.global.exception.ErrorResponse;
import com.godbyul.modern_api.global.response.PageResponse;
import com.godbyul.modern_api.user.dto.CreateUserRequest;
import com.godbyul.modern_api.user.dto.UpdateUserRequest;
import com.godbyul.modern_api.user.dto.UserResponse;
import com.godbyul.modern_api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users", description = "사용자 등록·조회·수정·삭제 API")
@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR: 서버 내부 오류",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "사용자 등록")
    @ApiResponse(responseCode = "201", description = "등록 성공")
    @ApiResponse(responseCode = "400", description = "INVALID_INPUT_VALUE: 이름 또는 이메일 검증 실패",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "USER_EMAIL_ALREADY_EXISTS: 이미 사용 중인 이메일",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request
    ) {
        UserResponse response = userService.createUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "사용자 단건 조회")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND: 사용자를 찾을 수 없음",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(
            @Parameter(description = "사용자 ID", example = "1") @PathVariable Long userId
    ) {
        UserResponse response = userService.getUser(userId);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "사용자 목록 조회")
    @ApiResponse(responseCode = "200", description = "페이지 단위 조회 성공")
    @GetMapping
    public ResponseEntity<PageResponse<UserResponse>> getUsers(
            @ParameterObject Pageable pageable
    ) {
        Page<UserResponse> page = userService.getUsers(pageable);

        return ResponseEntity.ok(PageResponse.from(page));
    }

    @Operation(summary = "사용자 이름 수정")
    @ApiResponse(responseCode = "200", description = "수정 성공")
    @ApiResponse(responseCode = "400", description = "INVALID_INPUT_VALUE: 이름 검증 실패",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND: 사용자를 찾을 수 없음",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @Parameter(description = "사용자 ID", example = "1") @PathVariable Long userId,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        UserResponse response = userService.updateUser(userId, request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "사용자 삭제")
    @ApiResponse(responseCode = "204", description = "삭제 성공", content = @Content)
    @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND: 사용자를 찾을 수 없음",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "사용자 ID", example = "1") @PathVariable Long userId
    ) {
        userService.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }
}
