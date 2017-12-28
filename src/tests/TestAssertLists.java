package tests;

import assertmodels.AssertModelDelegate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tests.models.TestModel1;
import tests.models.TestModel2;

import java.util.ArrayList;
import java.util.Date;

public class TestAssertLists {

    AssertModelDelegate assertDelegate = new AssertModelDelegate();

    @Before
    public void before() {}

    @Test
    public void test1() throws Exception {

        Date d = new Date();
        TestModel1 model1 = new TestModel1(2, 3.1f);
        model1.childModel = new TestModel1(2, 1.0f);
        model1.secondChild = new TestModel2();
        model1.secondChild.date = d;
        model1.childModel.childModel = new TestModel1(2,6.0f);
        model1.list1 = new ArrayList<>();
        model1.list2 = new ArrayList<>();
        model1.list1.add("1");
        model1.list1.add("test");

        for (short i = 0; i < 4; ++i) {
            TestModel1 m = new TestModel1(i, i+0.6f);
            model1.list2.add(m);
        }

        TestModel1 model2 = new TestModel1(2, 3.1f);
        model2.childModel = new TestModel1(2, 1.0f);
        model2.secondChild = new TestModel2();
        model2.secondChild.date = d;

        assertDelegate.assertModels(model1, model2);

    }

    @Test
    public void test2() throws Exception {

        Date d = new Date();
        TestModel1 model1 = new TestModel1(2, 3.1f);
        model1.childModel = new TestModel1(2, 1.0f);
        model1.secondChild = new TestModel2();
        model1.secondChild.date = d;
        model1.childModel.childModel = new TestModel1(2,6.0f);
        model1.list1 = new ArrayList<>();
        model1.list2 = new ArrayList<>();
        model1.list1.add("1");
        model1.list1.add("test");

        for (short i = 0; i < 4; ++i) {
            TestModel1 m = new TestModel1(i, i+0.6f);
            model1.list2.add(m);
        }

        TestModel1 model2 = new TestModel1(2, 3.1f);
        model2.childModel = new TestModel1(2, 1.0f);
        model2.secondChild = new TestModel2();
        model2.secondChild.date = d;
        model2.list1 = new ArrayList<>();
        model2.list2 = new ArrayList<>();
        model2.list1.add("1");
        model2.list1.add("test");

        for (short i = 0; i < 4; ++i) {
            TestModel1 m = new TestModel1(i, i-0.6f);
            model2.list2.add(m);
        }

        assertDelegate.assertModels(model1, model2);

    }


    @After
    public void after() {}
}
