package com.web.corona.crowling;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.cozydo.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data // 전체를 아우르는 대신 찾아봐라
@Getter // lombok에서 지원해주는 어노테이션
@Setter // lombok에서 지원해주는 어노테이션
@ToString // lombok에서 지원해주는 어노테이션
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 모든 생성자
@JsonInclude(JsonInclude.Include.NON_NULL)
public class corona {
	private String date;
}
