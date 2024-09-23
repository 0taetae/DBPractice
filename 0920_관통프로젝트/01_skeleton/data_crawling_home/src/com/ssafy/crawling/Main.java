package com.ssafy.crawling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"https://apis.data.go.kr/1613000/RTMSDataSvcAptTradeDev/getRTMSDataSvcAptTradeDev");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey=", "UTF-8")
				+ "------------------------- 공공 데이터 서비스 키 입력 -------------------------");
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("30", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode("11110", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode("202408", "UTF-8"));
		System.out.println(urlBuilder.toString());
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(new InputSource(new StringReader(sb.toString())));
			NodeList nodeList = document.getElementsByTagName("item");
			System.out.println("apt count : " + nodeList.getLength());
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);// 첫번째 element 얻기  
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) node;
					String aptName = eElement.getElementsByTagName("aptNm").item(0).getTextContent();
					System.out.println("-------------------------- " + aptName + " --------------------------");
					System.out.println("건축년도: " + eElement.getElementsByTagName("buildYear").item(0).getTextContent());
					System.out.println("법정동: " + eElement.getElementsByTagName("umdNm").item(0).getTextContent());
					System.out.println("층: " + eElement.getElementsByTagName("floor").item(0).getTextContent());
					System.out.println("매매년: " + eElement.getElementsByTagName("dealYear").item(0).getTextContent());
					System.out.println("매매월: " + eElement.getElementsByTagName("dealMonth").item(0).getTextContent());
					System.out.println("매매일: " + eElement.getElementsByTagName("dealDay").item(0).getTextContent());
					System.out.println("전용면적: " + eElement.getElementsByTagName("excluUseAr").item(0).getTextContent());
					System.out.println("거래금액: " + eElement.getElementsByTagName("dealAmount").item(0).getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

}
