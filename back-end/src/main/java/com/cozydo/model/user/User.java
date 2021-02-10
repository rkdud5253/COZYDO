package com.cozydo.model.user;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_idx")
	private long userIdx; // autoincrase

	@NonNull
	@NotNull
	@Column(name = "user_email")
	private String email;

	@NonNull
	@NotNull
	@Column(name = "user_password")
	private String password;

	@NonNull
	@NotNull
	@Column(name = "user_name")
	private String name;

	@NonNull
	@NotNull
	@Column(name = "user_nickname")
	private String nickname;

	@NonNull
	@Column(name = "user_authkey")
	private String authkey;

	@Column(name = "user_authstatus")
	private int authStatus;

}