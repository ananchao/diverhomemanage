package com.diverhome.wechat.message.resp;

/**
 * 回复语音消息
 * 
 * @author anchao
 * 
 */
public class VoiceMessage extends BaseMessage {
	// 语音消息媒体id
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}
