package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
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

       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */
            outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
            for (OutsourcedPart part : outsourcedParts) {
                System.out.println(part.getName() + " " + part.getCompanyName());
            }
        }
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

        /*
        Product bicycle= new Product("bicycle",100.0,15);
        Product unicycle= new Product("unicycle",100.0,15);
        productRepository.save(bicycle);
        productRepository.save(unicycle);
        */

            System.out.println("Started in Bootstrap");
            System.out.println("Number of Products" + productRepository.count());
            System.out.println(productRepository.findAll());
            System.out.println("Number of Goods" + partRepository.count());
            System.out.println(partRepository.findAll());

        }
    }
