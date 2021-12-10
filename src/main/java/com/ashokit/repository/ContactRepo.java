package com.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {

}
