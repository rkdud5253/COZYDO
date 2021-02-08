package com.cozydo.model.likes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Likes {

		
	@NotNull
	@Column(name = "user_idx")
	private int userIdx;
	
	@Id
	@NotNull
	@Column(name = "place_idx")
	private int placeIdx;

}
