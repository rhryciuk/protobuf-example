package pl.touk.workshop.protobuf.service.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pl.touk.workshop.protobuf.messages.PersonMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: rafal
 * Date: 7/31/12
 * Time: 7:52 PM
 */
public class ServiceServlet extends HttpServlet {

    private static final Log LOG = LogFactory.getLog(ServiceServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String userId = req.getParameter("userId");
        PersonMessage.Person personToReturn = null;
        if ("rafal".equalsIgnoreCase(userId)) {
            personToReturn = PersonMessage.Person.newBuilder()
                    .setEmail("rhr@touk.pl")
                    .setId(1)
                    .setName("Rafał")
                    .addPhone(PersonMessage.Person.PhoneNumber.newBuilder().setType(PersonMessage.Person.PhoneType.HOME).setNumber("123456789"))
                    .build();
        } else {
            personToReturn = PersonMessage.Person.newBuilder().setId(0).setName("name").build();
        }
        LOG.info(personToReturn.toString());
        try {
            personToReturn.writeTo(resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
