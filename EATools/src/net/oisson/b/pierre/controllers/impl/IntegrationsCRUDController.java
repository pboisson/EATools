/**
 * 
 */
package net.oisson.b.pierre.controllers.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.oisson.b.pierre.controllers.CRUDController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;

/**
 * @author pico
 * 
 */
@Controller
@RequestMapping("/integrations")
public class IntegrationsCRUDController implements CRUDController {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.oisson.b.pierre.controllers.CRUDController#create(org.springframework
	 * .ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, ModelMap model) {

		Entity entity = populate(request, null);
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		datastore.put(entity);

		return new ModelAndView("redirect:read");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.oisson.b.pierre.controllers.CRUDController#read(org.springframework
	 * .ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request, ModelMap model) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query("Integration").addSort("date",
				Query.SortDirection.DESCENDING);
		
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(PAGESIZE);
		String cursor = request.getParameter("cursor");
		
		if(cursor != null) {
			fetchOptions.startCursor(Cursor.fromWebSafeString(cursor));
		}
		
		QueryResultList<Entity> integrations = datastore.prepare(query).asQueryResultList(
				fetchOptions);
		
		cursor = integrations.getCursor().toWebSafeString();
		model.addAttribute("cursor", cursor);
		model.addAttribute("integrationsList", integrations);
		return "integrations/read";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.oisson.b.pierre.controllers.CRUDController#update(java.lang.String,
	 * javax.servlet.http.HttpServletRequest, org.springframework.ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/update/{key}", method = RequestMethod.GET)
	public String update(@PathVariable String key, HttpServletRequest request,
			ModelMap model) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity entity;
		try {
			entity = datastore.get(KeyFactory.stringToKey(key));
			model.addAttribute("integration", entity);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addEntitiesAndSystems(model, datastore);

		return "integrations/update";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.oisson.b.pierre.controllers.CRUDController#update(javax.servlet.http
	 * .HttpServletRequest, org.springframework.ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, ModelMap model) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Entity entity;
		try {
			entity = datastore.get(KeyFactory.stringToKey(request
					.getParameter("key")));
			entity = populate(request, entity);

			datastore.put(entity);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return to list
		return new ModelAndView("redirect:read");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.oisson.b.pierre.controllers.CRUDController#delete(java.lang.String,
	 * javax.servlet.http.HttpServletRequest, org.springframework.ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String key,
			HttpServletRequest request, ModelMap model) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		datastore.delete(KeyFactory.stringToKey(key));

		// return to list
		return new ModelAndView("redirect:../read");
	}

	@Override
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap model) {
		
		addEntitiesAndSystems(model, DatastoreServiceFactory
				.getDatastoreService());
		
		return "integrations/create";
	}

	/**
	 * @param model
	 */
	private void addEntitiesAndSystems(ModelMap model, DatastoreService datastore) {
		if(null == datastore)
			datastore = DatastoreServiceFactory
				.getDatastoreService();
		
		/* get entities to choose for the integration */
		Query query = new Query("Entity").addSort("name",
				Query.SortDirection.ASCENDING);
		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withDefaults());
		model.addAttribute("entities", entities);
		
		/* get systems to choose for the integration */
		query = new Query("System").addSort("name",
				Query.SortDirection.ASCENDING);
		List<Entity> systems = datastore.prepare(query).asList(
				FetchOptions.Builder.withDefaults());
		model.addAttribute("systems", systems);
	}

	@Override
	public Entity populate(HttpServletRequest request, Entity entity) {
		if (null == entity)
			entity = new Entity(KeyFactory.createKey("Integration",
					request.getParameter("id")));
		entity.setProperty("description", request.getParameter("description"));
		entity.setProperty("entity", KeyFactory.stringToKey(request.getParameter("entity")));
		entity.setProperty("requestor", KeyFactory.stringToKey(request.getParameter("requestor")));
		entity.setProperty("source", KeyFactory.stringToKey(request.getParameter("source")));
		entity.setProperty("owner", KeyFactory.stringToKey(request.getParameter("owner")));
		entity.setProperty("date", new Date());
		return entity;
	}
}
