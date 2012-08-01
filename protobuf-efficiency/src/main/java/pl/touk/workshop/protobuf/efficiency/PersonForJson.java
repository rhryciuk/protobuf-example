package pl.touk.workshop.protobuf.efficiency;


import pl.touk.workshop.protobuf.messages.PersonMessage;

import java.util.ArrayList;
import java.util.List;

public class PersonForJson {

    private int id;

    private String name;

    private String mail;

    private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

    public PersonForJson() {
    }


    public PersonForJson(int id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void addPhoneNumber(PhoneNumber pn) {
        this.phoneNumbers.add(pn);
    }

    public static class PhoneNumber {

        private String number;

        private Type type;

        public PhoneNumber() {
        }

        public PhoneNumber(String number, Type type) {
            this.number = number;
            this.type = type;
        }

        public String getNumber() {
            return number;
        }

        public Type getType() {
            return type;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public static enum Type {HOME, OFFICE;}
    }
}
