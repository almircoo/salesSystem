package listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;



@WebListener
public class SessionListener implements HttpSessionListener {
	
	
	public void sessionCreated(HttpSessionEvent e) {
		System.out.print("SESSION CREADA");	
		System.out.print("SESSION LISTENER: CRAETED - " + e.getSession().getId());	
		System.out.print("SESSION LISTENER: CRAETED - " + e.getSession().getCreationTime());	
	}
	
	public void sessionDestroyed(HttpSessionEvent e) {
		System.out.print("SESSION DESTRUIDA");	
		System.out.print("SESSION DESTRUIDA: CRAETED - " + e.getSession().getId());	
		System.out.print("SESSION DESTRUIDA: CRAETED - " + e.getSession().getCreationTime());	
	}
}
