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
            ih1.setPrice(2.50);
            ih1.setId(200);
            ih1.setMinInv(1);
            ih1.setMaxInv(150);
            ih1.setInv(30);
            inhousePartRepository.save(ih1);
            InhousePart thePart = null;
            inhouseParts = (List<InhousePart>) inhousePartRepository.findAll();
            for (InhousePart part : inhouseParts) {
                if (part.getName().equals("Monday Muffin")) thePart = part;
            }

            InhousePart ih2 = new InhousePart();
            ih2.setName("Tuesday Tarts");
            ih2.setPrice(3.50);
            ih2.setId(300);
            ih2.setMinInv(1);
            ih2.setMaxInv(150);
            ih2.setInv(30);
            inhousePartRepository.save(ih2);
            thePart = null;
            inhouseParts = (List<InhousePart>) inhousePartRepository.findAll();
            for (InhousePart part : inhouseParts) {
                if (part.getName().equals("Tuesday Tarts")) thePart = part;
            }

            InhousePart ih3 = new InhousePart();
            ih3.setName("Friday Fritter");
            ih3.setPrice(4.00);
            ih3.setId(400);
            ih3.setMinInv(1);
            ih3.setMaxInv(150);
            ih3.setInv(30);
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
            o1.setPrice(7.50);
            o1.setId(998);
            o1.setMinInv(1);
            o1.setMaxInv(200);
            o1.setInv(30);
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
            o2.setPrice(8.50);
            o2.setId(997);
            o2.setMinInv(1);
            o2.setMaxInv(250);
            o2.setInv(30);
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
