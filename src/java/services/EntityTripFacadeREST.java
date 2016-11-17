/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.EntityTrip;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Soyer Alex <a.soyer@cubis-helios.com>
 */
@Stateless
@Path("trip")
public class EntityTripFacadeREST extends AbstractFacade<EntityTrip> {

    @PersistenceContext(unitName = "WorkshopI5PU")
    private EntityManager em;

    public EntityTripFacadeREST() {
        super(EntityTrip.class);
    }

    @POST
    @Path("order-price/{i}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listOfTripsLowerPrice(@PathParam("i") String i) {

        List<EntityTrip> trips = new ArrayList<>();

        try {

            switch (i) {
                case "0":
                    trips = this.em.createQuery("select o from EntityTrip as o ORDER BY o.numberOfParticiper / o.amountOfTrip desc").getResultList();
                    break;

                case "1":
                    trips = this.em.createQuery("select o from EntityTrip as o ORDER BY o.numberOfParticiper / o.amountOfTrip asc").getResultList();
                    break;

                default:
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).build();
        }

        return Response.ok().entity(trips).build();
    }

    @POST
    @Path("available")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listOfAvailableTrips(EntityTrip trip) {

        List<EntityTrip> trips = new ArrayList<>();

        try {
            trips = this.em.createQuery("select o from EntityTrip as o where o.entityAddressTo like :country and o.entityDateTo = :date")
                    .setParameter("country", "%" + trip.getEntityAddressTo() + "%")
                    .setParameter("date", trip.getEntityDateTo(), TemporalType.DATE)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).build();
        }

        if (trips.size() > 0) {
            return Response.ok().entity(trips).build();
        } else {
            return Response.status(404).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response _create(EntityTrip entity) {

        try {
            super.create(entity);

        } catch (Exception e) {

            e.printStackTrace();
            return Response.status(400).build();
        }

        return Response.ok().entity(entity).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response _edit(EntityTrip entity) {

        try {
            super.edit(entity);

        } catch (Exception e) {

            e.printStackTrace();
            return Response.status(400).build();
        }

        return Response.ok().status(200).build();
    }

    @DELETE
    @Path("{id}")
    public Response _remove(@PathParam("id") Long id) {

        try {
            super.remove(super.find(id));

        } catch (Exception e) {

            e.printStackTrace();
            return Response.status(400).build();
        }

        return Response.ok().status(200).build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id) {

        try {

            EntityTrip entity = super.find(id);

            if (entity != null) {
                return Response.ok().entity(entity).build();
            } else {
                return Response.status(404).build();
            }

        } catch (java.lang.Exception e) {

            e.printStackTrace();
            return Response.status(400).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response _findAll() {

        try {
            return Response.ok().entity(super.findAll()).build();

        } catch (Exception e) {

            e.printStackTrace();
            return Response.status(400).build();
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countREST() {

        try {
            return Response.ok().entity(super.count()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).build();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
