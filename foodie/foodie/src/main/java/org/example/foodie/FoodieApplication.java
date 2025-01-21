package org.example.foodie;

import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.example.foodie.dao.*;
import org.example.foodie.entity.*;
import org.example.foodie.service.RestaurantService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalTime;

@SpringBootApplication
public class FoodieApplication {
    private final RestaurantDao restaurantDao;
    private final CategoryDao categoryDao;
    private final DishDao dishDao;
    private final CustomerDao customerDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;



    public FoodieApplication(RestaurantDao restaurantDao, CategoryDao categoryDao, DishDao dishDao, CustomerDao customerDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.restaurantDao = restaurantDao;
        this.categoryDao = categoryDao;
        this.dishDao = dishDao;
        this.customerDao = customerDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Transactional
    @Profile("dev")
    public ApplicationRunner runner() {
        return args -> {
//            Dish dish1=new Dish("Yorkshire Lamb Patties",
//                    "Lamb patties which melt in your mouth, and are quick and easy to make. Served hot with a crisp salad.",14.00,"62908867a48e4.jpg");
//            Dish dish2=new Dish("Lobster Thermidor","Lobster Thermidor is a French dish of lobster meat cooked in a rich wine sauce, stuffed back into a lobster shell, and browned.",
//                    36.00,"629089fee52b9.jpg");
//            Dish dish3=new Dish("Chicken Madeira",
//                    "Chicken Madeira, like Chicken Marsala, is made with chicken, mushrooms, and a special fortified wine. But, the wines are different",
//                    23.0,"62908bdf2f581.jpg");
//            Dish dish4=new Dish("Stuffed Jacket Potatoes",
//                    "Deep fry whole potatoes in oil for 8-10 minutes or coat each potato with little oil. Mix the onions, garlic, tomatoes and mushrooms. Add yoghurt, ginger, garlic, chillies, coriander",8.00,
//                    "62908d393465b.jpg");
//            Dish dish5=new Dish("Pink Spaghetti Gamberoni",
//                    "Spaghetti with prawns in a fresh tomato sauce. This dish originates from Southern Italy and with the combination of prawns, garlic, chilli and pasta. Garnish each with remaining tablespoon parsley.",21.00,
//                    "606d7491a9d13.jpg");
//            Dish dish6=new Dish("Cheesy Mashed Potato","Deliciously Cheesy Mashed Potato. The ultimate mash for your Thanksgiving table or the perfect accompaniment to vegan sausage casserole. Everyone will love it\\'s fluffy",
//                    5.0,"606d74c416da5.jpg");
//            Dish dish7=new Dish("Crispy Chicken Strips","Fried chicken strips, served with special honey mustard sauce.",
//                    8.0,"606d74f6ecbbb.jpg");
//            Dish dish8=new Dish("Lemon Grilled Chicken And Pasta",
//                    "Marinated rosemary grilled chicken breast served with mashed potatoes and your choice of pasta.",
//                    11.00,"606d752a209c3.jpg");
//            Dish dish9=new Dish("Vegetable Fried Rice",
//                    "Chinese rice wok with cabbage, beans, carrots, and spring onions.",
//                    5.00,"606d7575798fb.jpg");
//            Dish dish10=new Dish("Prawn Crackers",
//                    "12 pieces deep-fried prawn crackers",
//                    7.00,"606d75a7e21ec.jpg");
//            Dish dish11=new Dish("Spring Rolls",
//                    "Lightly seasoned shredded cabbage, onion and carrots, wrapped in house made spring roll wrappers, deep fried to golden brown.",
//                    6.00,"606d75ce105d0.jpg");
//            Dish dish12=new Dish("Manchurian Chicken",
//                    "Chicken pieces slow cooked with spring onions in our house made manchurian style sauce.",
//                    11.0,"606d7600dc54c.jpg"
//            );
//            Dish dish13=new Dish("Buffalo Wings",
//                    "Fried chicken wings tossed in spicy Buffalo sauce served with crisp celery sticks and Blue cheese dip.",
//                    11.00,"606d765f69a19.jpg");
//            Dish dish14=new Dish("Mac N Cheese Bites",
//                    "Served with our traditional spicy queso and marinara sauce.",9.0,"606d768a1b2a1.jpg");
//            Dish dish15=new Dish("Signature Potato Twisters",
//                    "Spiral sliced potatoes, topped with our traditional spicy queso, Monterey Jack cheese, pico de gallo, sour cream and fresh cilantro.",
//                    6.0,"606d76ad0c0cb.jpg");
//            Dish dish16=new Dish("Meatballs Penne Pasta",
//                    "Garlic-herb beef meatballs tossed in our house-made marinara sauce and penne pasta topped with fresh parsley.",
//                    10.00,"606d76eedbb99.jpg");
//
//            Category category1 = new Category();
//            category1.setCategoryName("Chinese Restaurant");
//
//            Category category2 = new Category();
//            category2.setCategoryName("Western Restaurant");
//
//            Restaurant restaurant1 = new Restaurant("Dolphin","Kandaw Gyi, Nat Mauk Road, Bahan Township","09-109110969","dolphin@gmail.com", LocalTime.of(9,0),LocalTime.of(23,0),"606d720b5fc71");
//            Restaurant restaurant2 = new Restaurant("Foodie","Hlaing","55-555-555","foodie@gmail.com", LocalTime.of(9,0),LocalTime.of(23,0),"6290af6f81887");
//
//            category1.addRestaurant(restaurant1);
//            category2.addRestaurant(restaurant2);
//
//            restaurant1.addDish(dish1);
//            restaurant1.addDish(dish2);
//            restaurant1.addDish(dish3);
//            restaurant1.addDish(dish4);
//            restaurant1.addDish(dish5);
//            restaurant1.addDish(dish6);
//            restaurant1.addDish(dish7);
//            restaurant1.addDish(dish8);
//
//            restaurant2.addDish(dish9);
//            restaurant2.addDish(dish10);
//            restaurant2.addDish(dish11);
//            restaurant2.addDish(dish12);
//            restaurant2.addDish(dish13);
//            restaurant2.addDish(dish14);
//            restaurant2.addDish(dish15);
//            restaurant2.addDish(dish16);
//
//            categoryDao.save(category1);
//            categoryDao.save(category2);
//
//            restaurantDao.save(restaurant1);
//            restaurantDao.save(restaurant2);
//
//            dishDao.save(dish1);
//            dishDao.save(dish2);
//            dishDao.save(dish3);
//            dishDao.save(dish4);
//            dishDao.save(dish5);
//            dishDao.save(dish6);
//            dishDao.save(dish7);
//            dishDao.save(dish8);
//            dishDao.save(dish9);
//            dishDao.save(dish10);
//            dishDao.save(dish11);
//            dishDao.save(dish12);
//            dishDao.save(dish13);
//            dishDao.save(dish14);
//            dishDao.save(dish15);
//            dishDao.save(dish16);
//
//            Customer c1 = new Customer("John Doe", "john", passwordEncoder.encode("12345"), "john@gmail.com");
//            Customer c2 = new Customer("Mary Smith", "mary", passwordEncoder.encode("12345"), "mary@gmail.com");
//
//            Role r1 = new Role();
//            r1.setRoleName("ROLE_ADMIN");
//            Role r2 = new Role();
//            r2.setRoleName("ROLE_USER");
//            c1.addRole(r1);
//            c2.addRole(r2);
//
//            customerDao.save(c1);
//            customerDao.save(c2);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(FoodieApplication.class, args);
    }

}
