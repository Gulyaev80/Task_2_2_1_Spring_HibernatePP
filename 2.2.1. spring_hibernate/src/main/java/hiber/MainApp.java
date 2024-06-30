package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Иван","Иванов", "ivanov@hogwarts.com");
      User user2 = new User("Петр", "Петров", "petrov@hogwarts.com");
      User user3 = new User("Роман", "Романов", "romanov@hogwarts.com");
      User user4 = new User("Николай", "Николаев", "nikolaev@hogwarts.com");

      Car car1 = new Car("Mazda", 3);
      Car car2 = new Car("Vaz", 1);
      Car car3 = new Car("Vaz", 15);
      Car car4 = new Car("Mazda", 6);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));


      for (User user : userService.listUsers()) {
         System.out.println(user.getFirstName()+ " "+ user.getLastName()+" "+user.getEmail()+ " " +   user.getCar());

      }

      try {
         User userCar = userService.getUserByCar("Mazda", 3);
         System.out.println(userCar);
      } catch (NoResultException e) {
         System.out.println("У данного автомобиля нет пользователя");

      }

      context.close();
   }
}
