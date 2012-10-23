package com.volvo.ea.entities;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * @author pico
 * An Integration describes the flow of an information being transfered 
 * between two applications in the IT landscape.
 */
@PersistenceCapable
public class Integration implements IVolvoEntity {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	/**
	 * The description of the integration as of the integration request document
	 */
	@Persistent
	private String description;
	@Persistent
	private Key entity;
	@Persistent
	private Key requestor;
	@Persistent
	private Key source;
	@Persistent
	private Key owner;
	@Persistent
	private Date date;
	
	/**
	 * @param key
	 * @param description
	 * @param entity
	 * @param requestor
	 * @param source
	 * @param owner
	 * @param date
	 */
	public Integration(Key key, String description, Key entity,
			Key requestor, Key source, Key owner,
			Date date) {
		this.key = key;
		this.description = description;
		this.entity = entity;
		this.requestor = requestor;
		this.source = source;
		this.owner = owner;
		this.date = date;
	}
	
	public Integration() {
		super();
	}
	
	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
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
	
}
