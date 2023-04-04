package bll;

import dao.OrderDAO;
import model.Client;
import model.Orders;
import model.Product;

import java.beans.IntrospectionException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *  Foloseste metoda din clasa AbstractDao pe un obiect de tipul Order si creeaza un Order
 */

public class OrderBLL {

    private OrderDAO orderDAO;

    public OrderBLL(){

        orderDAO = new OrderDAO();
    }

    /**
     * Creaza un fisier.txt in care se va scrie comanda efectuata
     * @param client
     * @param product
     * @param order
     * @throws IOException
     */
    public void createBill(Client client, Product product, Orders order) throws IOException {
        int finalPrice = order.getQuantity() * product.getPrice();

        String string = "Clientul cu id-ul " + client.getId() + " a efectuat comanda produsului " + product.getName() + "\n" + "Suma de plata este " + finalPrice;
        FileWriter fileWriter = new FileWriter("Order.txt");
        fileWriter.write(string);
        fileWriter.close();
    }

    /**
     * Insereaza comanda efectuata in tabelul de order
     * @param idProduct
     * @param idClient
     * @param quantity
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public void insertOrder(int idProduct, int idClient, int quantity) throws IntrospectionException, InvocationTargetException, IllegalAccessException, IOException {
        ProductBLL productBLL = new ProductBLL();
        ClientBLL clientBLL = new ClientBLL();

        Product product = productBLL.findProductById(idProduct);
        Client client = clientBLL.findStudentById(idClient);
        if(product.getQuantity() >= quantity){
            productBLL.updateProduct(new Product(product.getName(), product.getPrice(), product.getQuantity() - quantity),idProduct);
            int x = product.getQuantity() - quantity;
            if(x == 0) {
                productBLL.deleteProduct(idProduct);
            }
            Orders order = new Orders(idClient, idProduct, quantity);
            orderDAO.insert(order);
            createBill(client, product, order);
        }
        else{
            System.out.println("Orderul nu s-a putut efectua deoarece nu avem cantitatea ceruta disponibila");
        }
    }

    /**
     * Afiseaza in tabel toate comenzile
     * @return
     */
    public List<Orders> findAllOrders() {

        List<Orders> st = orderDAO.findAll();
        if (st == null) {
            throw new NoSuchElementException("findAllOrders failed");
        }
        return st;
    }
}
