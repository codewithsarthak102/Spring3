package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;
import java.util.function.Supplier;

public class example7 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Vehicle volkswagen = new Vehicle();
        volkswagen.setName("volkswagen");
        Supplier<Vehicle> volkswagenSupplier = () -> volkswagen;

        Supplier<Vehicle> audiSupplier = () -> {
            Vehicle audi = new Vehicle();
            audi.setName("Audi");
            return audi;
        };

        Random random = new Random();
        var randomNumber = random.nextInt(10);
        System.out.println("Random Number is :" + randomNumber);

        if (randomNumber % 2 == 0) {
            context.registerBean("volkswagen", Vehicle.class, volkswagenSupplier);
        } else
            context.registerBean("audi", Vehicle.class, audiSupplier);

        Vehicle audiVehicle = null;
        Vehicle volksVehicle = null;
        try {
            volksVehicle = context.getBean("volkswagen", Vehicle.class);
        } catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
            System.out.println("Error While Creating Volkswagen Vehicle..");

        }try {
            audiVehicle = context.getBean("audi", Vehicle.class);
        }catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
            System.out.println("Error While Creating Audi Vehicle");
        }
        if (volksVehicle!=null)
            System.out.println("Programming Vehicle name from Spring Context is: "+ volksVehicle.getName());
        else
            System.out.println("Programming Vehicle name from Spring Context is: "+ audiVehicle.getName());

//       Vehicle vehicle = new Vehicle();
//       vehicle.printHello();

    }
}
