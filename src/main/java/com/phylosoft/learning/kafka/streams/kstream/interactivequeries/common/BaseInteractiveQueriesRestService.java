package com.phylosoft.learning.kafka.streams.kstream.interactivequeries.common;

import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.state.HostInfo;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

public abstract class BaseInteractiveQueriesRestService {

    private final KafkaStreams streams;
    private final MetadataService metadataService;
    private Server jettyServer;
    private HostInfo hostInfo;
    private final Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

    protected BaseInteractiveQueriesRestService(final KafkaStreams streams,
                                                final HostInfo hostInfo) {
        this.streams = streams;
        this.metadataService = new MetadataService(streams);
        this.hostInfo = hostInfo;
    }

    protected KafkaStreams getStreams() {
        return streams;
    }

    protected Client getClient() {
        return client;
    }

    /**
     * Get the metadata for all of the instances of this Kafka Streams application
     *
     * @return List of {@link HostStoreInfo}
     */
    @GET()
    @Path("/instances")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HostStoreInfo> streamsMetadata() {
        return metadataService.streamsMetadata();
    }

    /**
     * Get the metadata for all instances of this Kafka Streams application that currently
     * has the provided store.
     *
     * @param store The store to locate
     * @return List of {@link HostStoreInfo}
     */
    @GET()
    @Path("/instances/{storeName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HostStoreInfo> streamsMetadataForStore(@PathParam("storeName") String store) {
        return metadataService.streamsMetadataForStore(store);
    }

    /**
     * Find the metadata for the instance of this Kafka Streams Application that has the given
     * store and would have the given key if it exists.
     *
     * @param store Store to find
     * @param key   The key to find
     * @return {@link HostStoreInfo}
     */
    @GET()
    @Path("/instance/{storeName}/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public HostStoreInfo streamsMetadataForStoreAndKey(@PathParam("storeName") String store,
                                                       @PathParam("key") String key) {
        return metadataService.streamsMetadataForStoreAndKey(store, key, new StringSerializer());
    }

    protected boolean thisHost(final HostStoreInfo host) {
        return host.getHost().equals(hostInfo.host()) &&
                host.getPort() == hostInfo.port();
    }

    /**
     * Start an embedded Jetty Server on the given port
     *
     * @param port port to run the Server on
     * @throws Exception
     */
    public void start(final int port) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        jettyServer = new Server(port);
        jettyServer.setHandler(context);

        ResourceConfig rc = new ResourceConfig();
        rc.register(this);
        rc.register(JacksonFeature.class);

        ServletContainer sc = new ServletContainer(rc);
        ServletHolder holder = new ServletHolder(sc);
        context.addServlet(holder, "/*");

        jettyServer.start();
    }

    /**
     * Stop the Jetty Server
     *
     * @throws Exception
     */
    public void stop() throws Exception {
        if (jettyServer != null) {
            jettyServer.stop();
        }
    }


}
