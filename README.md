You are working for a company that licenses and customizes a software application to keep track of inventory in stores. Your job as a software developer is to customize this application to meet a specific customer’s needs. You will choose any type of customer you would like, but it must sell a product composed of parts. An example of products versus parts would be a customer that’s a bicycle shop: a bicycle is a product, and a set of two matching wheels is a part (do not use the bicycle shop example in your project).



You have been provided with a Spring application with a Java backend, a generic HTML user interface to use in the design and development of the system, and a UML class diagram to assist you in your work (see the attached “UML Class Diagram”). You can find a user guide to help assist with the inventory management application in the attachments (see “Shop Inventory Management User Guide”).


Changes Log:

C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.
File:
mainscreen.html
MainscreenController.java

Lines:
18-21 of mainscreen.html Edited to rename shop and part category.
```
 <div class="container">
    <h1>Ruby's Bakery</h1>
    <hr>
    <h2>Specials</h2>
```
30-31 renamed add buttons
```
    <a th:href="@{/showFormAddInPart}" class="btn btn-primary btn-sm mb-3">Add Daily Specials</a>
    <a th:href="@{/showFormAddOutPart}" class="btn btn-primary btn-sm mb-3">Add Weekend Specials</a>
```

MainscreenController.java
Lines:
22 and 37: Edited name to correct typo, corrected name of file 
Note: Do not remove any elements that were included in the screen. You may add any additional elements you would like or any images, colors, and styles, although it is not required.


D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.
Files:
about.html
AboutPageController.java

about.html
Lines:
1-30
Created an about page and short description of shop, styled after main screen.
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>About Ruby's Bakery</title>
</head>
<body>
<div class="container">
    <h1>About Ruby's Bakery</h1>


    <p> Ruby's Bakery is a small, family owned bakery that strives to make our community better, one pastry at a time.
    </p>

    <p>
        We started in a home kitchen and were able to branch out to this brick and mortar location due to the generous support of our friends and family.
        Every week, we wil feature a set of unique pastries alongside our crowd favorites.
    </p>
    <a href="/mainscreen" class ="btn btn-primary btn-sm mb-3">Home</a>
</div>
</body>
</html>
```


AboutPageController.java
Lines:
1-32 
Added controller and @mapping to allow access to about.html
```
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutPageController {
    private PartService partService;
    private ProductService productService;
    private List<Part> theParts;
    private List<Product> theProducts;

    public AboutPageController(PartService partService, ProductService productService) {
        this.partService = partService;
        this.productService = productService;
    }

    @GetMapping({"/about"})
    public String about() {
        return "about.html";
    }
}

```

E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.
Files:
BootStrapData.java
application.properties

BootStrapData.java
Lines:
32, 35, 38: added repo for InhouseParts
```
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final InhousePartRepository inhousePartRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, InhousePartRepository inhousePartRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.inhousePartRepository = inhousePartRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
    }
```

42-118: added 3 Inhouse and 2 Outsourced parts along with conditional if() statement for inventory
```
    @Override
    public void run(String... args) throws Exception {

        if (inhousePartRepository.count() == 0) {

            List<InhousePart> inhouseParts = (List<InhousePart>) inhousePartRepository.findAll();

            InhousePart ih1 = new InhousePart();
            ih1.setName("Monday Muffin");
            ih1.setInv(30);
            ih1.setPrice(2.50);
            ih1.setId(2);
            inhousePartRepository.save(ih1);
            InhousePart thePart = null;
            inhouseParts = (List<InhousePart>) inhousePartRepository.findAll();
            for (InhousePart part : inhouseParts) {
                if (part.getName().equals("Monday Muffin")) thePart = part;
            }

            InhousePart ih2 = new InhousePart();
            ih2.setName("Tuesday Tarts");
            ih2.setInv(30);
            ih2.setPrice(3.50);
            ih2.setId(3);
            inhousePartRepository.save(ih2);
            thePart = null;
            inhouseParts = (List<InhousePart>) inhousePartRepository.findAll();
            for (InhousePart part : inhouseParts) {
                if (part.getName().equals("Tuesday Tarts")) thePart = part;
            }

            InhousePart ih3 = new InhousePart();
            ih3.setName("Friday Fritter");
            ih3.setInv(30);
            ih3.setPrice(4.00);
            ih3.setId(4);
            inhousePartRepository.save(ih3);
            thePart = null;
            inhouseParts = (List<InhousePart>) inhousePartRepository.findAll();
            for (InhousePart part : inhouseParts) {
                if (part.getName().equals("Friday Fritter")) thePart = part;
            }
        }

        if (outsourcedPartRepository.count() == 0) {

            List<OutsourcedPart> outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();

            OutsourcedPart o1 = new OutsourcedPart();
            o1.setCompanyName("Oliver's Patisserie");
            o1.setName("Caramel Cruffin");
            o1.setInv(30);
            o1.setPrice(7.50);
            o1.setId(998);
            outsourcedPartRepository.save(o1);
            OutsourcedPart theOutPart = null;
            outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
            for (OutsourcedPart part : outsourcedParts) {
                if (part.getName().equals("Caramel Cruffin")) theOutPart = part;
            }

            System.out.println(theOutPart.getCompanyName());

            OutsourcedPart o2 = new OutsourcedPart();
            o2.setCompanyName("Marley's Pastry Shop");
            o2.setName("Berry Cronut");
            o2.setInv(30);
            o2.setPrice(8.50);
            o2.setId(997);
            outsourcedPartRepository.save(o2);
            theOutPart = null;
            outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
            for (OutsourcedPart part : outsourcedParts) {
                if (part.getName().equals("Berry Cronut")) theOutPart = part;
            }

            System.out.println(theOutPart.getCompanyName());
```

141-155: Added 5 Products
```
if (productRepository.count() == 0) {

            Product pastry1 = new Product("Croissants", 3.50, 30);
            Product pastry2 = new Product("Muffins", 4.50, 30);
            Product pastry3 = new Product("Cookies", 3.00, 30);

            Product bread1 = new Product("Japanese Milk Bread Loaf", 15.00, 25);
            Product bread2 = new Product("Artisanal Brioche Loaf", 17.00, 25);

            productRepository.save(pastry1);
            productRepository.save(pastry2);
            productRepository.save(pastry3);
            productRepository.save(bread1);
            productRepository.save(bread2);
        }
```

application.properties
Lines:
6: updated name for database and version number 
```
spring.datasource.url=jdbc:h2:file:~/PhamDatabasev.3
```

Note: Make sure the sample inventory is added only when both the part and product lists are empty. When adding the sample inventory appropriate for the store, the inventory is stored in a set so duplicate items cannot be added to your products. When duplicate items are added, make a “multi-pack” part.


F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
• The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
•  Display a message that indicates the success or failure of a purchase.

Files:
created success.html
created failure.html
mainscreen.html
BuyProductsController.java
application.properties

success.html
Lines:
1-20: created landing page for successful purchase of products.
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Success</title>
</head>
<body>
<h4>Product purchased</h4>

<a href="/mainscreen" class ="btn btn-primary btn-sm mb-3">Home</a>
</body>
</html>
```

failure.html
Lines:
1-20: created landing page for failure to purchase of products.
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Failure</title>
</head>
<body>
<h4>Purchase not successful. The product you chose may be out of stock.</h4>

<a href="/mainscreen" class ="btn btn-primary btn-sm mb-3">Home</a>
</body>
</html>
```
mainscreen.html
Lines:
86: added "Buy Now" button.
```
    <a th:href="@{/buyProducts(productID=${tempProduct.id})}" class="btn btn-primary btn-sm mb-3">Buy Now</a>
```

BuyProductsController.java
Lines:
1-39: created new controller for "Buy Now" button in product section of mainscreen.
```
package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Controller
public class BuyProductsController {

    @Autowired
    private ProductRepository productRepository;

@GetMapping("/buyProducts")
    public String buyProducts(@RequestParam("productID") long theId, Model theModel) {
        Optional<Product> product = productRepository.findById(theId);
        int inv = product.get().getInv();

        if (inv == 0) {
            return "/failure";
        } else {
            product.get().setInv(inv - 1);
            productRepository.save(product.get());
            return "/success";
        }
    }
}
```
application.properties
Lines:
6: updated name for database and version number
```
spring.datasource.url=jdbc:h2:file:~/PhamDatabasev.4
```


G.  Modify the parts to track maximum and minimum inventory by doing the following:
Files:
created InventoryValidator.java
created ValidInventory.java
BootStrapData.java
Part.java
InhousePartForm.html
OutsourcedPartForm.html
mainscreen.html


•  Add additional fields to the part entity for maximum and minimum inventory.
Part.java
Lines:
34-39: added minimum and maximum inventory parameters
```
@Min(value = 0, message = "Min inventory value must be positive")
int minInv;

@Min(value = 0, message = "Max inventory value must be positive")
int maxInv;
```
93-108: created getter and setters methods for minimum and maximum values
```
    public int getMinInv() {
        return minInv;
    }

    public void setMinInv(int minInv) {
        this.minInv = minInv;
    }

    public int getMaxInv() {
        return maxInv;
    }

    public void setMaxInv(int maxInv) {
        this.maxInv = maxInv;
    }

    public Set<Product> getProducts() {
        return products;
    }
```

mainscreen.html
Lines:
38-39, 48-49: added fields for minimum and maximum inventory to table
```
<th>Min Inventory</th>
<th>Max Inventory</th>
            
<td th:text="${tempPart.minInv}">1</td>
<td th:text="${tempPart.maxInv}">1</td>
```

•  Modify the sample inventory to include the maximum and minimum fields.
BootStrapData.java
Lines:
54-55, 68-69, 82-83: added Min and Max values to InhouseParts
```
ih1.setMinInv(1);
ih1.setMaxInv(150);

ih2.setMinInv(1);
ih2.setMaxInv(150);

ih3.setMinInv(1);
ih3.setMaxInv(150);
```
102-103, 119-120: added Min and Max values to Outsourcedparts
```
o1.setMinInv(1);
o1.setMaxInv(200);

o2.setMinInv(1);
o2.setMaxInv(250);
```

•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
InhousePartForm.html
Lines:
10-24, 35-36: edited field names for Name, Price, Count, and ID
```
<h1>Daily Specials Detail</h1>
<form action="#" th:action="@{/showFormAddInPart}" th:object="${inhousepart}" method="POST"}>

<!-- Add hidden form field to handle update -->
    <p><input type="hidden" th:field="*{id}"/></p>

<p>Name:
    <input type="text" th:field="*{name}" placeholder="Name" class="form-control mb-4 col-4"/></p>

<p>Price:
    <input type="text" path="price" th:field="*{price}" placeholder= "Price" class="form-control mb-4 col-4"/></p>
<p th:if="${#fields.hasErrors('price')}" th:errors="*{price}">Price Error</p>

<p>Count:
    <input type="text" path="inv" th:field="*{inv}" placeholder="Inventory" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('inv')}" th:errors="*{inv}">Inventory Error</p>
```
27-33: added Maximum and Minimum Count fields and enabled input for both fields
```
    <p>Minimum Count:
        <input type="text" path="minInv" th:field="*{minInv}" placeholder="Min Inventory" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('minInv')}" th:errors="*{minInv}">Min Inventory Error</p>

    <p>Maximum Count:
        <input type="text" path="maxInv" th:field="*{maxInv}" placeholder="Max Inventory" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('maxInv')}" th:errors="*{maxInv}">Min Inventory Error</p>
```

OutsourcedPartForm.html
Lines:
17-26, 36-37: edited field names for Name, Price, Count, and Source Store.
```
<p>Name:
    <input type="text" th:field="*{name}" placeholder="Name" class="form-control mb-4 col-4"/></p>

<p>Price:
    <input type="text" th:field="*{price}" placeholder= "Price" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('price')}" th:errors="*{price}">Price Error</p>

<p>Count:
    <input type="text" th:field="*{inv}" placeholder="Inventory" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('inv')}" th:errors="*{inv}">Inventory Error</p>
    
<p>Source Store:
    <input type="text" th:field="*{companyName}" placeholder="Company Name" class="form-control mb-4 col-4"/></p>
```
28-34: added Maximum and Minimum Count fields and enabled input for both fields
```
  <p>Minimum Count:
        <input type="text" path="minInv" th:field="*{minInv}" placeholder="Min Inventory" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('minInv')}" th:errors="*{minInv}">Min Inventory Error</p>

    <p>Maximum Count:
        <input type="text" path="maxInv" th:field="*{maxInv}" placeholder="Max Inventory" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('maxInv')}" th:errors="*{maxInv}">Min Inventory Error</p>
```

•  Rename the file the persistent storage is saved to.
application.properties
Lines:
6: updated name for database and version number

•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.
InventoryValidator.java
Lines:
1-41: created validator for min and max values
```
package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 *
 *
 *
 */
public class InventoryValidator implements ConstraintValidator<ValidInventory, Part> {
    @Autowired
    private ApplicationContext context;
    public static ApplicationContext myContext;

    @Override
    public void initialize(ValidInventory constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext constraintValidatorContext) {
        if (part.getInv() > part.getMaxInv()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Solution: Fix your Inventory, it is greater than the max inventory").addConstraintViolation();
            return false;
        } else if (part.getInv() < part.getMinInv()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Solution: Fix your Inventory, it is lower than the min inventory").addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }
}
```

ValidInventory.java
Lines:
1-23: created interface for validator class
```
package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 *
 *
 */
@Constraint(validatedBy = {InventoryValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidInventory {
    String message() default "Inventory Error!";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
```

38-42: added validator error messages for fields
```
<div th:if="${#fields.hasErrors()}">
        <ul>
            <li th:each="err: ${#fields.allErrors()}" th:text="${err}">
        </ul>
</div>
```

Part.java
Line:
23: added annotation for validator in Part.java
```
@ValidInventory
```

InhousePart.html
Lines:
38-42: added error messages for invalid inputs of minimum and maximum values
```
    <div th:if="${#fields.hasErrors()}">
        <ul>
            <li th:each="err: ${#fields.allErrors()}" th:text="${err}">
        </ul>
    </div>
```

OutsourcedPart.html
Lines:
39-43: added error messages for invalid inputs of minimum and maximum values
```
    <div th:if="${#fields.hasErrors()}">
        <ul>
            <li th:each="err: ${#fields.allErrors()}" th:text="${err}">
        </ul>
    </div>
```

H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.

Files:
created MaximumValidator.java
created MinimumValidator.java
created ValidMaximum.java
created ValidMinimum.java
Part.java
InhousePartForm.html
OutsourcedPartForm.html
BootStrapData.java
application.properties


BootStrapData.java
Lines:
153-164: edited names and prices of product to fit associated parts
```
            Product i1 = new Product("Tarts", 3.50, 30);
            Product i2 = new Product("Muffins", 2.50, 30);
            Product i3 = new Product("Fritters", 4.00, 30);

            Product out1 = new Product("Cruffins", 7.50, 25);
            Product out2 = new Product("Cronuts", 8.50, 25);

            productRepository.save(i1);
            productRepository.save(i2);
            productRepository.save(i3);
            productRepository.save(out1);
            productRepository.save(out2);
        }
```

MinimumValidator.java
Lines:
1-30: created minimum validator to test for inventory value
```
package com.example.demo.validators;

import com.example.demo.domain.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 *
 *
 *
 */
public class MinimumValidator implements ConstraintValidator<ValidMinimum, Part> {
    @Autowired
    private ApplicationContext context;
    public static ApplicationContext myContext;

    @Override
    public void initialize(ValidMinimum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext constraintValidatorContext) {
        return part.getInv() > part.getMinInv();
    }
}

```
ValidMinimum.java
Lines:
1-23: created accompanying ValidMinimum.java to display error message when there are not enough parts
```
package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 *
 *
 */
@Constraint(validatedBy = {MinimumValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMinimum {
    String message() default "Inventory Error! There is not enough parts";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
```

Part.java
Lines:
26: applying MinimumValidator to Part.java
```
@ValidMinimum
```

MaximumValidator.java
Lines:
1-30: created validator for maximum inventory value
```
package com.example.demo.validators;

import com.example.demo.domain.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 *
 *
 *
 */
public class MaximumValidator implements ConstraintValidator<ValidMaximum, Part> {
    @Autowired
    private ApplicationContext context;
    public static ApplicationContext myContext;

    @Override
    public void initialize(ValidMaximum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext constraintValidatorContext) {
        return part.getInv() < part.getMaxInv();
    }
}
```

ValidMaximum
Lines:
1-23: created accompanying ValidMaximum.java to display error message when there are too many parts
```
package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 *
 *
 */
@Constraint(validatedBy = {MaximumValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMaximum {
    String message() default "Inventory Error! There are too many parts";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
```

Part.java
Lines:
27: applying MaximumValidator
```
@ValidMaximum
```

InhousePart.html
Lines:
40: Ensured that error message would be displayed properly
```
            <li th:each="err: ${#fields.allErrors()}" th:text="${err}" class ="error"></li>
```

OutsourcedPart.html
Lines:
41: Ensured that error message would be displayed properly
```
            <li th:each="err: ${#fields.allErrors()}" th:text="${err}" class ="error"></li>
```

application.properties
Lines:
6: updated name for database and version number

I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.


J.  Remove the class files for any unused validators in order to clean your code.