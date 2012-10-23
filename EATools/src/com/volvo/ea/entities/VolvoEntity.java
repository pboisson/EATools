/**
 * 
 */
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
 * A VolvoEntity describes an information being transfered between two
 * applications in the IT landscape.
 */
@PersistenceCapable(detachable="true")
public class VolvoEntity implements IVolvoEntity {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	@Persistent
	private String name;
	@Persistent
	private Date date;
	@Persistent
	private Set<Key> ownedBy;
	
	/**
	 * @param key
	 * @param name
	 * @param date
	 * @param ownedBy
	 */
	public VolvoEntity(Key key, String name, Date date, Set<Key> ownedBy) {
		this.key = key;
		this.name = name;
		this.date = date;
		this.ownedBy = ownedBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Key> getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(Set<Key> ownedBy) {
		this.ownedBy = ownedBy;
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
}
