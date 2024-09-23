package com.ssafy.crawling.home;

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

public class AptMain {

//	주소 >> 좌표 변환을 위한 kakao 정보
//	https://developers.kakao.com/docs/latest/ko/local/dev-guide#address-coord
	private static final String kakaoApiUrl = "https://dapi.kakao.com/v2/local/search/address.xml";
	private static final String kakaoApiKey = "---------------- Kakao REST API KEY ----------------";

//	아파트 정보를 얻기 위한 공공데이터 정보
	private static final String dataApiUrl = "https://apis.data.go.kr/1613000/RTMSDataSvcAptTradeDev/getRTMSDataSvcAptTradeDev";
	private static final String dataApiKey = "---------------- 공공데이터 API KEY ----------------";
	private static final String pageNo = "1";
	private static final String numOfRows = "2000";
	private static String LAWD_CD = "11110";
//  COMPLETED : 201111, 201112, 202407, 202406, 202405, 202404, 202403, 202402, 202401, 202408
//  COMPLETED : 201201, 021207, 201301, 201307, 201401, 201407, 201501, 201507, 201601, 201607 
//  COMPLETED : 201701, 201707, 201801, 201807, 201901, 201907, 202001, 202007, 202101, 202107, 202201, 202207, 202301, 
//  TODO: 
	private static String DEAL_YMD = "201501";

	public static void main(String[] args) throws IOException {

		Random random = new Random();
		int[] randomTime = { 750, 550, 808, 920, 380, 700, 690, 530, 1000, 450 };

		List<String> dongCodeList = AptDaoImpl.getAptDao().getDongCodeList();
		int cnt = dongCodeList.size();

		for (String lawdCd : dongCodeList) {
//			LAWD_CD = "11290";
			LAWD_CD = lawdCd;
			System.out.println("현재 작업중 지역 : " + LAWD_CD + ", " + cnt-- + "개 작업 남음!!!");

			boolean[] infoDbExists = new boolean[1000000];

			List<String> aptSeqList = AptDaoImpl.getAptDao().getAptSeqList(LAWD_CD);
			for (String seq : aptSeqList)
				infoDbExists[Integer.parseInt(seq.substring(6))] = true;

			List<AptInfoDto> aptInfoList = new ArrayList<>();
			List<AptDealDto> aptDealList = new ArrayList<>();

			StringBuilder dataUrlBuilder = new StringBuilder(dataApiUrl).append("?serviceKey=").append(dataApiKey)
					.append("&pageNo=").append(URLEncoder.encode(pageNo, "UTF-8")).append("&numOfRows=")
					.append(URLEncoder.encode(numOfRows, "UTF-8")).append("&LAWD_CD=")
					.append(URLEncoder.encode(LAWD_CD, "UTF-8")).append("&DEAL_YMD=")
					.append(URLEncoder.encode(DEAL_YMD, "UTF-8"));

			URL dataUrl = new URL(dataUrlBuilder.toString());
			HttpURLConnection dataConn = (HttpURLConnection) dataUrl.openConnection();
			dataConn.setRequestMethod("GET");
			dataConn.setRequestProperty("Content-type", "application/json");

			BufferedReader dataIn;
			if (dataConn.getResponseCode() >= 200 && dataConn.getResponseCode() <= 300) {
				dataIn = new BufferedReader(new InputStreamReader(dataConn.getInputStream()));
			} else {
				dataIn = new BufferedReader(new InputStreamReader(dataConn.getErrorStream()));
			}
			StringBuilder dataSb = new StringBuilder();
			String dataLine;
			while ((dataLine = dataIn.readLine()) != null) {
				dataSb.append(dataLine);
			}
			dataIn.close();
			dataConn.disconnect();

			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();

				Document dataDocument = builder.parse(new InputSource(new StringReader(dataSb.toString())));
				NodeList dataNodeList = dataDocument.getElementsByTagName("item");
				System.out.println("apt count : " + dataNodeList.getLength());
				for (int i = 0; i < dataNodeList.getLength(); i++) {

					AptDealDto aptDealDto = new AptDealDto();

					Node dataNode = dataNodeList.item(i);// 첫번째 element 얻기  
					if (dataNode.getNodeType() == Node.ELEMENT_NODE) {

						Element dataElement = (Element) dataNode;
//					System.out.println("-------------------------------------");
//					System.out.println("단지 일련번호: " + getValueByTagName(dataElement, "aptSeq"));
//					System.out.println("법정동시구군코드: " + getValueByTagName(dataElement, "sggCd"));
//					System.out.println("법정동읍면동코드: " + getValueByTagName(dataElement, "umdCd"));
//					System.out.println("법정동: " + getValueByTagName(dataElement, "umdNm"));
//					System.out.println("지번: " + getValueByTagName(dataElement, "jibun"));
//					System.out.println("도로명시구군코드: " + getValueByTagName(dataElement, "roadNmSggCd"));
//					System.out.println("도로명: " + getValueByTagName(dataElement, "roadNm"));
//					System.out.println("도로명 번길: " + getValueByTagName(dataElement, "roadNmBonbun"));
//					System.out.println("단지명: " + getValueByTagName(dataElement, "aptNm"));
//					System.out.println("아파트 동: " + getValueByTagName(dataElement, "aptDong"));
//					System.out.println("층: " + getValueByTagName(dataElement, "floor"));
//					System.out.println("매매년: " + getValueByTagName(dataElement, "dealYear"));
//					System.out.println("매매월: " + getValueByTagName(dataElement, "dealMonth"));
//					System.out.println("매매일: " + getValueByTagName(dataElement, "dealDay"));
//					System.out.println("전용면적: " + getValueByTagName(dataElement, "excluUseAr"));
//					System.out.println("거래금액: " + getValueByTagName(dataElement, "dealAmount") + " 만원");
//					System.out.println("건축년도: " + getValueByTagName(dataElement, "buildYear"));

						aptDealDto.setAptSeq(getValueByTagName(dataElement, "aptSeq"));
						aptDealDto.setSggCd(getValueByTagName(dataElement, "sggCd"));
						aptDealDto.setUmdCd(getValueByTagName(dataElement, "umdCd"));
						aptDealDto.setUmdNm(getValueByTagName(dataElement, "umdNm"));
						aptDealDto.setJibun(getValueByTagName(dataElement, "jibun"));
						aptDealDto.setRoadNmSggCd(getValueByTagName(dataElement, "roadNmSggCd"));
						aptDealDto.setRoadNm(getValueByTagName(dataElement, "roadNm"));
						aptDealDto.setRoadNmBonbun(nullToZero(getValueByTagName(dataElement, "roadNmBonbun")));
						aptDealDto.setRoadNmBubun(nullToZero(getValueByTagName(dataElement, "roadNmBubun")));
						aptDealDto.setAptNm(getValueByTagName(dataElement, "aptNm"));
						aptDealDto.setAptDong(getValueByTagName(dataElement, "aptDong"));
						aptDealDto.setFloor(getValueByTagName(dataElement, "floor"));
						aptDealDto.setDealYear(nullToZero(getValueByTagName(dataElement, "dealYear")));
						aptDealDto.setDealMonth(nullToZero(getValueByTagName(dataElement, "dealMonth")));
						aptDealDto.setDealDay(nullToZero(getValueByTagName(dataElement, "dealDay")));
						aptDealDto.setExcluUseAr(Double.parseDouble(getValueByTagName(dataElement, "excluUseAr")));
						aptDealDto.setDealAmount(getValueByTagName(dataElement, "dealAmount"));
						aptDealDto.setBuildYear(nullToZero(getValueByTagName(dataElement, "buildYear")));

//					주소 >> 좌표 변환.
						String roadFullAddr = aptDealDto.getRoadNm() + " " + aptDealDto.getRoadNmBonbun()
								+ (aptDealDto.getRoadNmBubun() == 0 ? "" : "-" + aptDealDto.getRoadNmBubun());

						StringBuilder kakaoUrlBuilder = new StringBuilder(kakaoApiUrl).append("?query=")
								.append(URLEncoder.encode(roadFullAddr, "UTF-8"));

						URL kakaoUrl = new URL(kakaoUrlBuilder.toString());
						HttpURLConnection kakaoConn = (HttpURLConnection) kakaoUrl.openConnection();
						kakaoConn.setRequestProperty("Authorization", "KakaoAK " + kakaoApiKey);

						BufferedReader kakaoIn = null;
						kakaoIn = new BufferedReader(new InputStreamReader(kakaoConn.getInputStream(), "UTF-8"));
						StringBuilder kakaoSb = new StringBuilder();

						String kakaoLine;
						while ((kakaoLine = kakaoIn.readLine()) != null) {
							kakaoSb.append(kakaoLine);
						}

						kakaoIn.close();
						kakaoConn.disconnect();

						Document kakaoDocument = builder.parse(new InputSource(new StringReader(kakaoSb.toString())));
						NodeList kakaoNodeList = kakaoDocument.getElementsByTagName("documents");
						Node kakaoNode = kakaoNodeList.item(0);// 첫번째 element 얻기  
						if (kakaoNode != null && kakaoNode.getNodeType() == Node.ELEMENT_NODE) {
							Element kakaoElement = (Element) kakaoNode;
//						System.out.println("위도(latitude): " + getValueByTagName(kakaoElement, "y"));
//						System.out.println("경도(longitude): " + getValueByTagName(kakaoElement, "x"));
							aptDealDto.setLatitude(getValueByTagName(kakaoElement, "y"));
							aptDealDto.setLongitude(getValueByTagName(kakaoElement, "x"));
						}
					}
					
					int len = aptDealDto.getAptSeq().length();

//				아파트 실거래 상세자료 추가.
					if(len != 0)
						aptDealList.add(aptDealDto);

//				아파트 기본정보 추가.
					AptInfoDto aptInfoDto = aptDealDto;

//				boolean flag = true;
//				검색 결과내에서 중복 체크
//				for(AptInfoDto dto: aptInfoList) {
//					if(dto.getAptSeq().equals(aptInfoDto.getAptSeq())) {
//						System.out.println("추가못합1111.");
//						flag = false;
//						break;
//					}
//				}

//				기존 DB내에있는 아파트 정보 중복 체크
//				if(flag) {
//					for(String aptSeq: aptSeqList) {
//						if(aptSeq.equals(aptInfoDto.getAptSeq())) {
//							System.out.println("추가못합2222.");
//							flag = false;
//							break;
//						}
//					}
//				}
//				if (infoDbExists[Integer.parseInt(aptInfoDto.getAptSeq().substring(6))]) {
//					System.out.println("추가못합2222.");
//					flag = false;
//				}

					if(len != 0) {
						int seq = Integer.parseInt(aptInfoDto.getAptSeq().substring(6));
						if (!infoDbExists[seq]) {
							aptInfoList.add(aptInfoDto);
							infoDbExists[seq] = true;
						}
					}

				}
				System.out.println("info list : " + aptInfoList.size() + "\tdeal list : " + aptDealList.size());

				Thread.sleep(randomTime[random.nextInt(10)]);

//			아파트 기본정보 DB에 insert
				if ((aptInfoList.size() != 0))
					AptDaoImpl.getAptDao().registAptInfo(aptInfoList);

//			아파트 상세 실거래정보 DB에 insert
				if ((aptDealList.size() != 0))
					AptDaoImpl.getAptDao().registAptDeal(aptDealList);

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("수고하셨습니다!!!!");
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

	private static boolean isNumber(String str) {
		boolean flag = true;
		if (str != null) {
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c < 48 || c > 57) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

}
