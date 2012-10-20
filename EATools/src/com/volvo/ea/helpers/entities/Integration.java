package com.volvo.ea.helpers.entities;

import java.util.Date;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class Integration {

	private String id;
	private String description;
	private Key entity;
	private Key requestor;
	private Key source;
	private Key owner;
	private Date date;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the entity
	 */
	public Key getEntity() {
		return entity;
	}
	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Key entity) {
		this.entity = entity;
	}
	/**
	 * @return the requestor
	 */
	public Key getRequestor() {
		return requestor;
	}
	/**
	 * @param requestor the requestor to set
	 */
	public void setRequestor(Key requestor) {
		this.requestor = requestor;
	}
	/**
	 * @return the source
	 */
	public Key getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(Key source) {
		this.source = source;
	}
	/**
	 * @return the owner
	 */
	public Key getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Key owner) {
		this.owner = owner;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Integration() {};
	
	public Integration(Entity e) {
		if(e !=null) {
			if(e.getProperty("description") != null) {
				this.description = (String) e.getProperty("description");
			}
			if(e.getProperty("entity") != null) {
				this.entity = (Key) e.getProperty("entity");
			}
			if(e.getProperty("requestor") != null) {
				this.requestor = (Key) e.getProperty("requestor");
			}
			if(e.getProperty("source") != null) {
				this.source = (Key) e.getProperty("source");
			}
			if(e.getProperty("owner") != null) {
				this.owner = (Key) e.getProperty("owner");
			}
			if(e.getProperty("date") != null) {
				this.date = (Date) e.getProperty("date");
			}
		}
	}
	
}
