package tests.models;

import assertmodels.AbstractModel;
import assertmodels.IAbstractModel;

import java.util.List;

public class TestModel1 extends AbstractModel {
    public int firstNumber = 0;
    public float secondNumber = 0;
    public TestModel1 childModel;
    public List<String> list1;
    public List<IAbstractModel> list2;
    public TestModel2 secondChild;

    public TestModel1() {}

    public TestModel1(int firstNumber, float secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

}
