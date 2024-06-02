package com.parastrivedi.JournalApplication.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	private ObjectId id;
	@Indexed
	@NonNull
	private String userName;
	@Indexed(unique = true)
	@NonNull
	private String userEmail;
//    private boolean sentimentAnalysis;
	@NonNull
	private String password;
	private String role;
	@DBRef
	private List<Journal> journalEntries = new ArrayList<>();

//    private List<String> roles;
}