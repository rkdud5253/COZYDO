package com.web.corona.crowling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class kakaoquestion {
	public static void main(String[] args) throws Exception {
		System.out.println(addrToCoord(URLEncoder.encode("서울", "UTF-8")));
//		System.out.println(coordToAddr("37.5665958","126.9783813"));
//		System.out.println(URLEncoder.encode("강남구", "UTF-8"));
		list();
	}

	public static void list() throws IOException {
//		{ "강남구", "강동구", "강서구", "강북구", "관악구", "광진구", "구로구", "금천구" ,"노원구", "동대문구", "도봉구", "동작구", "마포구" ,"서대문구", "성동구", "성북구", "서초구","송파구" ,"영등포구", "용산구", "양천구", "은평구" ,"종로구", "중구", "중랑구"};

		List<String[]>[] list = new ArrayList[25];
//		for (int i = 0; i < 25; i++) {
//			list[i] = new ArrayList<String[]>();
//			list[i].add(new String[] {"역삼동","개포동","삼성동"});
//		}
		

	}

	public static String addrToCoord(String addr) {
		String url = "https://dapi.kakao.com/v2/local/search/keyword.json?page=2&size=15&sort=accuracy&query=" + addr;

		String json = "";
		try {
			json = getJSONData(url);
		} catch (Exception e) {

			e.printStackTrace();

		}
		return json;
	}

	public static String coordToAddr(String x, String y) {

		String url = "https://dapi.kakao.com/v2/local/geo/coord2address.json?x=" + x + "&y=" + y + "&input_coord=WGS84";
		String json = "";
		try {
			json = getJSONData(url);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return json;
	}

	private static String getJSONData(String apiUrl) throws Exception {
		String jsonString = new String();
		String buf;
		String apikey = "e554b05582e168ef18ac59afb454e8f9"; // apikey

		URL url = new URL(apiUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		String auth = "KakaoAK " + apikey;
		conn.setRequestMethod("GET");
		conn.setRequestProperty("X-Requested-With", "curl");
		conn.setRequestProperty("Authorization", auth);

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		while ((buf = br.readLine()) != null) {
			jsonString += buf;
		}
		return jsonString;
	}
}
