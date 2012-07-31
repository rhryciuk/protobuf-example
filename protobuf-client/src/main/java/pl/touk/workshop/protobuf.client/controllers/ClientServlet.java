package pl.touk.workshop.protobuf.client.controllers;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import pl.touk.workshop.protobuf.messages.PersonMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User: rafal
 * Date: 7/31/12
 * Time: 7:59 PM
 */
public class ClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        PersonMessage.Person person = getPersonFromExternalService(userId);
        PrintWriter out = resp.getWriter();
        out.println(person.toString());
        out.println("size : " + person.getSerializedSize());
        out.println("isInitialized : " + person.isInitialized());
        out.println("hasEmail : " + person.hasEmail());
        out.close();
    }

    private PersonMessage.Person getPersonFromExternalService(String userId) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet("http://localhost:8080/protobuf-service?userId=" + userId);
        try {
            HttpResponse response = httpClient.execute(getRequest);
            return PersonMessage.Person.parseFrom(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
