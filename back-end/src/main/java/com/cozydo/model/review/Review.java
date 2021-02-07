package com.cozydo.model.review;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_idx")
	private long reviewIdx;
	
	
	@Column(name = "place_idx")
	private int placeIdx;

	@NotNull
	@Column(name = "user_idx")
	private int userIdx;

	@NotNull
	@Column(name = "review_score")
	private int reviewScore;

	@NonNull
	@NotNull
	private String content; 

	@NonNull
	@NotNull
	@Column(name = "write_time")
	private LocalDateTime  writeTime; 
	
	@PrePersist
    public void createdAt() {
        this.writeTime = LocalDateTime.now();
    }

}
