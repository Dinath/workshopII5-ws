/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apps;

import entities.EntityTrip;
import entities.EntityUser;
import java.io.Serializable;
import java.util.Calendar;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import services.EntityTripFacadeREST;
import services.EntityUserFacadeREST;
import utils.M;
import utils.R;

/**
 *
 * @author Soyer Alex <a.soyer@cubis-helios.com>
 */
@ApplicationScoped
@Named
public class Application implements Serializable {

    @Inject
    private EntityUserFacadeREST entityUserFacadeREST;
    @Inject
    private EntityTripFacadeREST entityTripFacadeREST;

    /**
     *
     * @param init
     */
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {

        if (R.DEBUG) {
            test();
        }

    }

    /**
     * Fire test within the app to test it
     *
     * - create some object to get for WS -
     */
    public void test() {

        // loading test
        M.debug("Application.test", "Loading tests...");

        // root user
        M.debug("Application.test", "Creating root user...");

        EntityUser user = new EntityUser();

        user.setEntityName("root");
        user.setEntityNameFirst("root");
        user.setEntityEmail("root@root.org");
        user.setEntityPassword(M.passwordEncode("root"));
        user.setEntityDateBirthday(Calendar.getInstance().getTime());
        user.setEntityNumberPhone("06-07-88-99-14");

        this.entityUserFacadeREST.create(user);

        M.debug("Application.test", "Creating root user... OK");

        // trips
        M.debug("Application.test", "Creating trip test1...");

        EntityTrip trip;

        trip = new EntityTrip();
        trip.setAmountOfTrip(20.00);
        trip.setEntityAddressFrom("Odyseum, Montpellier");
        trip.setEntityAddressTo("Gare du Nord, Paris");
        trip.setEntityName("Test 1");
        trip.setEntityDescription("Description...");
        trip.setNumberOfParticiper((short) 10);
        trip.setNumberOfUsersInside((short) 2);

        trip.setEntityUserOrganizer(user);
        trip.getEntityUsersParticiper().add(user);

        this.entityTripFacadeREST.create(trip);

        M.debug("Application.test", "Creating trip test1... OK");

        // test done
        M.debug("Application.test", "Loading tests done !");
    }

}
