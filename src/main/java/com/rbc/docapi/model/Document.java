package com.rbc.docapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENT")
public class Document implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Document version
	 */
	private String version;

	/**
	 * Document appCode
	 */
	private String appCode;

	/**
	 * Document created date
	 */
	private Date cratedDate;

	/**
	 * Document last modified date
	 */
	private Date lastModifieddDate;

	/**
	 * Document body
	 */
	private String body;

	/**
	 * Default Constructor
	 */
	public Document() {

	}

	/**
	 * Constructor with parameters
	 * 
	 * @param version
	 * @param appCode
	 * @param createdDate
	 * @param updatedDate
	 */
	public Document(String version, String appCode, Date createdDate, Date lastModifieddDate, String body) {
		this.version = version;
		this.appCode = appCode;
		this.cratedDate = createdDate;
		this.lastModifieddDate = lastModifieddDate;
		this.body = body;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the appCode
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * @param appCode
	 *            the appCode to set
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * @return the cratedDate
	 */
	public Date getCratedDate() {
		return cratedDate;
	}

	/**
	 * @param cratedDate
	 *            the cratedDate to set
	 */
	public void setCratedDate(Date cratedDate) {
		this.cratedDate = cratedDate;
	}

	/**
	 * @return the lastModifieddDate
	 */
	public Date getLastModifieddDate() {
		return lastModifieddDate;
	}

	/**
	 * @param lastModifieddDate
	 *            the lastModifieddDate to set
	 */
	public void setLastModifieddDate(Date lastModifieddDate) {
		this.lastModifieddDate = lastModifieddDate;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appCode == null) ? 0 : appCode.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (appCode == null) {
			if (other.appCode != null)
				return false;
		} else if (!appCode.equals(other.appCode))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Document [id=" + id + ", version=" + version + ", appCode=" + appCode + ", cratedDate=" + cratedDate
				+ ", lastModifieddDate=" + lastModifieddDate + ", body=" + body + "]";
	}

}
