package com.dwh.gyndowindback.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

public class OkHttpUtils {
    /**
     * 给定特定的url，参数封装成一个map,get请求
     *
     * @param url
     * @param params
     * @return 响应字符串
     */
    private static OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String getResponseWithParams(String url, Map<String, String> params) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        HttpUrl.Builder builder = httpUrl.newBuilder();
        if (params != null) {
            for (String key : params.keySet()) {
                builder.addQueryParameter(key, params.get(key));
            }
        }

        Request request = new Request.Builder()
                .url(builder.build())
                .build();

        try {
            String res = client.newCall(request).execute().body().string();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getResponseWithParamsAndHeaders(String url, Map<String, String> params, Map<String, String> headers) {
        if (params != null) {
            StringBuffer urlSb = new StringBuffer(url);
            urlSb.append('?');
            for (String key : params.keySet()) {
                urlSb.append(key).append('=').append(params.get(key)).append('&');
            }
            urlSb.deleteCharAt(urlSb.length() - 1);
            url = urlSb.toString();
        }
        System.out.println(url);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (null != headers)
            headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        Request request = requestBuilder.build();

        try {
            String res = client.newCall(request).execute().body().string();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求 参数是map格式
     *
     * @param url
     * @param params
     * @return
     */
    public static String postResponseWithParamsInMap(String url, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }

        RequestBody body = builder.build();

        Request request = new Request.Builder().url(url).post(body).build();

        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求 参数是map格式
     *
     * @param url
     * @param params
     * @return
     */
    public static String postResponseWithParamsAndHeaders(String url, Map<String, String> params, Map<String, String> headers) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        RequestBody body = builder.build();
        Request.Builder requestBuilder = new Request.Builder().url(url).post(body);
        if (null != headers)
            headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        Request request = requestBuilder.build();
        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 参数是json 格式
     *
     * @param url
     * @param json
     * @return
     */
    public static String postResponseWithParamsInJsonAndHeaders(String url, String json, Map<String, String> headers) {
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder requestBuilder = new Request.Builder().url(url).post(body);
        if (null != headers)
            headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        Request request = requestBuilder.build();
        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * post请求 参数是map格式
     *
     * @param url
     * @param params
     * @return
     */
    public static String postResponseForm(String url, Map<String, Object> params) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (params != null) {
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, params.get(key).toString());
            }
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求 参数是map格式
     *
     * @param url
     * @param params
     * @return
     */
    public static String postResponseFormResHeader(String url, Map<String, String> params, String headerName) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (params != null) {
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, params.get(key));
            }
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            String res = response.header(headerName);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 参数是json 格式
     *
     * @param url
     * @param json
     * @return
     */
    public static String postResponseWithParamsInJson(String url, String json) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 参数是json 格式
     *
     * @param url
     * @param json
     * @return
     */
    public static String postResponseWithParamsInJsonResHeader(String url, String json, String headerName) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            String res = response.header(headerName);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
