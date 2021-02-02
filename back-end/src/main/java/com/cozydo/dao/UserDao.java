
package com.cozydo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozydo.model.user.User;

public interface UserDao extends JpaRepository<User, String> {
	User getUserByEmail(String email);

	User getUserByUid(String nickname);

	Optional<User> findUserByEmailAndPassword(String email, String password);
}
