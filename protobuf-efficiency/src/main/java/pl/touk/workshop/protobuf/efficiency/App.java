package pl.touk.workshop.protobuf.efficiency;


import org.codehaus.jackson.map.ObjectMapper;
import pl.touk.workshop.protobuf.messages.PersonMessage;

import java.io.*;

import static pl.touk.workshop.protobuf.messages.PersonMessage.Person;

public class App
{
    public static void main( String[] args ) {
        try {
            protobufTimes();
            System.out.println("========================================================================================");
            jsonTimes();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static void protobufTimes() throws IOException {
        long start = System.currentTimeMillis();
        Person.Builder builder = Person.newBuilder().setId(4).setName("name").setEmail("test@touk.pl");
        for (int i = 0 ; i < 1000 ; ++i) {
            builder.addPhone(createProtobufPhoneNumber());
        }
        Person person = builder.build();
        long stop = System.currentTimeMillis();
        System.out.println("Object creation : " + (stop - start) + " ms");


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        start = System.currentTimeMillis();
        person.writeTo(out);
        stop = System.currentTimeMillis();
        System.out.println("serialization : " + (stop - start) + " ms");
        System.out.println("Serialized size : " + out.size());

        byte[] array = out.toByteArray();
        InputStream in = new ByteArrayInputStream(array);

        start = System.currentTimeMillis();
        Person p = Person.parseFrom(in);
        stop = System.currentTimeMillis();
        System.out.println("deserialization : " + (stop - start) + " ms");
    }

    private static void jsonTimes() throws IOException {
        long start = System.currentTimeMillis();
        PersonForJson person = new PersonForJson(4, "name", "test@touk.pl");
        for (int i = 0 ; i < 1000 ; ++i) {
            person.addPhoneNumber(createJsonPhoneNumber());
        }
        long stop = System.currentTimeMillis();
        System.out.println("Object creation : " + (stop - start) + " ms");


        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        start = System.currentTimeMillis();
        mapper.writeValue(out, person);
        stop = System.currentTimeMillis();
        System.out.println("serialization : " + (stop - start) + " ms");
        System.out.println("Serialized size : " + out.size());

        byte[] array = out.toByteArray();
        InputStream in = new ByteArrayInputStream(array);

        start = System.currentTimeMillis();
        PersonForJson p = mapper.readValue(in, PersonForJson.class);
        stop = System.currentTimeMillis();
        System.out.println("deserialization : " + (stop - start) + " ms");

    }

    private static Person.PhoneNumber createProtobufPhoneNumber() {
        return Person.PhoneNumber.newBuilder().setNumber("123456789").setType(Person.PhoneType.HOME).build();
    }

    private static PersonForJson.PhoneNumber createJsonPhoneNumber() {
        return new PersonForJson.PhoneNumber("1234556789", PersonForJson.PhoneNumber.Type.HOME);
    }

}
