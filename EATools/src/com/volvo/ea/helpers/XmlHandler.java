/**
 * 
 */
package com.volvo.ea.helpers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.KeyFactory;
import com.volvo.ea.helpers.entities.Entity;
import com.volvo.ea.helpers.entities.Integration;
import com.volvo.ea.helpers.entities.System;

/**
 * @author pico
 * 
 */
public class XmlHandler extends DefaultHandler {

	private Stack<Entity> entityStack;
	private List<Entity> entities;
	private Stack<System> systemStack;
	private List<System> systems;
	private Stack<Integration> integrationStack;
	private List<Integration> integrations;

	private StringBuilder content;

	public void startDocument() throws SAXException {

		entityStack = new Stack<Entity>();
		entities = new ArrayList<Entity>();
		systemStack = new Stack<System>();
		systems = new ArrayList<System>();
		integrationStack = new Stack<Integration>();
		integrations = new ArrayList<Integration>();

	}

	public void startElement(String namespaceURI, String localName,
			String qualifiedName, Attributes attributes) throws SAXException {

		content = new StringBuilder();

		if (qualifiedName.equals("entity")) {

			if (integrationStack.isEmpty()) {

				Entity entity = new Entity();
				entity.setId(attributes.getValue("id"));
				entityStack.push(entity);

			}

		} else if (qualifiedName.equals("system")) {

			System system = new System();
			system.setId(attributes.getValue("id"));
			system.setUrl(attributes.getValue("href"));
			systemStack.push(system);

		} else if (qualifiedName.equals("integration")) {

			if (integrationStack.isEmpty() && null != attributes.getValue("id")) {
				Integration integration = new Integration();
				integration.setId(attributes.getValue("id"));
				integrationStack.push(integration);
			}

		}

	}

	public void endElement(String namespaceURI, String simpleName,
			String qualifiedName) throws SAXException {

		if (!entityStack.isEmpty()) {

			if (qualifiedName.equals("entity")) {

				entities.add(entityStack.pop());

			} else if (qualifiedName.equals("name")) {

				Entity entity = entityStack.pop();
				entity.setName(content.toString());
				entityStack.push(entity);

			}

		} else if (!systemStack.isEmpty()) {

			if (qualifiedName.equals("system")) {

				systems.add(systemStack.pop());

			} else if (qualifiedName.equals("name")) {

				System system = systemStack.pop();
				system.setName(content.toString());
				systemStack.push(system);

			}

		} else if (!integrationStack.isEmpty()) {

			if (qualifiedName.equals("integration")) {

				integrations.add(integrationStack.pop());

			} else if (qualifiedName.equals("description")) {

				Integration integration = integrationStack.pop();
				integration.setDescription(content.toString());
				integrationStack.push(integration);

			} else if (qualifiedName.equals("entity")) {

				Integration integration = integrationStack.pop();
				integration.setEntity(KeyFactory.createKey("Entity",
						content.toString()));
				integrationStack.push(integration);

			} else if (qualifiedName.equals("requestor")) {

				Integration integration = integrationStack.pop();
				integration.setRequestor(KeyFactory.createKey("System",
						content.toString()));
				integrationStack.push(integration);

			} else if (qualifiedName.equals("source")) {

				Integration integration = integrationStack.pop();
				integration.setSource(KeyFactory.createKey("System",
						content.toString()));
				integrationStack.push(integration);

			} else if (qualifiedName.equals("owner")) {

				Integration integration = integrationStack.pop();
				integration.setOwner(KeyFactory.createKey("System",
						content.toString()));
				integrationStack.push(integration);

			} else if (qualifiedName.equals("related-integrations")) {

				/*
				 * meaningless integration (already covered by another
				 * integration)
				 */
				integrationStack.pop();

			}
		}
	}

	public void endDocument() throws SAXException {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		for (Entity e : entities) {
			com.google.appengine.api.datastore.Entity dataEntity = new com.google.appengine.api.datastore.Entity(
					KeyFactory.createKey("Entity", e.getId()));
			dataEntity.setProperty("name", e.getName());
			java.lang.System.out.println("recorded entity: " + e.getName());
			dataEntity.setProperty("date", new Date());
			datastore.put(dataEntity);
		}
		for (System s : systems) {
			com.google.appengine.api.datastore.Entity dataEntity = new com.google.appengine.api.datastore.Entity(
					KeyFactory.createKey("System", s.getId()));
			dataEntity.setProperty("name", s.getName());
			java.lang.System.out.println("recorded system: " + s.getName());
			dataEntity.setProperty("url", s.getUrl());
			dataEntity.setProperty("date", new Date());
			datastore.put(dataEntity);
		}
		for (Integration i : integrations) {
			com.google.appengine.api.datastore.Entity dataEntity = new com.google.appengine.api.datastore.Entity(
					KeyFactory.createKey("Integration", i.getId()));
			dataEntity.setProperty("description", i.getDescription());
			java.lang.System.out.println("recorded integration: " + i.getId());
			dataEntity.setProperty("entity", i.getEntity());
			dataEntity.setProperty("requestor", i.getRequestor());
			dataEntity.setProperty("source", i.getSource());
			dataEntity.setProperty("owner", i.getOwner());
			dataEntity.setProperty("date", new Date());
			datastore.put(dataEntity);
		}
	}

	public void characters(char buf[], int offset, int len) throws SAXException {

		content.append(buf, offset, len);

	}

}
