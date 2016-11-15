/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.EntityUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        } catch (NoResultException e) {

            return Response.status(404).build();
        }

        return Response.ok().entity(entity).build();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(EntityUser entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, EntityUser entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public EntityUser find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<EntityUser> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<EntityUser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
