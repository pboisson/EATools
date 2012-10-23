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
import com.volvo.ea.entities.Integration;

/**
 * @author pico
 * This Data access object is responsible for all operations on Integration
 * persistent objects.
 */
public class IntegrationDAOImpl implements VolvoDAO<Integration> {
	
	PersistenceManagerFactory pmf;
	
	/**
	 * Default constructor
	 */
	public IntegrationDAOImpl() {
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
	public IntegrationDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoDAO#create(com.volvo.ea.entities.Integration)
	 */
	@Override
	public void create(Integration pIntegration) throws Throwable {
		if(this.pmf != null) {
			if(pIntegration != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				try {
					pm.makePersistent(pIntegration);
		        } finally {
		            pm.close();
		        }
			}
			else {
				throw new Throwable("The integration to persist is null");
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist integrations");
		}
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoDAO#read(com.google.appengine.api.datastore.Key)
	 */
	@Override
	public Integration read(Key pKey) throws Throwable {
		Integration integration = null;
		if(this.pmf != null) {
			if(pKey != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				try {
					integration = pm.getObjectById(Integration.class, pKey);
		        } finally {
		            pm.close();
		        }
			}
			else {
				throw new Throwable("The key provided to retrieve object is null");
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist integrations");
		}
		return integration;
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoDAO#update(com.volvo.ea.entities.Integration)
	 */
	@Override
	public void update(Integration pIntegration) throws Throwable {
		if(this.pmf != null) {
			if(pIntegration != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				try {
					pm.makePersistent(pIntegration);
		        } finally {
		            pm.close();
		        }
			}
			else {
				throw new Throwable("The integration to persist is null");
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist integrations");
		}
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoDAO#delete(com.volvo.ea.entities.Integration)
	 */
	@Override
	public void delete(Key pKey) throws Throwable {
		if(this.pmf != null) {
			if(pKey != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				try {
					Integration integration = pm.getObjectById(Integration.class, pKey);
					pm.deletePersistent(integration);
		        } finally {
		            pm.close();
		        }
			}
			else {
				throw new Throwable("The integration to persist is null");
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to persist integrations");
		}
	}

	/* (non-Javadoc)
	 * @see com.volvo.ea.dao.VolvoDAO#list()
	 */
	@Override
	public List<Integration> list() throws Throwable {
		List<Integration> integrations = null;
		if(this.pmf != null) {
				PersistenceManager pm = this.pmf.getPersistenceManager();
				Query q = pm.newQuery(Integration.class);
				try {
					integrations = (List<Integration>) q.execute();
		        } finally {
		            pm.close();
			}
		}
		else {
			throw new Throwable("No PersistenceManagerFactory defined to retrieve integrations");
		}
		return integrations;
	}

}
