package com.quangnv.uet.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
import lombok.ToString;

@MappedSuperclass
@Data
@ToString
public abstract class BaseEntity {
	@CreatedDate
	@Column(name = "create_at", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@LastModifiedDate
	@Column(name = "update_at", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;

	@CreatedBy
	@Column(name = "create_by", nullable = true, length = 30)
	private String createBy;

	@LastModifiedBy
	@Column(name = "update_by", nullable = true, length = 30)
	private String updateBy;

	@Transient
	private boolean _saved = false;

	public void onSave() {
		_saved = true;
	}

	public void onLoad() {
		_saved = true;
	}

	public boolean isSaved() {
		return _saved;
	}
}
