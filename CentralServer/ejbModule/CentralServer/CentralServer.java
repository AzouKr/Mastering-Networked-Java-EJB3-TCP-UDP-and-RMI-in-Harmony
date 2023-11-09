package CentralServer;


import Models.Server;
import Models.Service;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class CentralServer implements CentralServerInterface {
	
	@PersistenceContext
	EntityManager em;
	
	public Service HandelServersAPIs(String name){
		
		return (Service) em.createQuery("SELECT s FROM Service s Where s.name = '"+name+"'").getSingleResult();	
	}


	@Override
	public void saveServers(Server server){
		
		em.persist(server);
		
	}
}
