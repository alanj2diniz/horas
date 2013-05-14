package br.com.wryel.horas.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wryel.horas.AppContext;
import br.com.wryel.horas.entity.Usuario;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpSessionListenerImpl.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		LOGGER.debug("Sess�o criada");
		httpSessionEvent.getSession().setMaxInactiveInterval(AppContext.Session.TIME_OUT);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		Usuario usuario = (Usuario) httpSessionEvent.getSession().getAttribute(AppContext.Session.USUARIO);
		if (usuario != null) {
			LOGGER.debug("Sess�o destruida com usuario logado: "  + usuario);			
		} else {
			LOGGER.debug("Sess�o destruida sem usuario logado");
		}
	}
}
