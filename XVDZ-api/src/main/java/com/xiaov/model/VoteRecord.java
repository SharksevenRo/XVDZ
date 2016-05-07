package com.xiaov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "voterecord", catalog = "xvdz")
public class VoteRecord {

	private String id;
	private String voteId;
	private String openId;
	
	@Id
	@Column(name = "id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="voteid")
	public String getVoteId() {
		return voteId;
	}
	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}
	@Column(name="oppenid")
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String oppenId) {
		this.openId = oppenId;
	}
	
}
