/**
 * 
 */
package com.volvo.ea.dao.impl;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.volvo.ea.dao.VolvoDAO;
import com.volvo.ea.entities.VolvoEntity;

/**
 * @author pico
 * This Data access object is responsible for all operations on VolvoEntity
 * persistent objects.
 */
public class VolvoEntityDAOImpl implements VolvoDAO<VolvoEntity> {
	
	PersistenceManagerFactory pmf;
	
	/**
	 * Default constructor
	 */
	public VolvoEntityDAOImpl() {
		super();
	}

	/**
	 * @return the PersistenceManagerFactory
	 */
	public PersistenceManagerFactory getPersistenceManagerFactory() {
		return pmf;
	}

	/**
	 * @param pmf the PersistenceManagerFactory to set
	 */
	public void setPersistenceManagerFactory(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	/**
	 * @param pmf
	 */
	public VolvoEntityDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoEntityDAO#create(com.volvo.ea.entities.VolvoEntity)
	 */
	@Override
	public void create(VolvoEntity pEntity) throws Throwable {
		if(this.pmf != null) {
			if(pEntity != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				try {
					pm.makePersistent(pEntity);
		        } finally {
		            pm.close();
		        }
			}
			else {
				throw new Throwable("The entity to persist is null");
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist entities");
		}
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoEntityDAO#read(com.google.appengine.api.datastore.Key)
	 */
	@Override
	public VolvoEntity read(Key pKey) throws Throwable {
		VolvoEntity volvoEntity = null;
		if(this.pmf != null) {
			if(pKey != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				try {
					volvoEntity = pm.getObjectById(VolvoEntity.class, pKey);
		        } finally {
		            pm.close();
		        }
			}
			else {
				throw new Throwable("The key provided to retrieve object is null");
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist entities");
		}
		return volvoEntity;
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoEntityDAO#update(com.volvo.ea.entities.VolvoEntity)
	 */
	@Override
	public void update(VolvoEntity pEntity) throws Throwable {
		if(this.pmf != null) {
			if(pEntity != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				try {
					pm.makePersistent(pEntity);
		        } finally {
		            pm.close();
		        }
			}
			else {
				throw new Throwable("The entity to persist is null");
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist entities");
		}
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoEntityDAO#delete(com.volvo.ea.entities.VolvoEntity)
	 */
	@Override
	public void delete(VolvoEntity pEntity) throws Throwable {
		if(this.pmf != null) {
			if(pEntity != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				try {
					pm.deletePersistent(pEntity);
		        } finally {
		            pm.close();
		        }
			}
			else {
				throw new Throwable("The entity to persist is null");
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist entities");
		}
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoEntityDAO#list()
	 */
	@Override
	public List<VolvoEntity> list() throws Throwable {
		List<VolvoEntity> volvoEntities = null;
		if(this.pmf != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				Query q = pm.newQuery(VolvoEntity.class);
				try {
					volvoEntities = (List<VolvoEntity>) q.execute();
		        } finally {
		            pm.close();
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist entities");
		}
		return volvoEntities;
	}

}
