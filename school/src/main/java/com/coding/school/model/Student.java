package com.coding.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private int id;
	private String name;
	
	@JsonIgnore
	public boolean isEmpty() {
		return (id == 0 && name == null);
	}

}
