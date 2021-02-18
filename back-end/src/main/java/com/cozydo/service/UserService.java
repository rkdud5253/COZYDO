package com.cozydo.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.cozydo.dao.UserDao;
import com.cozydo.jwt.JwtTokenProvider;
import com.cozydo.mail.EmailUtil;
import com.cozydo.model.BasicResponse;
import com.cozydo.model.user.SignupRequest;
import com.cozydo.model.user.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmailUtil emailUtil;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public Object Signup(SignupRequest request, BindingResult bindingResult) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();

		if (bindingResult.hasErrors()) {// @valid가 틀렸을 경우 에러와 같이 보내줌
			List<ObjectError> errorList = bindingResult.getAllErrors();
			result.status = false;
			result.data = "조건에 맞게 작성하여 주세요.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}
		if (userDao.getUserByEmail(request.getEmail()).isPresent()) { // userDao 에서 이메일 통해 찾아낸 user가 있으면 중복된거
			result.status = false;
			result.data = "이메일이 중복됩니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else if (userDao.getUserBynickname(request.getNickname()) != null) { // userDao 에서 닉네임통해 찾아낸 user가 있으면 중복된 거 !
			result.status = false;
			result.data = "닉네임이 중복됩니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			String Authkey = emailUtil.GetRandom(); // 회원 인증 번호 완성
			emailUtil.sendEmailToEmail(request.getEmail(), "Cozydo홈페이지에서 " + request.getName() + "님에게 회원 인증 요청 이메일입니다.",
					Authkey);
			userDao.save(
					User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
							.name(request.getName()).nickname(request.getNickname()).authkey(Authkey)
							.roles(Collections.singletonList("ROLE_USER")).build())
					.getUserIdx();
			result.status = true;
			result.data = "이메일을 통해 회원 인증 후 로그인 해주세요.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		return response;
	}

	public Object Login(String email, String password) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Map<String, String> map = new HashMap<String, String>();

		Optional<User> user = userDao.getUserByEmail(email);
		if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
			if (user.get().getAuthStatus() != 1) { // 인증이 안됬다면
				result.status = false;
				result.data = "이메일 인증 후 로그인 해주세요.";
				response = new ResponseEntity<>(result, HttpStatus.OK);
			} else { // 인증이 된 아이디라면
				map.put("idx", Long.toString(user.get().getUserIdx()));
				map.put("nickname", user.get().getNickname());
				map.put("email", user.get().getEmail());
				result.status = true;
				result.data = jwtTokenProvider.createToken(user.get().getUsername(), user.get().getRoles());
				result.object = map;
				response = new ResponseEntity<>(result, HttpStatus.OK);
			}
		} else {
			result.status = false;
			result.data = "아이디와 비밀번호를 확인 후 로그인 해주세요.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		return response;
	}

	public Object Update(SignupRequest request, BindingResult bindingResult) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();

		if (bindingResult.hasErrors()) {// @valid가 틀렸을 경우 에러와 같이 보내줌
			List<ObjectError> errorList = bindingResult.getAllErrors();
			result.status = false;
			result.data = "조건에 맞게 작성하여 주세요.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}
		Optional<User> user = userDao.getUserByEmail(request.getEmail());
		if (user.isPresent()) {
			user.ifPresent(UpdateUser -> {
				UpdateUser.setEmail(request.getEmail());
				UpdateUser.setPassword(request.getPassword());
				UpdateUser.setName(request.getName());
				UpdateUser.setNickname(request.getNickname());
				User newUser = userDao.save(UpdateUser);
			});
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "false";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		return response;
	}

	public Object Delete(String email) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Optional<User> user = userDao.getUserByEmail(email);

		if (user.isPresent()) {
			userDao.deleteById((long) user.get().getUserIdx());
			result.status = true;
			result.data = user.get().getNickname();
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "존재하지 않는 아이디입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		return response;
	}

	public Object FindPW(String email, String name) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Optional<User> user = userDao.getUserByEmailAndName(email, name);
		if (user.isPresent()) {
			String imsipw = emailUtil.GetRandom();

			user.ifPresent(UpdateUser -> {// 비번 임시비번으로 변경
				UpdateUser.setPassword(passwordEncoder.encode(imsipw));
				User newUser = userDao.save(UpdateUser);
			});
			emailUtil.sendEmailTOPW(email, "Cozydo홈페이지에서 " + name + "님께 보낸 임시비밀번호 입니다.", imsipw);

			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "false";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		return response;
	}

	public Object FindAuthkey(String email, String authkey) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Optional<User> user = userDao.getUserByEmailAndAuthkey(email, authkey);
		if (user.isPresent()) {
			user.ifPresent(UpdateUser -> {
				UpdateUser.setAuthStatus(1);
				User newUser = userDao.save(UpdateUser);
			});
			result.status = true;
			result.data = "인증이 완료되었습니다. 로그인을 해주세요.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "false";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		return response;
	}
}
