import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalTime;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void mainTest() {
        HelloWorld tree =
                (HelloWorld) applicationContext.getBean("helloworld");
        HelloWorld leaf =
                (HelloWorld) applicationContext.getBean("helloworld");


        Cat one = (Cat) applicationContext.getBean("cat");
        Cat two = (Cat) applicationContext.getBean("cat");
        

        Assert.assertSame("Тест провален, не корректная настройка бина HelloWorld", tree, leaf);
        Assert.assertNotSame("Тест провален, не корректная настройка бина Cat", one, two);


        Animal animal = (Dog) applicationContext.getBean("dog");
        System.out.println(animal.getSound());
        Timer timer = (Timer) applicationContext.getBean("timer");
        System.out.println(timer.getTime());
        AnimalCage animalCage = (AnimalCage) applicationContext.getBean("animalcage");
        animalCage.sound();
    }
}
