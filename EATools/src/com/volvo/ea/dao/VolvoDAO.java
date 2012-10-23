/**
 * 
 */
package com.volvo.ea.dao;

import java.util.List;

import com.google.appengine.api.datastore.Key;

/**
 * @author pico
 * A volvo entity describes an information being transfered between two
 * applications in the IT landscape.
 */
public interface VolvoDAO<T> {

	/**
	 * Creates a VolvoEntity in the persistence system
	 * @param pEntity
	 * @throws Throwable 
	 */
	void create(T pEntity) throws Throwable;
	
	/**
	 * Reads a VolvoEntity in the persistence system from its key
	 * @param pKey the key of the VolvoEntity
	 * @return the volvo entity associated to the key
	 * @throws Throwable 
	 */
	T read(Key pKey) throws Throwable;
	
	/**
	 * Updates an existing VolvoEntity in the persistence system
	 * @param pEntity
	 * @throws Throwable 
	 */
	void update(T pEntity) throws Throwable;
	
	/**
	 * Deletes an existing VolvoEntity in the persistence system from its key
	 * @param pKey
	 * @throws Throwable 
	 */
	void delete(Key pKey) throws Throwable;
	
	/**
	 * Finds all VolvoEntity objects in the persistence system
	 * @return a set of volvo entities
	 * @throws Throwable 
	 */
	List<T> list() throws Throwable;
}
