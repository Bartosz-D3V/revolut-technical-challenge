package com.revolut.technicalchallenge;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
  private final static Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(final String[] args) {
    final Server server = new Server(8080);
    final ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

    ctx.setContextPath("/");
    server.setHandler(ctx);

    final ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitOrder(1);
    servletHolder.setInitParameter("jersey.config.server.provider.packages", "com.revolut.technicalchallenge");

    try {
      server.start();
      server.join();
    } catch (final Exception ex) {
      logger.log(Level.SEVERE, null, ex);
    } finally {
      server.destroy();
    }
  }
}
