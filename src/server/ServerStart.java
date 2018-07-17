package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import server.Console;
import server.DatabaseConnection;

public class ServerStart {

    public static void main(String[] args) {

        DatabaseConnection.open("wwtbacss.db");

        ResourceConfig config = new ResourceConfig();
        config.packages("server");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(8081);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            Console.log("Server successfully started.");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
            DatabaseConnection.open("wwtbacss.db");
        }
    }
}

