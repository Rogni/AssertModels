package tests.models;

import assertmodels.IAbstractModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomModel1 implements IAbstractModel {

    private int number;
    private String type = "";
    private String name = "";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return String.format("%s %s %s", getName(), getType(), getNumber());
    }

    public Map<String, Object> getValuesOfProperties() throws IllegalArgumentException, IllegalAccessException {
        Map<String, Object> result = new HashMap<>();
        result.put("name", getName());
        result.put("type", getType());
        result.put("title", getTitle());
        return result;
    }
}
