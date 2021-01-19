// 하단 DB 설정 부분은 Sub PJT II에서 데이터베이스를 구성한 이후에 주석을 해제하여 사용.

package com.cozydo.model.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //기본키를 설정하는 옵션에 4개중 1개인 identity 가 지금은 적합하지 않은것 같아서 주석처리했습니다.
    //이 부분이 주석이 처리가 되지 않으면 sql에서 넣을때 sqlException 납니다..
    private String uid;

    @JsonIgnore
    private String password;
    private String email;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createDate;

}
