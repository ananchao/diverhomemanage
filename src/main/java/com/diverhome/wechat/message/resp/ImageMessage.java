package com.diverhome.wechat.message.resp;

/**
 * 回复图片消息
 * @author anchao
 *
 */
public class ImageMessage extends BaseMessage {
	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
