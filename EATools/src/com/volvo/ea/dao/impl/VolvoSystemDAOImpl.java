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
import com.volvo.ea.entities.VolvoSystem;

/**
 * @author pico
 * This Data access object is responsible for all operations on VolvoSystem
 * persistent objects.
 */
public class VolvoSystemDAOImpl implements VolvoDAO<VolvoSystem> {
	
	PersistenceManagerFactory pmf;
	
	/**
	 * Default constructor
	 */
	public VolvoSystemDAOImpl() {
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
	public VolvoSystemDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoDAO#create(com.volvo.ea.entities.VolvoSystem)
	 */
	@Override
	public void create(VolvoSystem pSystem) throws Throwable {
		if(this.pmf != null) {
			if(pSystem != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				try {
					pm.makePersistent(pSystem);
		        } finally {
		            pm.close();
		        }
			}
			else {
				throw new Throwable("The system to persist is null");
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist systems");
		}
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoDAO#read(com.google.appengine.api.datastore.Key)
	 */
	@Override
	public VolvoSystem read(Key pKey) throws Throwable {
		VolvoSystem volvoSystem = null;
		if(this.pmf != null) {
			if(pKey != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				try {
					volvoSystem = pm.getObjectById(VolvoSystem.class, pKey);
		        } finally {
		            pm.close();
		        }
			}
			else {
				throw new Throwable("The key provided to retrieve object is null");
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist systems");
		}
		return volvoSystem;
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoDAO#update(com.volvo.ea.entities.VolvoEntity)
	 */
	@Override
	public void update(VolvoSystem pSystem) throws Throwable {
		if(this.pmf != null) {
			if(pSystem != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				try {
					pm.makePersistent(pSystem);
		        } finally {
		            pm.close();
		        }
			}
			else {
				throw new Throwable("The system to persist is null");
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist systems");
		}
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoDAO#delete(com.volvo.ea.entities.VolvoSystem)
	 */
	@Override
	public void delete(Key pKey) throws Throwable {
		if(this.pmf != null) {
			if(pKey != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				try {
					VolvoSystem system = pm.getObjectById(VolvoSystem.class, pKey);
					pm.deletePersistent(system);
		        } finally {
		            pm.close();
		        }
			}
			else {
				throw new Throwable("The system to persist is null");
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist systems");
		}
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoDAO#list()
	 */
	@Override
	public List<VolvoSystem> list() throws Throwable {
		List<VolvoSystem> volvoSystems = null;
		if(this.pmf != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				Query q = pm.newQuery(VolvoSystem.class);
				try {
					volvoSystems = (List<VolvoSystem>) q.execute();
		        } finally {
		            pm.close();
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist entities");
		}
		return volvoSystems;
	}

}
