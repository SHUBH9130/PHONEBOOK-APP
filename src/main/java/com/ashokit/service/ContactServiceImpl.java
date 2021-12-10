package com.ashokit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.bindings.ContactForm;
import com.ashokit.entities.Contact;
import com.ashokit.repository.ContactRepo;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepo repo;

	@Override
	public String saveContact(ContactForm form) {
		// TODO Auto-generated method stub
		// Repo class save method require the entity object
		// our method take input as form binding class
		// we need to copy entity data to binding class
		Contact entity = new Contact();
		
		BeanUtils.copyProperties(form, entity);

		System.out.println(form.toString());
		System.out.println(entity.toString());
		
		entity.setActiveSW("Y");
		
		entity = repo.save(entity);

		System.out.println(entity.toString());
		if (entity.getContactId() != null) {
			return "Contact details saved succesfully...";
		}

		return "Failed to save the contact.. Please try again !!";
	}

	@Override
	public List<ContactForm> viewContacts() {
		/*
		 * Here also find all method require List of entity class data First we need to
		 * create one list to hold the contact form data Then we need to iterate the
		 * List of data in entity class After that copy the data from stored entity to
		 * binding class list add the form data to created binding list and return it
		 */

		// created binding class list to hold the entity data
		List<ContactForm> dataList = new ArrayList<>();

		// repo method took entity class list to hold the data
		List<Contact> findAll = repo.findAll();

		// iterate and copy the entity data to binding list as created above
		for (Contact entity : findAll) {
			ContactForm form = new ContactForm();
			BeanUtils.copyProperties(entity, form);
			dataList.add(form);
		}

		return dataList;
	}

	@Override
	public ContactForm editContact(Integer contactId) {
		// getting contact id from database and hold into entity class obj
		Optional<Contact> findById = repo.findById(contactId);

		// check if given id is present or not if present then get the data and hold
		// into
		// entity obj and then copy the entity data into binding class obj and then
		// return the
		// Binding class obj
		if (findById.isPresent()) {

			Contact entity = findById.get();

			ContactForm form = new ContactForm();

			BeanUtils.copyProperties(entity, form);

			return form;

		}

		return null;
	}

	@Override
	public List<ContactForm> deleteContact(Integer contactId) {

		repo.deleteById(contactId);
		// created binding class list to hold the entity data
		List<ContactForm> dataList = new ArrayList<>();

		// repo method took entity class list to hold the data
		List<Contact> findAll = repo.findAll();

		// iterate and copy the entity data to binding list as created above
		for (Contact entity : findAll) {
			ContactForm form = new ContactForm();
			BeanUtils.copyProperties(entity, form);
			dataList.add(form);
		}

		return dataList;
	}

}
