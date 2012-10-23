/**
 * 
 */
package com.volvo.ea.controllers.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.KeyFactory;
import com.volvo.ea.controllers.CRUDController;
import com.volvo.ea.dao.VolvoDAO;
import com.volvo.ea.entities.VolvoSystem;

/**
 * @author pico
 * 
 */
@Controller
@RequestMapping("/systems")
public class SystemsCRUDController implements CRUDController<VolvoSystem> {

	private VolvoDAO<VolvoSystem> volvoDAO;

	/**
	 * @return the volvoEntityDAO
	 */
	@Override
	public VolvoDAO<VolvoSystem> getVolvoDAO() {
		return volvoDAO;
	}

	/**
	 * @param volvoEntityDAO
	 *            the volvoEntityDAO to set
	 */
	@Override
	public void setVolvoDAO(VolvoDAO<VolvoSystem> volvoEntityDAO) {
		this.volvoDAO = volvoEntityDAO;
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

		VolvoSystem system = populate(request, null);

		try {
			this.volvoDAO.create(system);
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

		List<VolvoSystem> systems = null;
		try {
			systems = this.volvoDAO.list();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("systemsList", systems);
		return "systems/read";
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

		VolvoSystem system = null;
		try {
			system = this.volvoDAO.read(KeyFactory.stringToKey(key));
			model.addAttribute("system", system);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "systems/update";
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

		VolvoSystem system;
		try {
			system = this.volvoDAO.read(KeyFactory.stringToKey(request
					.getParameter("key")));
			populate(request, system);
			this.volvoDAO.update(system);
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
		
		VolvoSystem system;
		try {
			system = this.volvoDAO.read(KeyFactory.stringToKey(key));
			this.volvoDAO.delete(system);
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
		return "systems/create";
	}

	@Override
	public VolvoSystem populate(HttpServletRequest request, VolvoSystem pSystem) {
		if (null == pSystem || pSystem.getKey() == null)
			pSystem = new VolvoSystem(KeyFactory.createKey(
					VolvoSystem.class.getSimpleName(),
					request.getParameter("id")), request.getParameter("name"),
					request.getParameter("url"), new Date(), null, null, null);
		else {
			pSystem.setName(request.getParameter("name"));
			pSystem.setUrl(request.getParameter("url"));
		}
		return pSystem;
	}

}
