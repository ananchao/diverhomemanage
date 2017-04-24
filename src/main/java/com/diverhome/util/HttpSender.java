package com.diverhome.util;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * ***************************************************************  
 * <p>Filename:    HttpSender.java
 * <p>Description: http接口发送器
 * <p>
 * <p>Copyright:   Copyright (c)2015  
 * <p>Company:     njits  
 * <p>Create at:   2015-6-25 下午11:24:07
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2015-6-25     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
public class HttpSender {
	public interface ResultListener {


        public void onConnectionPoolTimeoutError();

    }

    private static Logger logger = LoggerFactory.getLogger(HttpSender.class);

    //表示请求器是否已经做了初始化工作
    private boolean hasInit = false;

    //响应超时时间，默认15秒
    private int socketTimeout = 15000;

    //连接超时时间，默认5秒
    private int connectTimeout = 5000;

    //请求器的配置
    private RequestConfig requestConfig;

    //HTTP请求器
    private CloseableHttpClient httpClient;

    public HttpSender() throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, NoSuchProviderException {
        init();
    }

    /**
     * 初始化http连接
     * @Title: init
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws NoSuchProviderException 
     * @return void
     * @throws
     */
    private void init() {

        httpClient = HttpClients.custom().build();

        //根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();

        hasInit = true;
    }

    /**
     * 通过Https往API post xml数据
     *
     * @param url    API地址
     * @param xmlObj 要提交的XML数据对象
     * @return API回包的实际数据
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws NoSuchProviderException 
     */
	public String sendPost(String url, Object jsonObj)
			throws UnrecoverableKeyException, KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException, IOException,
			NoSuchProviderException {

		if (!hasInit) {
			init();
		}

        String result = null;

        HttpPost httpPost = new HttpPost(url);

        //将要提交给API的数据对象转换成json格式数据Post给API
        Gson gson = new Gson();
        String postDataJson = gson.toJson(jsonObj);

        logger.info("API，POST过去的数据是：");
        logger.info(postDataJson);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataJson, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        //设置请求器的配置
        httpPost.setConfig(requestConfig);

        logger.info("executing request" + httpPost.getRequestLine());

        try {
        	long time1 = System.currentTimeMillis();
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            logger.info("Saas:" + url + "执行效率:" + (System.currentTimeMillis() - time1));
            result = EntityUtils.toString(entity, "UTF-8");
            logger.info("API，响应的数据是：" + result);
        } catch (ConnectionPoolTimeoutException e) {
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)", e);
            throw e;

        } catch (ConnectTimeoutException e) {
        	logger.error("http get throw ConnectTimeoutException", e);
        	throw e;

        } catch (SocketTimeoutException e) {
        	logger.error("http get throw SocketTimeoutException", e);
        	throw e;
        } finally {
        	httpPost.abort();
        }

        return result;
    }
    
    /**
     * 通过Https往API get xml数据
     *
     * @param url    API地址
     * @param xmlObj 要提交的XML数据对象
     * @return API回包的实际数据
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws NoSuchProviderException 
     */
    public String sendGet(String url)
    		throws UnrecoverableKeyException, KeyManagementException,
    		NoSuchAlgorithmException, KeyStoreException, IOException,
    		NoSuchProviderException {
    	if (!hasInit) {
            init();
        }

        String result = null;

        logger.info("API，GET的URL是：");
        logger.info(url);

        HttpGet httpGet = new HttpGet(url);
        //设置请求器的配置
        httpGet.setConfig(requestConfig);

        logger.info("executing request" + httpGet.getRequestLine());

        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            logger.info("API，响应的数据是：" + result);
        } catch (ConnectionPoolTimeoutException e) {
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)", e);
            throw e;

        } catch (ConnectTimeoutException e) {
        	logger.error("http get throw ConnectTimeoutException", e);
        	throw e;

        } catch (SocketTimeoutException e) {
        	logger.error("http get throw SocketTimeoutException", e);
        	throw e;
        } finally {
        	httpGet.abort();
        }

        return result;
    }

    /**
     * 设置连接超时时间
     *
     * @param socketTimeout 连接时长，默认10秒
     */
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        resetRequestConfig();
    }

    /**
     * 设置传输超时时间
     *
     * @param connectTimeout 传输时长，默认30秒
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        resetRequestConfig();
    }

    private void resetRequestConfig(){
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    }
}
