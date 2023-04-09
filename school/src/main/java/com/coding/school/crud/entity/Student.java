package com.coding.school.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	@Id
	private Integer id;
	
	private String name;
	
	@JsonIgnore
	public boolean isEmpty() {
		return (id == 0 && name == null);
	}

}
