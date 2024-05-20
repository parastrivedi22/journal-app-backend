package com.parastrivedi.JournalApplication.exception;

import org.bson.types.ObjectId;

public class ResourceNotFoundException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ResourceNotFoundException(String resourceName, ObjectId id) {
		super(String.format("%s not found with id: %s", resourceName, id));
		
	}
	
	
	

}
