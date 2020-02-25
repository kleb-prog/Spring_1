package Lesson_3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        try (SessionFactory factory = new Configuration()
                .configure("resources/hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Purchase.class)
                .buildSessionFactory()) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Main main = new Main();
            while (true) {
                System.out.println("Choose operation:\n1 - Check customer\n2 - Check product\n3 - Delete customer\n4 - Delete product \n\nTo exit type \"exit\"");
                String chose = reader.readLine();

                if (chose.equals("exit")) {
                    break;
                }

                switch (chose) {
                    case "1":
                        System.out.println("List of customers:");
                        List<Customer> allCustomers = main.getCustomersList(factory.getCurrentSession());
                        for (Customer customer : allCustomers) {
                            System.out.println(customer);
                        }
                        System.out.println("Choose customer by id:");
                        int chosenCustomerId = Integer.parseInt(reader.readLine());
                        Customer findCustomer = allCustomers.stream().filter(f -> f.getId() == chosenCustomerId).findFirst().orElse(null);
                        if (findCustomer != null) {
                            List<Product> productsByCustomer = main.getProductsByCustomerId(findCustomer.getId(), factory.getCurrentSession());
                            System.out.println("Products of " + findCustomer.getName() + ":");
                            for (Product product : productsByCustomer) {
                                System.out.println(product);
                            }
                            System.out.println();
                        } else {
                            System.out.println("Incorrect id!");
                        }
                        break;

                    case "2":
                        System.out.println("List of products");
                        List<Product> allProducts = main.getProductsList(factory.getCurrentSession());
                        for (Product product : allProducts) {
                            System.out.println(product);
                        }
                        System.out.println("Choose product by id:");
                        int chosenProductId = Integer.parseInt(reader.readLine());
                        Product findProduct = allProducts.stream().filter(p -> p.getId() == chosenProductId).findFirst().orElse(null);
                        if (findProduct != null) {
                            List<Customer> customersByProduct = main.getCustomersByProductId(findProduct.getId(), factory.getCurrentSession());
                            System.out.println("Customers of " + findProduct.getTitle() + ":");
                            for (Customer customer : customersByProduct) {
                                System.out.println(customer);
                            }
                            System.out.println();
                        } else {
                            System.out.println("Incorrect id!");
                        }
                        break;

                    case "3":
                        System.out.println("List of customers before delete:");
                        List<Customer> allCustomersBefore = main.getCustomersList(factory.getCurrentSession());
                        for (Customer customer : allCustomersBefore) {
                            System.out.println(customer);
                        }
                        System.out.println("Choose customer you want to delete by id or type 0 to cancel:");
                        int customerToDel = Integer.parseInt(reader.readLine());
                        if (customerToDel != 0) {
                            main.deleteCustomer(customerToDel, factory.getCurrentSession());
                            System.out.println("List of customers after delete:");
                            List<Customer> allCustomersAfter = main.getCustomersList(factory.getCurrentSession());
                            for (Customer customer : allCustomersAfter) {
                                System.out.println(customer);
                            }
                            System.out.println();
                        }
                        break;

                    case "4":
                        System.out.println("List of product before delete:");
                        List<Product> allProductBefore = main.getProductsList(factory.getCurrentSession());
                        for (Product product : allProductBefore) {
                            System.out.println(product);
                        }
                        System.out.println("Choose product you want to delete by id or type 0 to cancel:");
                        int productToDel = Integer.parseInt(reader.readLine());
                        if (productToDel != 0) {
                            main.deleteProduct(productToDel, factory.getCurrentSession());
                            System.out.println("List of products after delete:");
                            List<Product> allProductAfter = main.getProductsList(factory.getCurrentSession());
                            for (Product product : allProductAfter) {
                                System.out.println(product);
                            }
                            System.out.println();
                        }
                        break;
                    default:
                        break;
                }
            }
        }


//        //Read products
//        session = factory.getCurrentSession();
//        session.beginTransaction();
//        List<Product> products = session.createQuery("from Product", Product.class).getResultList();
//        session.getTransaction().commit();
//        System.out.println(products);
//
//        //Read customers
//        session = factory.getCurrentSession();
//        session.beginTransaction();
//        List<Customer> customers = session.createQuery("from Customer", Customer.class).getResultList();
//        session.getTransaction().commit();
//        System.out.println(customers);
//
//        session = factory.getCurrentSession();
//        session.beginTransaction();
//        Purchase purchase = session.get(Purchase.class, 1);
//        System.out.println(purchase.getProducts());
//        System.out.println(purchase.getCustomers());
//        session.getTransaction().commit();
//
//        //Delete
//        session = factory.getCurrentSession();
//        session.beginTransaction();
//        Customer customer = session.get(Customer.class, 5);
//        session.delete(customer);
//        session.getTransaction().commit();
//
//
//        session.close();
//        factory.close();
    }

    private List<Customer> getCustomersList(Session session) {
        try {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("from Customer", Customer.class).getResultList();
            session.getTransaction().commit();
            return customers;
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    private List<Product> getProductsList(Session session) {
        try {
            session.beginTransaction();
            List<Product> customers = session.createQuery("from Product", Product.class).getResultList();
            session.getTransaction().commit();
            return customers;
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    private List<Product> getProductsByCustomerId(int id, Session session) {
        try {
            session.beginTransaction();
            Purchase purchase = session.get(Purchase.class, id);
            List<Product> result = new ArrayList<>(purchase.getProducts());
            session.getTransaction().commit();
            return result;
        } finally {
            session.close();
        }
    }

    private List<Customer> getCustomersByProductId(int id, Session session) {
        try {
            session.beginTransaction();
            Purchase purchase = session.get(Purchase.class, id);
            List<Customer> result = new ArrayList<>(purchase.getCustomers());
            session.getTransaction().commit();
            return result;
        } finally {
            session.close();
        }
    }

    private void deleteCustomer(int id, Session session) {
        try {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.delete(customer);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    private void deleteProduct(int id, Session session) {
        try {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
