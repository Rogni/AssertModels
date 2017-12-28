package tests.models;

import assertmodels.IAbstractModel;

import java.util.HashMap;
import java.util.Map;

public class CustomModel2 implements IAbstractModel {

    private CustomModel1 childCustom;
    private TestModel1 childTest;

    public CustomModel1 getChildCustom() {
        return childCustom;
    }

    public void setChildCustom(CustomModel1 childCustom) {
        this.childCustom = childCustom;
    }

    public TestModel1 getChildTest() {
        return childTest;
    }

    public void setChildTest(TestModel1 childTest) {
        this.childTest = childTest;
    }

    public Map<String, Object> getValuesOfProperties() throws IllegalArgumentException, IllegalAccessException {
        Map<String, Object> result = new HashMap<>();

        result.put("title", childCustom.getTitle());
        result.put("test", childTest);
        return result;
    }
}
