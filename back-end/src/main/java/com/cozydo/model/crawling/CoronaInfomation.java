package com.cozydo.model.crawling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CoronaInfomation {
	@NonNull
	private String gubun;// 도시
	@NonNull
	private String CreateDt;// 날짜
	private int DeathCnt;// 누적사망자 수
	private int DecideCnt;// 누적 확진자 수
	private int Clearcnt;// 누적 격리해제 수
	@NonNull
	private int Today_DeathCnt; // 당일 사망자 수
	@NonNull
	private int Today_DecideCnt; // 당일 확진자 수
	private int Today_Clearcnt; // 당일 격리해제 수
	private int Local; // 국내발생
	private int Overflow; // 해외발생

}
