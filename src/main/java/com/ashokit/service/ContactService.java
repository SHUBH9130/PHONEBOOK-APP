package com.ashokit.service;

import java.util.List;

import com.ashokit.bindings.ContactForm;


public interface ContactService {
	
	//save the contact form data
	public String saveContact(ContactForm form);

	//view contact details
	public List<ContactForm> viewContacts();

	//Edit Contact Details
	public ContactForm editContact(Integer contactId);

	//Delete Contact Details
	public List<ContactForm> deleteContact(Integer contactId);

}
