package com.cozydo.corona.crawling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;

import com.cozydo.dao.PlaceDao;
import com.cozydo.model.place.Place;

public class Excel {

//	public static void main(String[] args) {
//
//		// 반환용 리스트
//		// 9개의 칼럼
//		List<String> list = new ArrayList<String>();
//		BufferedReader br = null;
//		Set<String> set = new HashSet<String>();
//		try {
//			br = Files.newBufferedReader(Paths.get("C:\\Users\\multicampus\\Desktop\\prac.txt"));
//			// place 카테고리 만들기
//			FileWriter fw = new FileWriter("C:\\Users\\multicampus\\Desktop\\places.txt");
//			String line = br.readLine().trim();
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.write("상호명,상권업종중분류코드,상권업종중분류명,시군구명,법정동명,지번주소,도로명 주소,경도,위도");
//			bw.newLine();		
//			String line2 = "";
//			int cnt = 0;
//			
//			while ((line2 = br.readLine()) != null) {
//				String arr[] = line2.split(",");
//				for (int i = 0; i < arr.length; i++)
//					arr[i] = arr[i].trim();
//				if (지점명조사(arr[0], arr[1])) {
//					arr[0] += arr[1];
//				}
//				if (층조사(arr[8]))
//					arr[7] += " " + arr[8] + "층";
//				
//				String str = arr[0] + "," + arr[2] + "," + arr[3] + "," + arr[4] + "," + arr[5] + "," + arr[6] + ","
//						+ arr[7] + "," + arr[9] + "," + arr[10];
//				
//				cnt++;
//				//왜 안들어갈까 367464
//				bw.write(str);
//				bw.newLine();
//				if(cnt >= 367463)
//					System.out.println(str);
//				
//				set.add(arr[2] + arr[3]);
//			}
//
//			// category 테이블 만들기
//			fw = new FileWriter("C:\\Users\\multicampus\\Desktop\\category.txt");
//			bw = new BufferedWriter(fw);
//			bw.write("sub_category" + "," + "sub_category_name");
//			bw.newLine();
//
//			for (String tmp : set) {
//				bw.write(tmp.substring(0, 3) + "," + tmp.substring(3));
//				bw.newLine();
//			}
//			bw.close();
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (br != null) {
//					br.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	private static boolean 지점명조사(String name, String land) { // 상호명 지점명
//		if (name.substring(name.length() - 1).equals("점") || land.equals("null")) { // 끝자리가 점으로 끝나거나 지점명이 null일 경우
//			return false;
//		} else
//			return true;
//	}
//
//	private static boolean 층조사(String string) {
//		if (string.contains("null"))
//			return false;
//		else {
//			return true;
//		}
//	}
}
