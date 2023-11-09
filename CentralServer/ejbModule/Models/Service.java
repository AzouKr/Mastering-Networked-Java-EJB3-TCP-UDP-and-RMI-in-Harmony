package Models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity

public class Service implements Serializable {

	@Id
	int id;
	int number;
	String name;
	String description;
	
	
	public Service() {
		super();
	}
	public Service(int id, int number, String name, String description) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.description = description;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
