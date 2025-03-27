import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalTime;

@Configuration
public class AppConfig {
 
    @Bean(name="helloworld")
    public HelloWorld getHelloWorld() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setMessage("Hello World!");
        return helloWorld;
    }

    @Bean(name = "cat")
    @Scope("prototype")
    public Cat makeSoundCat(){
        Cat cat = new Cat();
        cat.setSound("Meow");
        return cat;
    }

    @Bean(name = "dog")
    @Scope("prototype")
    public Dog makeSoundDog(){
        Dog dog = new Dog();
        dog.setSound("Woow-woow");
        return dog;
    }
    @Bean("timer")
    public Timer timer() {
        return new Timer();
    }
    @Bean("animalcage")
    public AnimalCage animalCage(@Qualifier("dog") Animal animal, Timer timer) {
        return new AnimalCage(animal, timer);
    }
}
