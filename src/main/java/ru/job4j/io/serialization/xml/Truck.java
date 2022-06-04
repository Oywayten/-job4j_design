package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement
@XmlAccessorType
public class Truck {
    @XmlAttribute
    private boolean isTipper;
    @XmlAttribute
    private int loadCapacity;
    @XmlAttribute
    private String model;
    @XmlElement
    private Contact contact;
    @XmlElementWrapper
    @XmlElement(name = "passenger")
    private String[] passengers;

    public Truck() {

    }

    public Truck(boolean isTipper, int loadCapacity, String model, Contact contact, String... passengers) {
        this.isTipper = isTipper;
        this.loadCapacity = loadCapacity;
        this.model = model;
        this.contact = contact;
        this.passengers = passengers;
    }

    public static void main(String[] args) throws Exception {
        Truck truck = new Truck(false, 3, "Gazel", new Contact("+7911"), "Arnold", "forbidden");
        JAXBContext context = JAXBContext.newInstance(Truck.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(truck, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Truck result = (Truck) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }

    @Override
    public String toString() {
        return "Truck{" + "isTipper=" + isTipper + ", loadCapacity="
                + loadCapacity + ", model='" + model + '\'' + ", contact="
                + contact + ", passengers=" + Arrays.toString(passengers) + '}';
    }
}
