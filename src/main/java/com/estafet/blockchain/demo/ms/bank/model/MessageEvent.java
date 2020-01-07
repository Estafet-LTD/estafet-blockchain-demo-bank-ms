package com.estafet.blockchain.demo.ms.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.estafet.demo.commons.lib.event.AbstractMessageEvent;

@Entity
@Table(name = "MESSAGE_EVENT")
public class MessageEvent extends AbstractMessageEvent {

	@Id
	@Column(name = "TOPIC_ID")
	private String topicId;

	@Column(name = "MESSAGE_REFERENCE", nullable = false)
	private String messageReference;
	
	@Version
	@Column(name = "VERSION")
	private Integer version;

	public String getTopicId() {
		return topicId;
	}

	public String getMessageReference() {
		return messageReference;
	}

	public MessageEvent setTopicId(String topicId) {
		this.topicId = topicId;
		return this;
	}

	public MessageEvent setMessageReference(String messageReference) {
		this.messageReference = messageReference;
		return this;
	}

}