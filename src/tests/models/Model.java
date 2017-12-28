package tests.models;

import assertmodels.AbstractModel;

import java.util.ArrayList;
import java.util.List;


public class Model extends AbstractModel {
    public Model(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public String name;
    public Integer id;
    public List<Model> childs = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("%d: %s (%s)", id, name, getClass().toString());
    }
}