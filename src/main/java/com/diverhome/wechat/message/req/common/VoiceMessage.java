package com.diverhome.wechat.message.req.common;


/**
 * 语音消息
 * 
 * @author anchao
 * 
 */
public class VoiceMessage extends CommonBaseMessage {
	// 语音消息媒体id
	private String MediaId;
	// 语音格式
	private String Format;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

}
