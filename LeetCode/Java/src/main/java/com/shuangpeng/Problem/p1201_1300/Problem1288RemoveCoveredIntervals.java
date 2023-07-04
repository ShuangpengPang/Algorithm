package com.shuangpeng.Problem.p1201_1300;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1288RemoveCoveredIntervals（删除被覆盖区间）
 * @date 2023/6/25 11:35 AM
 */
public class Problem1288RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int n = intervals.length, max = -1;
        for (int[] arr : intervals) {
            if (arr[1] <= max) {
                n--;
            } else {
                max = arr[1];
            }
        }
        return n;
    }


    public static void main(String[] args) throws IOException {
//        function1();
//        function2();
        function3();
    }

    private static void function1() {
        try {
            String url = "http://124.71.86.184:7084/dc/buginsight/image?env=123";
            String filePath = "/Users/shuangpeng/Downloads/aaa.jpeg";
            String scene = "default";
            String output = "json";
            String boundary = Long.toString(System.currentTimeMillis());

            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);

            // 创建连接
            URL requestURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Charset", "utf-8");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

            // 写入文件数据
            outputStream.writeBytes("--" + boundary + "\r\n");
            outputStream.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
            outputStream.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.writeBytes("\r\n");
            outputStream.flush();

            // 写入其他参数
            outputStream.writeBytes("--" + boundary + "\r\n");
            outputStream.writeBytes("Content-Disposition: form-data; name=\"scene\"\r\n\r\n");
            outputStream.writeBytes(scene + "\r\n");
            outputStream.writeBytes("--" + boundary + "\r\n");
            outputStream.writeBytes("Content-Disposition: form-data; name=\"output\"\r\n\r\n");
            outputStream.writeBytes(output + "\r\n");
            outputStream.writeBytes("--" + boundary + "--\r\n");
            outputStream.writeBytes("\r\n");
            outputStream.flush();
            outputStream.close();

            // 获取响应
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                    response.append("\n");
                }
                reader.close();
                System.out.println(response.toString());
            } else {
                System.out.println("Request failed: " + responseCode + " " + connection.getResponseMessage());
            }

            connection.disconnect();
        } catch (Exception e) {
        }
    }


    public static void function2() {
        HttpURLConnection connection = null;
        try {
            String urlString = "http://124.71.86.184:7084/dc/buginsight/image";
            URL url = new URL("http://124.71.86.184:7084/dc/buginsight/image?env=sita"); // 替换为实际的上传接口URL
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data"); // 替换为实际的图片类型
            File imageFile = new File("/Users/shuangpeng/Downloads/aaa.jpeg"); // 替换为实际的图片文件路径
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            OutputStream outputStream = connection.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            fileInputStream.close();
            outputStream.close();
            int responseCode = connection.getResponseCode();
            String message = connection.getResponseMessage();
            int i = 1;
        } catch (Exception e) {
            int i = 1;
        } finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception e) {
                }
            }
        }
    }

    private static final void function3() {
        String url = "http://124.71.86.184:7084/dc/buginsight/image?env=123";
        String filePath = "/Users/shuangpeng/Downloads/aaa.jpeg";

        try {
            URL requestUrl = new URL(url);
            File file = new File(filePath);

            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data");

            MultipartUtility multipart = new MultipartUtility(connection);
            multipart.addFormField("scene", "default");
            multipart.addFormField("output", "json");
            multipart.addFilePart("file", file);
            multipart.addLast();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String responseBody = multipart.getResponse();
                System.out.println(responseBody);
            } else {
                System.out.println("Request failed with error code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MultipartUtility {
    private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection connection;
    private OutputStream outputStream;

    public MultipartUtility(HttpURLConnection connection) throws IOException {
        this.connection = connection;
        boundary = "===" + System.currentTimeMillis() + "===";
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        outputStream = connection.getOutputStream();
    }

    public void addFormField(String name, String value) throws IOException {
        writeBoundary();
        outputStream.write(("Content-Disposition: form-data; name=\"" + name + "\"").getBytes());
        outputStream.write(LINE_FEED.getBytes());
        outputStream.write(LINE_FEED.getBytes());
        outputStream.write(value.getBytes());
        outputStream.write(LINE_FEED.getBytes());
    }

    public void addFilePart(String fieldName, File uploadFile) throws IOException {
        String fileName = uploadFile.getName();
        writeBoundary();
        outputStream.write(("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"").getBytes());
        outputStream.write(LINE_FEED.getBytes());
        outputStream.write(("Content-Type: " + Files.probeContentType(Paths.get(uploadFile.getAbsolutePath()))).getBytes());
//        outputStream.write(("Content-Type: application/octet-stream").getBytes());
        outputStream.write(LINE_FEED.getBytes());
        outputStream.write(LINE_FEED.getBytes());
        outputStream.write(Files.readAllBytes(uploadFile.toPath()));
        outputStream.write(LINE_FEED.getBytes());
    }

    public void addLast() throws IOException {
        outputStream.write((LINE_FEED + "--" + boundary + "--" + LINE_FEED).getBytes());
        outputStream.flush();
        outputStream.close();
    }

    public String getResponse() throws IOException {
        // Get Response
        StringBuilder response = new StringBuilder();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String line;
            while ((line = connection.getResponseMessage()) != null) {
                response.append(line);
            }
        }
        connection.disconnect();
        return response.toString();
    }

    private void writeBoundary() throws IOException {
        outputStream.write(("--" + boundary).getBytes());
        outputStream.write(LINE_FEED.getBytes());
    }
}
