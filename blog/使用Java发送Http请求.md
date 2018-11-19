由于Java原生API使用不便，考虑引入Apache HttpClient完成请求调用

- 组装参数

  ```java
  private static String getParamUrl(String url, Map<String, String> parameters) {
      StringBuilder pstr = new StringBuilder();
      for (Entry<String, String> p : parameters.entrySet()) {
          pstr.append("&").append(p.getKey() + "=" + p.getValue());
      }
      if (pstr.length() > 0) {
          pstr.replace(0, 1, "?");
      }
      return url + pstr.toString();
  }
  ```

- GET请求

  带参数，使用getParamUrl用来组装url和参数并返回url

  ```java
  public static String sendGet(String url, Map<String, String> parameters) throws IOException {
      HttpGet httpGet = new HttpGet(getParamUrl(url, parameters));
      try (CloseableHttpClient httpclient = HttpClientBuilder.create().build();
              CloseableHttpResponse response = httpclient.execute(httpGet)) {
          HttpEntity httpEntity = response.getEntity();
          return EntityUtils.toString(httpEntity);
      }
  }
  ```

- POST请求

  带参数和文件

  ```java
  public static String sendPost(String url, Map<String, String> parameters, Map<String, File> files) throws IOException {
      MultipartEntityBuilder paramsBuilder = MultipartEntityBuilder.create();
      for (Entry<String, String> entry : parameters.entrySet()) {
          paramsBuilder.addTextBody(entry.getKey(), entry.getValue());
      }
      for (Entry<String, File> entry : files.entrySet()) {
          paramsBuilder.addBinaryBody(entry.getKey(), entry.getValue());
      }
      
      HttpPost httppost = new HttpPost(url);
      httppost.setEntity(paramsBuilder.build());
      
      try (CloseableHttpClient httpclient = HttpClientBuilder.create().build();
              CloseableHttpResponse response = httpclient.execute(httppost)) {
          HttpEntity httpEntity = response.getEntity();
          return EntityUtils.toString(httpEntity);
      }
  }
  ```


