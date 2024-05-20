package com.parastrivedi.JournalApplication.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "journal")
public class Journal {
	
	@Id
	private ObjectId id;
	@NonNull
	private String title;
	private String content;
	private LocalDateTime createdAt;

}
