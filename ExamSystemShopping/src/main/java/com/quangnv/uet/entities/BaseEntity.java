package com.quangnv.uet.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;

@MappedSuperclass
@Data
@ToString
public abstract class BaseEntity {
	@Column(name = "create_at", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@Column(name = "update_at", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date updateAt;
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
