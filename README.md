You are working for a company that licenses and customizes a software application to keep track of inventory in stores. Your job as a software developer is to customize this application to meet a specific customer’s needs. You will choose any type of customer you would like, but it must sell a product composed of parts. An example of products versus parts would be a customer that’s a bicycle shop: a bicycle is a product, and a set of two matching wheels is a part (do not use the bicycle shop example in your project).



You have been provided with a Spring application with a Java backend, a generic HTML user interface to use in the design and development of the system, and a UML class diagram to assist you in your work (see the attached “UML Class Diagram”). You can find a user guide to help assist with the inventory management application in the attachments (see “Shop Inventory Management User Guide”).


Changes Log:

C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.
File:
mainscreen.html

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
    <a th:href="@{/showFormAddInPart}" class="btn btn-primary btn-sm mb-3">Add Daily Special</a>
    <a th:href="@{/showFormAddOutPart}" class="btn btn-primary btn-sm mb-3">Add Weekend Special</a>
```
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
43-115: added 3 Inhouse and 2 Outsourced parts along with conditional if() statement for inventory
138-152: Added 5 Products

application.properties
Lines:
6: updated name for database and version number 

Note: Make sure the sample inventory is added only when both the part and product lists are empty. When adding the sample inventory appropriate for the store, the inventory is stored in a set so duplicate items cannot be added to your products. When duplicate items are added, make a “multi-pack” part.


F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
• The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
•  Display a message that indicates the success or failure of a purchase.


G.  Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
•  Modify the sample inventory to include the maximum and minimum fields.
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
•  Rename the file the persistent storage is saved to.
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.


H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.


I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.


J.  Remove the class files for any unused validators in order to clean your code.