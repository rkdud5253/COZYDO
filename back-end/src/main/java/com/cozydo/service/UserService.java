package com.cozydo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.cozydo.dao.UserDao;
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

	public Object Signup(SignupRequest request, BindingResult bindingResult) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();

		if (bindingResult.hasErrors()) {// @valid가 틀렸을 경우 에러와 같이 보내줌
			List<ObjectError> errorList = bindingResult.getAllErrors();
			result.status = false;
			result.data = "조건에 맞게 작성하여 주세요.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (userDao.getUserByEmail(request.getEmail()) != null) { // userDao 에서 이메일 통해 찾아낸 user가 있으면 중복된거
			result.status = false;
			result.data = "이메일이 중복됩니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else if (userDao.getUserBynickname(request.getNickname()) != null) { // userDao 에서 닉네임통해 찾아낸 user가 있으면 중복된 거 !
			System.out.println(userDao.getUserBynickname(request.getNickname()));
			result.status = false;
			result.data = "닉네임이 중복됩니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			User user = new User(request.getEmail(), request.getPassword(), request.getName(), request.getNickname());
			userDao.save(user);
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		return response;
	}

	public Object Login(String email, String password) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Optional<User> user = userDao.findUserByEmailAndPassword(email, password);

		if (user.isPresent()) {
			result.status = true;
			result.data = user.get().getNickname();
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "false";
			response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
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
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}
		Optional<User> user = userDao.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
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
			response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		return response;
	}

	public Object Delete(String email, String password) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Optional<User> user = userDao.findUserByEmailAndPassword(email, password);

		if (user.isPresent()) {
			userDao.deleteById((long) user.get().getUserIdx());
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "false";
			response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		return response;
	}

	public Object FindPW(String email, String name) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Optional<User> user = userDao.getUserByEmailAndName(email, name);
		if (user.isPresent()) {
			String imsipw = emailUtil.GetRandomPW();

			user.ifPresent(UpdateUser -> {//비번 임시비번으로 변경
				UpdateUser.setPassword(imsipw);
				User newUser = userDao.save(UpdateUser);
			});
			emailUtil.sendEmail("xoghks11397@naver.com", "Cozydo홈페이지에서 " + name + "님께 보낸 임시비밀번호 입니다.",
					imsipw);
			
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "false";
			response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		return response;
	}
}
