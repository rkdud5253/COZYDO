
package com.cozydo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozydo.model.user.User;

public interface UserDao extends JpaRepository<User, Long> {
	User getUserByEmail(String email);

	User getUserBynickname(String nickname);

	Optional<User> getUserByEmailAndName(String email, String name);

	Optional<List<User>> findUserByEmailAndPassword(String email, String password);
}
