package com.ssafy.crawling.trip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ssafy.crawling.home.AptDaoImpl;
import com.ssafy.crawling.home.AptDealDto;
import com.ssafy.crawling.home.AptInfoDto;

public class AttractionMain {

	private static final String dataApiUrl = "https://apis.data.go.kr/B551011/KorService1";
//	private static final String dataApiKey = "q4OYsWA3c%2BOzkhXDClVeEW%2BzS5%2FGLnfe3FP%2FvgZ9JWBjLVKaUPr1Y2Dni8Qds%2BqNTZNWLzpyWslVdKn7GE6Ygw%3D%3D"; // 계희쌤키!!
	private static final String dataApiKey = "XqzbfhNXUSSyTQ0%2FK0PlrPpOhi%2BnZqAp3LP7guL1Djh9cYxsubBJJpWMmZ8EttAYZneUdg9GfBAdj3wzglcR7g%3D%3D"; // 계희쌤키!!

//	공통 파라미터
	private static final String numOfRows = "10000";
	private static final String pageNo = "1";
	private static final String MobileOS = "ETC";
	private static final String MobileApp = "AppTest";
	private static final String _type = "xml";

//	지역코드 기반 관광지 정보
//	https://apis.data.go.kr/B551011/KorService1/areaBasedList1
//	?serviceKey=serviceKey&numOfRows=1000&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=12&areaCode=39

//	서울(1), 인천(2), 대전(3), 대구(4), 광주(5), 부산(6), 울산(7), 세종특별자치시(8), 
//	경기도(31), 강원특별자치도(32), 충청북도(33), 충청남도(34), 경상북도(35), 경상남도(36), 전북특별자치도(37), 전라남도(38), 제주도(39)
//	private static final int[] areaCodes = { 1, 2, 3, 4, 5, 6, 7, 8, 31, 32, 33, 34, 35, 36, 37, 38, 39 };
	private static final int[] areaCodes = { 39 };
//	관광지(12), 문화시설(14), 축제공연행사(15), 여행코스(25), 레포츠(28), 숙박(32), 쇼핑(38), 음식점(39)
	private static final int[] contentTypeIds = { 12, 14, 15, 25, 28, 32, 38, 39 };
//	private static final int[] contentTypeIds = { 12, 14, 15, 25 };
//	private static final int[] contentTypeIds = { 28, 32, 38, 39 };
//	private static final int[] contentTypeIds = { 25 };

	private static final String areaApiUrl = dataApiUrl + "/areaBasedList1";
	private static final String listYN = "Y";
	private static final String arrange = "A";
	private static final String contentTypeId = "12";
	private static final String areaCode = "1";

//	관광지 상세 정보
//	https://apis.data.go.kr/B551011/KorService1/detailCommon1
//	?serviceKey=serviceKey&MobileOS=ETC&MobileApp=AppTest&_type=json&contentId=126508&contentTypeId=12&defaultYN=Y&firstImageYN=N&areacodeYN=N&catcodeYN=N&addrinfoYN=N&mapinfoYN=N&overviewYN=Y&numOfRows=10&pageNo=1
	private static final String detailApiUrl = dataApiUrl + "/detailCommon1";
	private static final String contentId = "126508";
	private static final String defaultYN = "Y";
	private static final String firstImageYN = "N";
	private static final String areacodeYN = "N";
	private static final String catcodeYN = "N";
	private static final String addrinfoYN = "N";
	private static final String mapinfoYN = "N";
	private static final String overviewYN = "Y";

	public static void main(String[] args) throws IOException {

		Random random = new Random();
		int[] randomTime = { 1500, 3500, 1080, 2200, 2800, 3000, 1900, 1300, 1000, 2500 };
		
		List<AttractionDto> attractionList = new ArrayList<>();

		String areaDataUrl = new StringBuilder(areaApiUrl).append("?serviceKey=").append(dataApiKey).append("&pageNo=")
				.append(URLEncoder.encode(pageNo, "UTF-8")).append("&numOfRows=")
				.append(URLEncoder.encode(numOfRows, "UTF-8")).append("&MobileOS=")
				.append(URLEncoder.encode(MobileOS, "UTF-8")).append("&MobileApp=")
				.append(URLEncoder.encode(MobileApp, "UTF-8")).append("&_type=")
				.append(URLEncoder.encode(_type, "UTF-8")).append("&listYN=").append(URLEncoder.encode(listYN, "UTF-8"))
				.append("&arrange=").append(URLEncoder.encode(arrange, "UTF-8")).toString();

		for (int t = 0; t < contentTypeIds.length; t++) {
			for (int a = 0; a < areaCodes.length; a++) {
				
				attractionList.clear();
				
				/////////////////////////////////// 지역, contentTypeId 별 관광지 정보
				/////////////////////////////////// ///////////////////////////////////
				String areaUrl = areaDataUrl + "&contentTypeId=" + contentTypeIds[t] + "&areaCode=" + areaCodes[a];
				URL dataAreaUrl = new URL(areaUrl);
				HttpURLConnection dataAreaConn = (HttpURLConnection) dataAreaUrl.openConnection();
				dataAreaConn.setRequestMethod("GET");
				dataAreaConn.setRequestProperty("Content-type", "application/json");

				BufferedReader dataAreaIn;
				if (dataAreaConn.getResponseCode() >= 200 && dataAreaConn.getResponseCode() <= 300) {
					dataAreaIn = new BufferedReader(new InputStreamReader(dataAreaConn.getInputStream()));
				} else {
					dataAreaIn = new BufferedReader(new InputStreamReader(dataAreaConn.getErrorStream()));
				}
				StringBuilder dataAreaSb = new StringBuilder();
				String dataAreaLine;
				while ((dataAreaLine = dataAreaIn.readLine()) != null) {
					dataAreaSb.append(dataAreaLine);
				}
				dataAreaIn.close();
				dataAreaConn.disconnect();
				
//				System.out.println(dataAreaSb);

				try {
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();

					Document dataAreaDocument = builder.parse(new InputSource(new StringReader(dataAreaSb.toString())));
					NodeList dataAreaNodeList = dataAreaDocument.getElementsByTagName("item");
					System.out.println("attraction count : " + dataAreaNodeList.getLength());
					for (int i = 0; i < dataAreaNodeList.getLength(); i++) {

						AttractionDto attractionDto = new AttractionDto();

						Node dataAreaNode = dataAreaNodeList.item(i);// 첫번째 element 얻기  
						if (dataAreaNode.getNodeType() == Node.ELEMENT_NODE) {

							Element dataAreaElement = (Element) dataAreaNode;
							attractionDto.setContentId(getValueByTagName(dataAreaElement, "contentid"));
							attractionDto.setTitle(getValueByTagName(dataAreaElement, "title"));
							attractionDto.setContentTypeId(getValueByTagName(dataAreaElement, "contenttypeid"));
							attractionDto.setAreaCode(getValueByTagName(dataAreaElement, "areacode"));
							String gugun = getValueByTagName(dataAreaElement, "sigungucode");
							attractionDto.setSiGunGuCode((gugun != null && gugun.trim().length() != 0) ? (gugun.equals("99") ? null : gugun) : null);
							attractionDto.setFirstImage1(getValueByTagName(dataAreaElement, "firstimage"));
							attractionDto.setFirstImage2(getValueByTagName(dataAreaElement, "firstimage2"));
							attractionDto.setMapLevel(nullToFive(getValueByTagName(dataAreaElement, "mlevel")));
							attractionDto.setLatitude(getValueByTagName(dataAreaElement, "mapy"));
							attractionDto.setLongitude(getValueByTagName(dataAreaElement, "mapx"));
							String tel = getValueByTagName(dataAreaElement, "tel");
							attractionDto.setTel(tel.length() <= 20 ? tel : "000-0000-0000");
							attractionDto.setAddr1(getValueByTagName(dataAreaElement, "addr1"));
							attractionDto.setAddr2(getValueByTagName(dataAreaElement, "addr2"));

							/////////////////////////////////// 관광지 상세 정보
							/////////////////////////////////// ///////////////////////////////////
							String detailDataUrl = new StringBuilder(detailApiUrl).append("?serviceKey=")
									.append(dataApiKey).append("&pageNo=").append(URLEncoder.encode(pageNo, "UTF-8"))
									.append("&numOfRows=").append(URLEncoder.encode(numOfRows, "UTF-8"))
									.append("&MobileOS=").append(URLEncoder.encode(MobileOS, "UTF-8"))
									.append("&MobileApp=").append(URLEncoder.encode(MobileApp, "UTF-8"))
									.append("&_type=").append(URLEncoder.encode(_type, "UTF-8")).append("&defaultYN=")
									.append(URLEncoder.encode(defaultYN, "UTF-8")).append("&firstImageYN=")
									.append(URLEncoder.encode(firstImageYN, "UTF-8")).append("&areacodeYN=")
									.append(URLEncoder.encode(areacodeYN, "UTF-8")).append("&catcodeYN=")
									.append(URLEncoder.encode(catcodeYN, "UTF-8")).append("&addrinfoYN=")
									.append(URLEncoder.encode(addrinfoYN, "UTF-8")).append("&mapinfoYN=")
									.append(URLEncoder.encode(mapinfoYN, "UTF-8")).append("&overviewYN=")
									.append(URLEncoder.encode(overviewYN, "UTF-8")).toString();
							String detailUrl = detailDataUrl + "&contentId=" + attractionDto.getContentId()
									+ "&contentTypeId=" + attractionDto.getContentTypeId();
							URL dataDetailUrl = new URL(detailUrl);
							HttpURLConnection dataDetailConn = (HttpURLConnection) dataDetailUrl.openConnection();
							dataDetailConn.setRequestMethod("GET");
							dataDetailConn.setRequestProperty("Content-type", "application/json");

							BufferedReader dataDetailIn;
							if (dataDetailConn.getResponseCode() >= 200 && dataDetailConn.getResponseCode() <= 300) {
								dataDetailIn = new BufferedReader(
										new InputStreamReader(dataDetailConn.getInputStream()));
							} else {
								dataDetailIn = new BufferedReader(
										new InputStreamReader(dataDetailConn.getErrorStream()));
							}
							StringBuilder dataDetailSb = new StringBuilder();
							String dataDetailLine;
							while ((dataDetailLine = dataDetailIn.readLine()) != null) {
								dataDetailSb.append(dataDetailLine);
							}
							dataDetailIn.close();
							dataDetailConn.disconnect();

//							System.out.println(dataDetailSb);

							Document dataDetailDocument = builder
									.parse(new InputSource(new StringReader(dataDetailSb.toString())));
							NodeList dataDetailNodeList = dataDetailDocument.getElementsByTagName("item");
							Node dataDetailNode = dataDetailNodeList.item(0);// 첫번째 element 얻기  
//							System.out.println(dataDetailNodeList.getLength() + "      " + dataDetailNode);
							if (dataDetailNode!= null && dataDetailNode.getNodeType() == Node.ELEMENT_NODE) {
//								System.out.println("데이터 있다!!!!!!!");
								Element dataDetailElement = (Element) dataDetailNode;
								attractionDto.setHomePage(getValueByTagName(dataDetailElement, "homepage"));
								attractionDto.setOverView(getValueByTagName(dataDetailElement, "overview"));
							}
						}
						System.out.println(i);
//						System.out.println(attractionDto);
						attractionList.add(attractionDto);
					}
					
					System.out.println("areaCode: " + areaCodes[a] + "\tcontentTypeId: " + contentTypeIds[t] + "\tattraction count: " + attractionList.size());
					AttractionDaoImpl.getAttractionDao().registAttraction(attractionList);
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("수고하셨습니다!!!");
	}

	private static String getValueByTagName(Element element, String tagName) {
		return element.getElementsByTagName(tagName).item(0) != null
				? element.getElementsByTagName(tagName).item(0).getTextContent()
				: " ";
	}

	private static int nullToZero(String str) {
		if (isNumber(str)) {
			return Integer.parseInt(str);
		}
		return 0;
	}
	
	private static int nullToOne(String str) {
		if (isNumber(str)) {
			return Integer.parseInt(str);
		}
		return 1;
	}
	
	private static int nullToFive(String str) {
		if (isNumber(str)) {
			return Integer.parseInt(str);
		}
		return 5;
	}

	private static boolean isNumber(String str) {
		return (str != null && str.trim().length() != 0) ? str.matches("[+-]?\\d*(\\.\\d+)?") : false;
//		return str.chars().allMatch(Character::isDigit);
	}
}
