package com.cozydo.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Valid
@ToString
public class SignupRequest {
	@ApiModelProperty(required = true)
	@NotNull
	@Email(message = "이메일 형식이 아닙니다.")
	String email;

	@ApiModelProperty(required = true)
	@NotNull
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,20}$", message = "공백없이 영어와숫자의 조합으로 8~20자 이내로 작성해주세요.")
	String password;

	@ApiModelProperty(required = true)
	@NotNull
	@Pattern(regexp = "^[가-힣]*$", message = "2~4자이내 한글로만 작성하세요.")
	String name;

	@ApiModelProperty(required = true)
	@NotNull
	@Size(max = 6,min = 2,message = "2~6자이내로 작성하세요.")
	String nickname;
}
