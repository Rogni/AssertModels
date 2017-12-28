package tests;

import assertmodels.AssertModelDelegate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tests.models.CustomModel1;
import tests.models.CustomModel2;
import tests.models.TestModel1;
import tests.models.Model;


public class TestCustomModel {
    AssertModelDelegate assertDelegate = new AssertModelDelegate();

    @Before
    public void before() {}

    @Test
    public void test1() throws Exception {
        CustomModel1 model1 = new CustomModel1();
        CustomModel1 model2 = new CustomModel1();

        assertDelegate.assertModels(model1, model2);
    }

    @Test
    public void test2() throws Exception {
        CustomModel1 model1 = new CustomModel1();
        model1.setNumber(4);
        model1.setName("test");
        model1.setType("type1");

        CustomModel1 model2 = new CustomModel1();
        model2.setNumber(3);
        model2.setName("test");
        model2.setType("type2");

        assertDelegate.assertModels(model1, model2);
    }

    @Test
    public void test3() throws Exception {
        CustomModel1 cmodel1 = new CustomModel1();
        cmodel1.setNumber(4);
        cmodel1.setName("test");
        cmodel1.setType("type1");

        CustomModel1 cmodel2 = new CustomModel1();
        cmodel2.setNumber(3);
        cmodel2.setName("test");
        cmodel2.setType("type2");

        TestModel1 tmodel1 = new TestModel1(3, 4.0f);
        TestModel1 tmodel2 = new TestModel1(2, 4.0f);
        tmodel2.childModel = new TestModel1(1, 1.0f);
        tmodel1.childModel = new TestModel1(0, 0.0f);
        tmodel1.childModel.childModel = new TestModel1(-1, -1.0f);

        CustomModel2 model1 = new CustomModel2();
        model1.setChildCustom(cmodel1);
        model1.setChildTest(tmodel1);

        CustomModel2 model2 = new CustomModel2();
        model2.setChildCustom(cmodel2);
        model2.setChildTest(tmodel2);


        assertDelegate.assertModels(model1, model2);
    }

    @Test
    public void test4() throws Exception {
        Model father = new Model(0, "Oberon");
        father.childs.add(new Model(1, "Corwin"));
        father.childs.add(new Model(2, "Eric"));
        System.out.print(father.getValuesForComparison());
    }


    @After
    public void after() {}
}
