//package CentralServer;
//
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import Models.Service;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//
//
//public class MysqlBDD {
//	
//	@PersistenceContext
//	EntityManager em;
//	public static void main(String[] args){
//		
//		try{
//			for(int i=0;i<16;i++) {
//				Service s = new Service(i,i,"Service"+i,"This is the description of the service number "+i);
//				em.persist(s);
//			}
//		}catch(Exception e) {System.out.println("Exception"+e.toString());}
//	}
//}
