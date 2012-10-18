/**
 * 
 */
package net.oisson.b.pierre.helpers.entities;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;

/**
 * @author pico
 *
 */
public class Entity {

	private String id;
	private String name;
	private Date date;
	private List<Key> ownedBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<Key> getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(List<Key> ownedBy) {
		this.ownedBy = ownedBy;
	}

}
