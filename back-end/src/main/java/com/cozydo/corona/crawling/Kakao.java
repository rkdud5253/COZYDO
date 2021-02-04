package com.cozydo.corona.crawling;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Kakao {
	private String lon;
	private String lat;
	private String city;

	public Kakao(String lon, String lat) {
		this.lon = lon;
		this.lat = lat;
		coordToAddr(lon, lat);
	}

	public String getSi() {
		return city;
	}

	public void setSi(String si) {
		this.city = si;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	private void coordToAddr(String x, String y) {
		String url = "https://dapi.kakao.com/v2/local/geo/coord2address.json?x=" + x + "&y=" + y + "&input_coord=WGS84";
		try {
			getJSONData(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getJSONData(String apiUrl) throws Exception {
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
		buf = br.readLine();
		jsonString += buf;

		JSONParser paser = new JSONParser();
		JSONObject J = (JSONObject) paser.parse(jsonString);
		JSONObject meta = (JSONObject) J.get("meta");
		JSONArray data = (JSONArray) J.get("documents");
		long size = (long) meta.get("total_count");
		if (size > 0) {
			JSONObject jsonX = (JSONObject) data.get(0);
			JSONObject jsons = (JSONObject) jsonX.get("address");
			String s = (String) jsons.get("region_1depth_name");
			this.city = (String) jsons.get("region_1depth_name");
		} else
			this.city = null;
	}
}
