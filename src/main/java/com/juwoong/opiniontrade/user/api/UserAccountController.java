// package com.juwoong.opiniontrade.user.api;
//
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.juwoong.opiniontrade.user.api.request.LoginRequest;
// import com.juwoong.opiniontrade.user.api.request.SignUpRequest;
// import com.juwoong.opiniontrade.user.application.UserAccountService;
// import com.juwoong.opiniontrade.user.domain.Email;
// import com.juwoong.opiniontrade.user.domain.Password;
//
// @RestController
// @RequestMapping("/users/account")
// public class UserAccountController {
// 	private final UserAccountService userService;
//
// 	public UserAccountController(UserAccountService userService) {
// 		this.userService = userService;
// 	}
//
// 	@PostMapping("/sign-up")
// 	public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
// 		Email email = signUpRequest.email();
// 		Password password = signUpRequest.password();
// 		SignUpResponse signUpResponse = userService.signUp(email, password);
//
// 		return new ResponseEntity<>(signUpResponse, HttpStatus.CREATED);
// 	}
//
// 	@PostMapping("/login")
// 	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
// 		Email email = loginRequest.email();
// 		Password password = loginRequest.password();
// 		LoginResponse loginResponse = userService.login(email, password);
//
// 		return ResponseEntity.ok(loginResponse);
// 	}
// }
