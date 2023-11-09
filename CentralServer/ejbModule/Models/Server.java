package Models;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Server implements Serializable {
	
	@Id
	int id;
	String name;
	int port;
	int servernum;
	
	
	public Server() {
		super();
	}
	
	public Server(int id, String name, int port, int servernum) {
		super();
		this.id = id;
		this.name = name;
		this.port = port;
		this.servernum = servernum;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getServernum() {
		return servernum;
	}
	public void setServernum(int servernum) {
		this.servernum = servernum;
	}
	
	

}
