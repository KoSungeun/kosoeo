package com.study.spring.gm;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "message-lsit")
public class GuestMessageXMLList {
	@XmlElement(name = "message")
	private List<GuestMessage> message;

	public GuestMessageXMLList(List<GuestMessage> message) {
		this.message = message;
	}

	public GuestMessageXMLList() {
	}

	public List<GuestMessage> getMessage() {
		return message;
	}

	public void setMessage(List<GuestMessage> message) {
		this.message = message;
	}
	
	
	

}
