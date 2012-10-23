/**
 * Interface to 
 */
package com.volvo.ea.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.volvo.ea.dao.VolvoDAO;
import com.volvo.ea.entities.VolvoSystem;

/**
 * @author pico
 * Typical Controller to create, read, update and delete entities.
 */
public interface CRUDController<T> {
	
	public int PAGESIZE = 15;
	
	public VolvoDAO<T> getVolvoDAO();
	
	public void setVolvoDAO(VolvoDAO<T> volvoDAO);

	/** create */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, ModelMap model);
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap model);
	
	/** read */
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request, ModelMap model);
	
	/** update */
	@RequestMapping(value = "/update/{key}", method = RequestMethod.GET)
	public String update(@PathVariable String key,
			HttpServletRequest request, ModelMap model);
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, ModelMap model);
	
	/** delete */
	@RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String key,
			HttpServletRequest request, ModelMap model);
	
	/** populate */
	public T populate(HttpServletRequest pRrequest, T pEntity);
	
}
