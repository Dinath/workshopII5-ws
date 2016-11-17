/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.EntityUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import utils.M;

/**
 *
 * @author Soyer Alex <a.soyer@cubis-helios.com>
 */
@Stateless
@Path("user")
public class EntityUserFacadeREST extends AbstractFacade<EntityUser> {

    @PersistenceContext(unitName = "WorkshopI5PU")
    private EntityManager em;

    public EntityUserFacadeREST() {
        super(EntityUser.class);
    }

    @POST
    @Path("connection")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response actionConnection(EntityUser entity) {

        try {
            entity = (EntityUser) this.em.createQuery("select o from EntityUser as o where o.entityPassword = :password and o.entityEmail = :email")
                    .setParameter("email", entity.getEntityEmail())
                    .setParameter("password", M.passwordEncode(entity.getEntityPassword()))
                    .getSingleResult();
        } catch (Exception e) {

            return Response.status(404).build();
        }

        return Response.ok().entity(entity).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response _create(EntityUser entity) {

        try {

            this.em.createQuery("select o from EntityUser as o where o.entityEmail = :email")
                    .setParameter("email", entity.getEntityEmail())
                    .getSingleResult();

        } catch (java.lang.Exception e) {

            try {
                super.create(entity);
                return Response.ok().entity(entity).build();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return Response.status(400).build();
        }

        return Response.status(405).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response _edit(EntityUser entity) {

        try {
            super.edit(entity);

        } catch (java.lang.Exception e) {

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

        } catch (java.lang.Exception e) {

            e.printStackTrace();
            return Response.status(400).build();
        }

        return Response.ok().status(200).build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id) {

        try {

            EntityUser entity = super.find(id);

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

        } catch (java.lang.Exception e) {

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
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return Response.status(400).build();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
