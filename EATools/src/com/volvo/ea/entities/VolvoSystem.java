package com.volvo.ea.entities;

import java.util.Date;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * @author pico
 * An VolvoSystem describes an application of the IT landscape.
 */
@PersistenceCapable
public class VolvoSystem implements IVolvoEntity {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private String name;
	@Persistent
	private String url;
	@Persistent
	private Date date;
	@Persistent
	private Set<Key> callers;
	@Persistent
	private Set<Key> calling;
	@Persistent
	private Set<Key> owns;
	
	/**
	 * @param key
	 * @param name
	 * @param url
	 * @param date
	 * @param callers
	 * @param calling
	 * @param owns
	 */
	public VolvoSystem(Key key, String name, String url, Date date,
			Set<Key> callers, Set<Key> calling,
			Set<Key> owns) {
		this.key = key;
		this.name = name;
		this.url = url;
		this.date = date;
		this.callers = callers;
		this.calling = calling;
		this.owns = owns;
	}
	
	public VolvoSystem() {
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	/**
	 * @return the callers
	 */
	public Set<Key> getCallers() {
		return callers;
	}
	/**
	 * @param callers the callers to set
	 */
	public void setCallers(Set<Key> callers) {
		this.callers = callers;
	}
	/**
	 * @return the calling
	 */
	public Set<Key> getCalling() {
		return calling;
	}
	/**
	 * @param calling the calling to set
	 */
	public void setCalling(Set<Key> calling) {
		this.calling = calling;
	}
	/**
	 * @return the owns
	 */
	public Set<Key> getOwns() {
		return owns;
	}
	/**
	 * @param owns the owns to set
	 */
	public void setOwns(Set<Key> owns) {
		this.owns = owns;
	}
	
	

}
