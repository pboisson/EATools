package net.oisson.b.pierre.helpers.entities;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;

public class System {

	private String id;
	private String name;
	private String url;
	private Date date;
	private List<Key> callers;
	private List<Key> calling;
	private List<Key> owns;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
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
	 * @param url
	 *            the url to set
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
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the callers
	 */
	public List<Key> getCallers() {
		return callers;
	}

	/**
	 * @param callers the callers to set
	 */
	public void setCallers(List<Key> callers) {
		this.callers = callers;
	}

	/**
	 * @return the calling
	 */
	public List<Key> getCalling() {
		return calling;
	}

	/**
	 * @param calling the calling to set
	 */
	public void setCalling(List<Key> calling) {
		this.calling = calling;
	}

	/**
	 * @return the owns
	 */
	public List<Key> getOwns() {
		return owns;
	}

	/**
	 * @param owns the owns to set
	 */
	public void setOwns(List<Key> owns) {
		this.owns = owns;
	}


	

}
