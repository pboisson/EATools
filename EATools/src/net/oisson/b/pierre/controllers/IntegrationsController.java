package net.oisson.b.pierre.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.oisson.b.pierre.helpers.entities.Integration;
import net.oisson.b.pierre.helpers.entities.System;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Key;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/integrations")
public class IntegrationsController {

	@RequestMapping(value = "/system-view", method = RequestMethod.GET)
	public String systemView(HttpServletRequest request, ModelMap model) {

		List<Integration> integrations = getAllIntegrations();

		Map<Key, System> systems = new HashMap<Key, System>();
		/* get the list of systems with integrations */
		for (Integration integration : integrations) {
			putRequestor(systems, integration);
			putSource(systems, integration);
			putOwner(systems, integration);
		}

		model.addAttribute("integrationsList", integrations);
		model.addAttribute("systemsList", systems);
		return "integrations/system-view";
	}

	@RequestMapping(value = "/entity-view", method = RequestMethod.GET)
	public String entityView(HttpServletRequest request, ModelMap model) {

		List<Integration> integrations = getAllIntegrations();

		Map<Key, net.oisson.b.pierre.helpers.entities.Entity> entities = new HashMap<Key, net.oisson.b.pierre.helpers.entities.Entity>();
		/* get the list of entities with owners */
		for (Integration integration : integrations) {
			putEntityOwner(entities, integration);
		}

		model.addAttribute("integrationsList", integrations);
		model.addAttribute("entitiesList", entities);
		return "integrations/entity-view";
	}

	/**
	 * @param systems
	 * @param integration
	 */
	private void putOwner(Map<Key, System> systems, Integration integration) {
		/* put the owning information in the map of system */
		if (systems.containsKey(integration.getOwner())) {
			System s = systems.get(integration.getOwner());
			if (s.getOwns() != null) {
				if (!s.getOwns().contains(integration.getEntity())) {
					s.getOwns().add(integration.getEntity());
				}
			} else {
				s.setOwns(new ArrayList<Key>());
				s.getOwns().add(integration.getEntity());
			}
			systems.put(integration.getOwner(), s);
		} else {
			System s = new System();
			if (s.getOwns() != null) {
				if (!s.getOwns().contains(integration.getEntity())) {
					s.getOwns().add(integration.getEntity());
				}
			} else {
				s.setOwns(new ArrayList<Key>());
				s.getOwns().add(integration.getEntity());
			}
			systems.put(integration.getOwner(), s);
		}
	}

	/**
	 * @param entities
	 * @param integration
	 */
	private void putEntityOwner(
			Map<Key, net.oisson.b.pierre.helpers.entities.Entity> entities,
			Integration integration) {
		/* put the owning information in the map of system */
		if (entities.containsKey(integration.getEntity())) {
			net.oisson.b.pierre.helpers.entities.Entity e = entities
					.get(integration.getEntity());
			if (e.getOwnedBy() != null) {
				if (!e.getOwnedBy().contains(integration.getOwner())) {
					e.getOwnedBy().add(integration.getOwner());
				}
			} else {
				e.setOwnedBy(new ArrayList<Key>());
				e.getOwnedBy().add(integration.getOwner());
			}
			entities.put(integration.getEntity(), e);
		} else {
			net.oisson.b.pierre.helpers.entities.Entity e = new net.oisson.b.pierre.helpers.entities.Entity();
			if (e.getOwnedBy() != null) {
				if (!e.getOwnedBy().contains(integration.getOwner())) {
					e.getOwnedBy().add(integration.getOwner());
				}
			} else {
				e.setOwnedBy(new ArrayList<Key>());
				e.getOwnedBy().add(integration.getOwner());
			}
			entities.put(integration.getEntity(), e);
		}
	}

	/**
	 * @param systems
	 * @param integration
	 */
	private void putSource(Map<Key, System> systems, Integration integration) {
		/* put the source information in the map of system */
		if (systems.containsKey(integration.getSource())) {
			System s = systems.get(integration.getSource());
			if (s.getCallers() != null) {
				if (!s.getCallers().contains(integration.getRequestor())) {
					s.getCallers().add(integration.getRequestor());
				}
			} else {
				s.setCallers(new ArrayList<Key>());
				s.getCallers().add(integration.getRequestor());
			}
			systems.put(integration.getSource(), s);
		} else {
			System s = new System();
			if (s.getCallers() != null) {
				if (!s.getCallers().contains(integration.getRequestor())) {
					s.getCallers().add(integration.getRequestor());
				}
			} else {
				s.setCallers(new ArrayList<Key>());
				s.getCallers().add(integration.getRequestor());
			}
			systems.put(integration.getSource(), s);
		}
	}

	/**
	 * Put the requestor information in the map of systems
	 * 
	 * @param systems
	 * @param integration
	 */
	private void putRequestor(Map<Key, System> systems, Integration integration) {
		if (systems.containsKey(integration.getRequestor())) {
			System s = systems.get(integration.getRequestor());
			if (s.getCalling() != null) {
				if (!s.getCalling().contains(integration.getSource())) {
					s.getCalling().add(integration.getSource());
				}
			} else {
				s.setCalling(new ArrayList<Key>());
				s.getCalling().add(integration.getSource());
			}
			systems.put(integration.getRequestor(), s);
		} else {
			System s = new System();
			if (s.getCalling() != null) {
				if (!s.getCalling().contains(integration.getSource())) {
					s.getCalling().add(integration.getSource());
				}
			} else {
				s.setCalling(new ArrayList<Key>());
				s.getCalling().add(integration.getSource());
			}
			systems.put(integration.getRequestor(), s);
		}
	}

	/**
	 * @return
	 */
	private List<Integration> getAllIntegrations() {
		/* get all the integrations from the datastore */
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query("Integration");

		FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();

		QueryResultList<Entity> integrationsFromQuery = datastore
				.prepare(query).asQueryResultList(fetchOptions);

		/* transform integrations from datastore to proper helper object */
		List<Integration> integrations = new ArrayList<Integration>();

		for (Entity integrationAsEntity : integrationsFromQuery) {
			integrations.add(new Integration(integrationAsEntity));
		}
		return integrations;
	}
}
