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
import com.volvo.ea.entities.VolvoEntity;

/**
 * @author pico
 * 
 */
@Controller
@RequestMapping("/entities")
public class EntitiesCRUDController implements CRUDController<VolvoEntity> {

	private VolvoDAO<VolvoEntity> volvoDAO;

	/**
	 * @return the volvoEntityDAO
	 */
	@Override
	public VolvoDAO<VolvoEntity> getVolvoDAO() {
		return volvoDAO;
	}

	/**
	 * @param volvoEntityDAO the volvoEntityDAO to set
	 */
	@Override
	public void setVolvoDAO(VolvoDAO<VolvoEntity> volvoEntityDAO) {
		this.volvoDAO = volvoEntityDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.volvo.ea.controllers.CRUDController#create(org.springframework
	 * .ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, ModelMap model) {

		VolvoEntity entity = populate(request, null);

		try {
			volvoDAO.create(entity);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("redirect:read");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.volvo.ea.controllers.CRUDController#read(org.springframework
	 * .ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request, ModelMap model) {
		
		List<VolvoEntity> entities = null;
		try {
			entities = this.volvoDAO.list();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("entitiesList", entities);
		return "entities/read";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.volvo.ea.controllers.CRUDController#update(java.lang.String,
	 * javax.servlet.http.HttpServletRequest, org.springframework.ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/update/{key}", method = RequestMethod.GET)
	public String update(@PathVariable String key, HttpServletRequest request,
			ModelMap model) {
		
		VolvoEntity entity = null;
		try {
			entity = this.volvoDAO.read(KeyFactory.stringToKey(key));
			model.addAttribute("entity", entity);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "entities/update";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.volvo.ea.controllers.CRUDController#update(javax.servlet.http
	 * .HttpServletRequest, org.springframework.ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, ModelMap model) {

		VolvoEntity entity;
		try {
			entity = volvoDAO.read(KeyFactory.stringToKey(request
					.getParameter("key")));
			entity = populate(request, entity);
			volvoDAO.update(entity);
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
	 * @see
	 * com.volvo.ea.controllers.CRUDController#delete(java.lang.String,
	 * javax.servlet.http.HttpServletRequest, org.springframework.ui.ModelMap)
	 */
	@Override
	@RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String key,
			HttpServletRequest request, ModelMap model) {

		try {
			this.volvoDAO.delete(KeyFactory.stringToKey(key));
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
		return "entities/create";
	}

	/**
	 * Populate a VolvoEntity from the request
	 * 
	 * @param request
	 * @param entity
	 * @return a volvoEntity populated from the request
	 */
	public VolvoEntity populate(HttpServletRequest request, VolvoEntity entity) {
		if (null == entity || entity.getKey() == null)
			entity = new VolvoEntity(KeyFactory.createKey(
					VolvoEntity.class.getSimpleName(),
					request.getParameter("id")), request.getParameter("name"),
					new Date(), null);
		else
			entity.setName(request.getParameter("name"));
		return entity;
	}

}
