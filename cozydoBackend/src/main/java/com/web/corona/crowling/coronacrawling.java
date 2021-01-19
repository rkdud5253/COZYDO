package com.web.corona.crowling;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class coronacrawling {

//	public Object arr() throws IOException {
//		String url = "http://ncov.mohw.go.kr/";
//		Document document = Jsoup.connect(url).get();// 해당 URL에 연결
//		Elements p_name = document.getElementsByClass("rss_detail"); // class이름으로 긁기
//		Elements p_price = document.getElementsByClass("catalog-price");
//		String url = "http://ncov.mohw.go.kr/";
//		Document document = Jsoup.connect(url).get();// 해당 URL에 연결
//		String img_alt = document.getElementsByClass("t_dp_n m_dp_n").select("img").attr("alt"); // class이름으로 긁기
//		corona corona = new corona();
//		corona.setDate(img_alt);
//		List<corona> list = new LinkedList();
//		list.add(new corona(alt));
////		System.out.println(p_name);
////		for (Element a : p_name)
////			list.add(a.text());
//		return list;
////			System.out.println(a.text()); /// 해당 태그내용 출력
//	}

	public static void main(String[] args) throws IOException{
		String url = "http://ncov.mohw.go.kr/";
		Document document = Jsoup.connect(url).get();// 해당 URL에 연결
		Elements p_name = document.getElementsByClass("rssm_graph").select("button"); // class이름으로 긁기
		Elements p_price = document.getElementsByClass("catalog-price");
		System.out.println(p_name.text());

	}
}
