package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Orders;
import model.Product;
import start.ReflectionExample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Controller {

    private MenuView menuView;
    private ClientView clientView;
    private OrderView orderView;
    private ProductView productView;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;

    public Controller(MenuView menuView, ClientView clientView, OrderView orderView, ProductView productView, ClientBLL clientBLL, ProductBLL productBLL, OrderBLL orderBLL) {
        this.menuView = menuView;
        this.clientView = clientView;
        this.orderView = orderView;
        this.productView = productView;
        this.clientBLL = clientBLL;
        this.productBLL = productBLL;
        this.orderBLL = orderBLL;

        this.menuView.btnClientTableListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientView.setVisible(true);
            }
        });

        this.menuView.btnProductTableListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productView.setVisible(true);
            }
        });

        this.menuView.btnOrderTableListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderView.setVisible(true);
            }
        });


        this.clientView.btnAddClientListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // ClientBLL clientBLL = new ClientBLL();
                String name = clientView.getClientNameInput();
                String address = clientView.getClientAddressInput();
                String email = clientView.getClientEmailInput();
                int age = Integer.parseInt(clientView.getClientAgeInput());
                try {
                    Client client = clientBLL.insertClient(new Client(name, address, email, age));

                } catch (IntrospectionException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.clientView.btnDeleteClientListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // ClientBLL clientBLL = new ClientBLL();

                int id = clientView.getIdFromTable();
                Client client = clientBLL.deleteClient(id);



                List<Client> clients = clientBLL.findAllClient();
                int length = clients.size();

                for(int i = 0; i < length+1; i++){
                    for(int j = 0; j < 5; j++){
                        clientView.setClientTableText(null, i+1, j);
                    }
                }

                for(int i = 0; i < length; i++){
                    String s = ReflectionExample.retrieveProperties(clients.get(i));
                    String[] string = s.split(System.lineSeparator());
                    clientView.setClientTableText(string[0], i+1, 0);
                    clientView.setClientTableText(string[1], i+1, 1);
                    clientView.setClientTableText(string[2], i+1, 2);
                    clientView.setClientTableText(string[3], i+1, 3);
                    clientView.setClientTableText(string[4], i+1, 4);
                }



            }
        });

        this.clientView.btnViewAllClientsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // ClientBLL clientBLL = new ClientBLL();

                List<Client> clients = clientBLL.findAllClient();
                int length = clients.size();
                for(int i = 0; i < length; i++){
                    String s = ReflectionExample.retrieveProperties(clients.get(i));
                    String[] string = s.split(System.lineSeparator());
                    clientView.setClientTableText(string[0], i+1, 0);
                    clientView.setClientTableText(string[1], i+1, 1);
                    clientView.setClientTableText(string[2], i+1, 2);
                    clientView.setClientTableText(string[3], i+1, 3);
                    clientView.setClientTableText(string[4], i+1, 4);
                }

            }
        });

        this.clientView.btnEditClientListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // ClientBLL clientBLL = new ClientBLL();
                int id = clientView.getIdFromTable();
                String name = clientView.getClientNameInput();
                String address = clientView.getClientAddressInput();
                String email = clientView.getClientEmailInput();
                int age = Integer.parseInt(clientView.getClientAgeInput());
                try {
                    Client client1 = clientBLL.updateClient(new Client(name, address, email, age), id);
                } catch (IntrospectionException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.productView.btnAddProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ClientBLL clientBLL = new ClientBLL();
                String name = productView.getProductNameInput();
                int price = Integer.parseInt(productView.getProductPriceInput());
                int quantity = Integer.parseInt(productView.getProductQuantityInput());
                try {
                    Product product = productBLL.insertProduct(new Product(name, price, quantity));

                } catch (IntrospectionException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.productView.btnDeleteProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ClientBLL clientBLL = new ClientBLL();

                int id = productView.getIdFromTable();
                Product product = productBLL.deleteProduct(id);



                List<Product> products = productBLL.findAllProducts();
                int length = products.size();

                for(int i = 0; i < length+1; i++){
                    for(int j = 0; j < 4; j++){
                        productView.setProductTableText(null, i+1, j);
                    }
                }

                for(int i = 0; i < length; i++){
                    String s = ReflectionExample.retrieveProperties(products.get(i));
                    String[] string = s.split(System.lineSeparator());
                    productView.setProductTableText(string[0], i+1, 0);
                    productView.setProductTableText(string[1], i+1, 1);
                    productView.setProductTableText(string[2], i+1, 2);
                    productView.setProductTableText(string[3], i+1, 3);
                }



            }
        });

        this.productView.btnViewAllProductsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ClientBLL clientBLL = new ClientBLL();

                List<Product> products = productBLL.findAllProducts();
                int length = products.size();

                for(int i = 0; i < length+1; i++){
                    for(int j = 0; j < 4; j++){
                        productView.setProductTableText(null, i+1, j);
                    }
                }

                for(int i = 0; i < length; i++){
                    String s = ReflectionExample.retrieveProperties(products.get(i));
                    String[] string = s.split(System.lineSeparator());
                    productView.setProductTableText(string[0], i+1, 0);
                    productView.setProductTableText(string[1], i+1, 1);
                    productView.setProductTableText(string[2], i+1, 2);
                    productView.setProductTableText(string[3], i+1, 3);
                }

            }
        });

        this.productView.btnEditProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ClientBLL clientBLL = new ClientBLL();
                int id = productView.getIdFromTable();
                String name = productView.getProductNameInput();
                int price = Integer.parseInt(productView.getProductPriceInput());
                int quantity = Integer.parseInt(productView.getProductQuantityInput());
                try {
                    Product product = productBLL.updateProduct(new Product(name, price, quantity), id);
                } catch (IntrospectionException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.orderView.btnExecuteOrderListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int productId = Integer.parseInt(orderView.getProductIdInput());
                int clientId = Integer.parseInt(orderView.getClientIdInput());
                int quantity = Integer.parseInt(orderView.getQuantityInput());

                try {
                    orderBLL.insertOrder(productId, clientId, quantity);

                } catch (IntrospectionException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.orderView.btnViewOrdersListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Orders> orders = orderBLL.findAllOrders();
                int length = orders.size();
                for(int i = 0; i < length; i++){
                    String s = ReflectionExample.retrieveProperties(orders.get(i));
                    String[] string = s.split(System.lineSeparator());
                    orderView.setOrderTableText(string[0], i+1, 0);
                    orderView.setOrderTableText(string[1], i+1, 1);
                    orderView.setOrderTableText(string[2], i+1, 2);
                    orderView.setOrderTableText(string[3], i+1, 3);
                }
            }
        });


    }
}
