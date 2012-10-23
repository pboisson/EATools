package com.volvo.ea.controllers;

import java.io.Reader;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/importer")
public class ImportController {
	
	private DefaultHandler handler;

	@RequestMapping(value = "/xml", method = RequestMethod.POST)
	public ModelAndView importer(HttpServletRequest request, ModelMap model) {

		String xmlAsString = request.getParameter("xml");
		if (xmlAsString != null && !"".equals(xmlAsString)) {
			
			Reader read = new StringReader(xmlAsString);
			InputSource is = new InputSource(read);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			try {

				SAXParser saxParser = factory.newSAXParser();
				saxParser.parse(is, handler);
				handler.toString();

			} catch (Throwable t) {

				t.printStackTrace();

			} 
		}

		return new ModelAndView("redirect:xml");
	}

	@RequestMapping(value = "/xml", method = RequestMethod.GET)
	public ModelAndView importer() {
		return new ModelAndView("importer/xml");
	}

	/**
	 * @return the handler
	 */
	public DefaultHandler getHandler() {
		return handler;
	}

	/**
	 * @param handler the handler to set
	 */
	public void setHandler(DefaultHandler handler) {
		this.handler = handler;
	}
}
