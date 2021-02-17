package com.cozydo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cozydo.corona.crawling.Kakao;
import com.cozydo.corona.crawling.CoronaApi;
import com.cozydo.dao.CoronaLevelDao;
import com.cozydo.model.BasicResponse;
import com.cozydo.model.crawling.CoronaInfomation;
import com.cozydo.model.crawling.Coronalevel;

@Service
public class CoronaService {
	@Autowired
	CoronaLevelDao coronaLevelDao;

	@Autowired
	CoronaApi coronaApi;

	public Object SaveAll() throws IOException {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();

		List<Coronalevel> list = new ArrayList<Coronalevel>();
		String url = "http://ncov.mohw.go.kr/";
		Document document = Jsoup.connect(url).get();// 해당 URL에 연결
		Elements p_name = document.getElementsByClass("rssm_graph").select("button"); // class이름으로 긁기
		// 지역별 단계
		String[] arr = new String[15];
		arr = p_name.text().split(" ");
		for (String a : arr) {
			String si = a.substring(0, 2);
			String level = a.substring(2);
			list.add(new Coronalevel(si, level));
		}

		if (list.size() > 0) {
			coronaLevelDao.deleteAll();
			for (Coronalevel level : list) {
				coronaLevelDao.save(level);
			}
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		} else {
			result.status = false;
			result.data = "크롤링 실패";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}
	}

	public Object Location(String lat, String lon) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();

		Kakao kakao = new Kakao(lon, lat);
		String city = kakao.getSi();

		if (city != null) {// 성공일 경우
			if (city.contains("제주"))
				city = "제주";
			Coronalevel level = coronaLevelDao.getCoronalevelByCity(city);
			result.status = true;
			result.data = "success";
			result.object = level;
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		} else {
			result.status = false;
			result.data = "false";
			result.object = "위치가 잘못되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}
	}

	public Object Confirmed() {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();

		List<ArrayList<String[]>> list = coronaApi.Classification();
		if (list.size() == 0) {
			result.status = false;
			result.data = "false";
			result.object = "트레픽 초과 또는 정보를 읽을 수 없습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {

			List<CoronaInfomation> today = TodayConfirmed(list.get(0));
			List<CoronaInfomation> sido = SidoConfirmed(list.get(1));
			List<CoronaInfomation> week = WeekConfirmed(list.get(2));
			List<List<CoronaInfomation>> AllList = new ArrayList<List<CoronaInfomation>>();
			AllList.add(today);
			AllList.add(sido);
			AllList.add(week);

			result.status = true;
			result.data = "success";
			result.object = AllList;
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		return response;
	}

	private List<CoronaInfomation> TodayConfirmed(List<String[]> list) {
		List<CoronaInfomation> info = new ArrayList<CoronaInfomation>();
		String gubun = list.get(0)[0];
		String date = list.get(0)[1];
		int DeathCnt = Integer.parseInt(list.get(0)[2]);
		int DecideCnt = Integer.parseInt(list.get(0)[3]);
		int Clearcnt = Integer.parseInt(list.get(0)[5]);
		int Today_DeathCnt = Integer.parseInt(list.get(0)[2]) - Integer.parseInt(list.get(1)[2]);
		int Today_DecideCnt = Integer.parseInt(list.get(0)[4]);
		int Today_Clearcnt = Integer.parseInt(list.get(0)[5]) - Integer.parseInt(list.get(1)[5]);
		int Local = Integer.parseInt(list.get(0)[6]);
		int Overflow = Integer.parseInt(list.get(0)[7]);
		info.add(new CoronaInfomation(gubun, date, DeathCnt, DecideCnt, Clearcnt, Today_DeathCnt, Today_DecideCnt,
				Today_Clearcnt, Local, Overflow));
		return info;
	}

	private List<CoronaInfomation> SidoConfirmed(List<String[]> list) {
		List<CoronaInfomation> info = new ArrayList<CoronaInfomation>();
		String gubun = "";
		String date = "";
		int DeathCnt = 0;
		int DecideCnt = 0;
		int Clearcnt = 0;
		int Today_DeathCnt = 0;
		int Today_DecideCnt = 0;
		int Today_Clearcnt = 0;
		int Local = 0;
		int Overflow = 0;

		for (int i = 0; i < 18; i++) {
			gubun = list.get(i)[0];
			date = list.get(i)[1];
			DeathCnt = Integer.parseInt(list.get(i)[2]);
			DecideCnt = Integer.parseInt(list.get(i)[3]);
			Clearcnt = Integer.parseInt(list.get(i)[5]);
			Local = Integer.parseInt(list.get(i)[6]);
			Overflow = Integer.parseInt(list.get(i)[7]);
			Today_DeathCnt = Integer.parseInt(list.get(i)[2]) - Integer.parseInt(list.get(i + 18)[2]);
			Today_DecideCnt = Integer.parseInt(list.get(i)[4]);
			Today_Clearcnt = Integer.parseInt(list.get(i)[5]) - Integer.parseInt(list.get(i + 18)[5]);
			info.add(new CoronaInfomation(gubun, date, DeathCnt, DecideCnt, Clearcnt, Today_DeathCnt, Today_DecideCnt,
					Today_Clearcnt, Local, Overflow));
		}

		return info;
	}

	private List<CoronaInfomation> WeekConfirmed(List<String[]> list) {
		List<CoronaInfomation> info = new ArrayList<CoronaInfomation>();
		int size = 7;
		String gubun = "";
		String date = "";
		int DeathCnt = 0;
		int DecideCnt = 0;
		int Clearcnt = 0;
		int Today_DeathCnt = 0;
		int Today_DecideCnt = 0;
		int Today_Clearcnt = 0;
		int Local = 0;
		int Overflow = 0;

		for (int i = 0; i < size; i++) {
			gubun = list.get(i)[0];
			date = list.get(i)[1];
			DeathCnt = Integer.parseInt(list.get(i)[2]);
			DecideCnt = Integer.parseInt(list.get(i)[3]);
			Clearcnt = Integer.parseInt(list.get(i)[5]);
			Local = Integer.parseInt(list.get(i)[6]);
			Overflow = Integer.parseInt(list.get(i)[7]);
			Today_DecideCnt = Integer.parseInt(list.get(i)[4]);
			Today_DeathCnt = Integer.parseInt(list.get(i)[2]) - Integer.parseInt(list.get(i + 1)[2]);
			Today_Clearcnt = Integer.parseInt(list.get(i)[5]) - Integer.parseInt(list.get(i + 1)[5]);
			info.add(new CoronaInfomation(gubun, date, DeathCnt, DecideCnt, Clearcnt, Today_DeathCnt, Today_DecideCnt,
					Today_Clearcnt, Local, Overflow));
		}
		return info;
	}
}
