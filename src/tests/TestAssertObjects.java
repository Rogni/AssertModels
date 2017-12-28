package tests;

import assertmodels.AssertModelDelegate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tests.models.TestModel1;
import tests.models.TestModel2;

import java.util.Date;

public class TestAssertObjects {

    private AssertModelDelegate assertDelegate = new AssertModelDelegate();

    @Before
    public void before() {}

    @Test
    public void test1() throws Exception {
        TestModel1 model1 = new TestModel1(2, 2.1f);
        TestModel1 model2 = new TestModel1(2, 2.1f);

        assertDelegate.assertModels(model1, model2);
    }

    @Test
    public void test2() throws Exception {
        TestModel1 model1 = new TestModel1(3, 2.1f);
        TestModel1 model2 = new TestModel1(2, 2.1f);

        assertDelegate.assertModels(model1, model2);
    }

    @Test
    public void test3() throws Exception {
        TestModel1 model1 = new TestModel1(2, 2.1f);
        model1.childModel = new TestModel1(1, 1.0f);

        TestModel1 model2 = new TestModel1(2, 2.1f);
        model2.childModel = new TestModel1(1, 1.0f);

        assertDelegate.assertModels(model1, model2);
    }

    @Test
    public void test4() throws Exception {
        TestModel1 model1 = new TestModel1(2, 2.1f);
        model1.childModel = new TestModel1(2, 1.0f);

        TestModel1 model2 = new TestModel1(2, 2.1f);
        model2.childModel = new TestModel1(1, 1.0f);

        assertDelegate.assertModels(model1, model2);
    }

    @Test
    public void test5() throws Exception {
        TestModel1 model1 = new TestModel1(2, 3.1f);
        model1.childModel = new TestModel1(2, 1.0f);

        TestModel1 model2 = new TestModel1(2, 2.1f);
        model2.childModel = new TestModel1(1, 1.0f);

        assertDelegate.assertModels(model1, model2);
    }

    @Test
    public void test6() throws Exception {
        Date d = new Date();
        TestModel1 model1 = new TestModel1(2, 3.1f);
        model1.childModel = new TestModel1(2, 1.0f);
        model1.secondChild = new TestModel2();
        model1.secondChild.date = d;

        TestModel1 model2 = new TestModel1(2, 3.1f);
        model2.childModel = new TestModel1(2, 1.0f);
        model2.secondChild = new TestModel2();
        model2.secondChild.date = d;
        model2.childModel.childModel = new TestModel1(2,6.0f);
        assertDelegate.assertModels(model1, model2);
    }

    @Test
    public void test7() throws Exception {
        Date d = new Date();
        TestModel1 model1 = new TestModel1(2, 3.1f);
        model1.childModel = new TestModel1(2, 1.0f);
        model1.secondChild = new TestModel2();
        model1.secondChild.date = d;
        model1.childModel.childModel = new TestModel1(2,6.0f);

        TestModel1 model2 = new TestModel1(2, 3.1f);
        model2.childModel = new TestModel1(2, 1.0f);
        model2.secondChild = new TestModel2();
        model2.secondChild.date = d;

        assertDelegate.assertModels(model1, model2);
    }

    @After
    public void after() {}
}
