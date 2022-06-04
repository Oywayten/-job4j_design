package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import org.json.JSONObject;

public class Main2 {
    public static void main(String[] args) {
        final Truck truck = new Truck(true, 20, "Volvo 200",
                new Contact("+79110010011"), new String[]{"Viktor", "Maria"});
        JSONObject object = new JSONObject(truck);
        System.out.println(object);
        String jsonString = object.toString();
        Gson gson = new Gson();
        Truck result = gson.fromJson(jsonString, Truck.class);
        System.out.println(result.getModel());
    }
}
