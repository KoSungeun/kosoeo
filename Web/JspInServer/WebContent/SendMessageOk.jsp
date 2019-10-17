<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%!String ApiKey = "AIzaSyARw-h5IYnZPEbDCHcfgDcSFgrE4HkJRpA";
	String fcmURL = "https://fcm.googleapis.com/fcm/send";%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		String notiTitle = request.getParameter("notiTitle");
		String notiBody = request.getParameter("notiBody");
		String msg = request.getParameter("message");

		try {
			// 데이터베이스에 저장된 토큰을 가져와 ArrayList에 저장
			String deviceId1 = "cNdB5JA_C-0:APA91bGrgPy7-6EVd0V4nWQqjMu13QSDtGTs3Olgb-wM0Tr4acBwIbJOaTqvvtftHsr6giNKp6tA84RPb05rrt98wVojbklTQAXDu6EjeENE2zVjgBiRDNc5pjolI7wdsVum9Lk5Fwc7";
			JSONArray deviceId = new JSONArray();
			deviceId.add(deviceId1);

			URL url = new URL(fcmURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "key=" + ApiKey);
			conn.setRequestProperty("Content-Type", "application/json");

			JSONObject json = new JSONObject();

			JSONObject noti = new JSONObject();
			noti.put("title", notiTitle);
			noti.put("body", notiBody);

			JSONObject data = new JSONObject();
			data.put("message", msg);

			json.put("registration_ids", deviceId);
			json.put("notification", noti);
			json.put("data", data);
			//System.out.println(json.toString());

			try {
				OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				wr.write(json.toString());
				wr.flush();

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String output;
				System.out.println("Output from Server ... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			out.print(notiTitle + "<br>");
			out.print(notiBody + "<br>");
			out.print("Firebase Cloud Message 가 발송되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</body>
</html>