/**
 * 
 */
package com.volvo.ea.controllers.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.volvo.ea.controllers.CRUDController;
import com.volvo.ea.dao.VolvoDAO;
import com.volvo.ea.entities.Integration;
import com.volvo.ea.entities.VolvoEntity;
import com.volvo.ea.entities.VolvoSystem;

/**
 * @author pico
 * 
 */
@Controller
@RequestMapping("/integrations")
public class IntegrationsCRUDController implements CRUDController<Integration> {

	private VolvoDAO<Integration> volvoDAO;
	private VolvoDAO<VolvoEntity> volvoEntityDAO;
	private VolvoDAO<VolvoSystem> volvoSystemDAO;

	/**
	 * @return the volvoEntityDAO
	 */
	public VolvoDAO<VolvoEntity> getVolvoEntityDAO() {
		return volvoEntityDAO;
	}

	/**
	 * @param volvoEntityDAO
	 *            the volvoEntityDAO to set
	 */
	public void setVolvoEntityDAO(VolvoDAO<VolvoEntity> volvoEntityDAO) {
		this.volvoEntityDAO = volvoEntityDAO;
	}

	/**
	 * @return the volvoSystemDAO
	 */
	public VolvoDAO<VolvoSystem> getVolvoSystemDAO() {
		return volvoSystemDAO;
	}

	/**
	 * @param volvoSystemDAO
	 *            the volvoSystemDAO to set
	 */
	public void setVolvoSystemDAO(VolvoDAO<VolvoSystem> volvoSystemDAO) {
		this.volvoSystemDAO = volvoSystemDAO;
	}

	@Override
	public VolvoDAO<Integration> getVolvoDAO() {
		return volvoDAO;
	}

	@Override
	public void setVolvoDAO(VolvoDAO<Integration> volvoDAO) {
		this.volvoDAO = volvoDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.volvo.ea.controllers.CRUDController#create(org.springframework
	 * .ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, ModelMap model) {

		Integration integration = populate(request, null);
		try {
			this.volvoDAO.create(integration);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("redirect:read");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.volvo.ea.controllers.CRUDController#read(org.springframework
	 * .ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request, ModelMap model) {

		try {
			model.addAttribute("integrationsList", this.volvoDAO.list());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "integrations/read";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.volvo.ea.controllers.CRUDController#update(java.lang.String,
	 * javax.servlet.http.HttpServletRequest, org.springframework.ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/update/{key}", method = RequestMethod.GET)
	public String update(@PathVariable String key, HttpServletRequest request,
			ModelMap model) {
		try {
			Integration integration = volvoDAO.read(KeyFactory
					.stringToKey(request.getParameter("key")));
			model.addAttribute("integration", integration);
			/* get entities to choose for the integration */
			model.addAttribute("entities", this.volvoEntityDAO.list());
			/* get systems to choose for the integration */
			model.addAttribute("systems", this.volvoSystemDAO.list());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "integrations/update";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.volvo.ea.controllers.CRUDController#update(javax.servlet.http
	 * .HttpServletRequest, org.springframework.ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, ModelMap model) {
		Integration integration;
		try {
			integration = volvoDAO.read(KeyFactory.stringToKey(request
					.getParameter("key")));
			populate(request, integration);

			volvoDAO.update(integration);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return to list
		return new ModelAndView("redirect:read");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.volvo.ea.controllers.CRUDController#delete(java.lang.String,
	 * javax.servlet.http.HttpServletRequest, org.springframework.ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String key,
			HttpServletRequest request, ModelMap model) {

		try {
			this.volvoDAO
					.delete(this.volvoDAO.read(KeyFactory.stringToKey(key)));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return to list
		return new ModelAndView("redirect:../read");
	}

	@Override
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap model) {
		try {
			/* get entities to choose for the integration */
			model.addAttribute("entities", this.volvoEntityDAO.list());
			/* get systems to choose for the integration */
			model.addAttribute("systems", this.volvoSystemDAO.list());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "integrations/create";
	}

	@Override
	public Integration populate(HttpServletRequest request,
			Integration integration) {
		Key entityKey = KeyFactory.stringToKey(request.getParameter("entity"));
		Key sourceKey = KeyFactory.stringToKey(request.getParameter("source"));
		Key ownerKey = KeyFactory.stringToKey(request.getParameter("owner"));
		Key requestorKey = KeyFactory.stringToKey(request
				.getParameter("requestor"));
		if (null == integration || integration.getKey() == null) {
			try {
				integration = new Integration(KeyFactory.createKey(
						"Integration", request.getParameter("id")),
						request.getParameter("description"), entityKey,
						requestorKey, sourceKey, ownerKey, new Date());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			integration.setDescription(request.getParameter("description"));
			try {
				integration.setEntity(entityKey);
				integration.setRequestor(requestorKey);
				integration.setSource(sourceKey);
				integration.setOwner(ownerKey);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return integration;
	}

}
