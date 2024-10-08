package com.wdbyte.downbit.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginContext;

/**
 * <p>
 * 网络请求操作工具类
 *
 * @author niujinpeng
 * @Date 2020/7/19 21:25
 */
public class HttpUtls {

    /**
     * 获取 HTTP 链接
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getHttpUrlConnection(String url) throws IOException {
        URL httpUrl = new URL(url);
        HttpURLConnection httpConnection = (HttpURLConnection)httpUrl.openConnection();
        httpConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        return httpConnection;
    }

    /**
     * 获取 HTTP 链接
     *
     * @param url
     * @param start
     * @param end
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getHttpUrlConnection(String url, long start, Long end) throws IOException {
        HttpURLConnection httpUrlConnection = getHttpUrlConnection(url);
        LogUtils.debug("此线程下载内容区间 {}-{}", start, end);
        if (end != null) {
            httpUrlConnection.setRequestProperty("Range", "bytes=" + start + "-" + end);
        } else {
            httpUrlConnection.setRequestProperty("Range", "bytes=" + start + "-");
        }
        Map<String, List<String>> headerFields = httpUrlConnection.getHeaderFields();
        for (String s : headerFields.keySet()) {
            LogUtils.debug("此线程相应头{}:{}", s, headerFields.get(s));
        }
        return httpUrlConnection;
    }

    /**
     * 获取网络文件大小 bytes
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static long getHttpFileContentLength(String url) throws IOException {
        HttpURLConnection httpUrlConnection = getHttpUrlConnection(url);
        httpUrlConnection.setRequestMethod("HEAD");
        int contentLength = httpUrlConnection.getContentLength();
        httpUrlConnection.disconnect();
        return contentLength;
    }

    /**
     * 获取网络文件 Etag
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String getHttpFileEtag(String url) throws IOException {
        HttpURLConnection httpUrlConnection = getHttpUrlConnection(url);
        Map<String, List<String>> headerFields = httpUrlConnection.getHeaderFields();
        List<String> eTagList = headerFields.get("ETag");
        httpUrlConnection.disconnect();
        return eTagList.get(0);
    }

    /**
     * 获取网络文件名
     *
     * @param url
     * @return
     */
    public static String getHttpFileName(String url) {
        int indexOf = url.lastIndexOf("/");
        return url.substring(indexOf + 1);
    }
}
