package bll;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.EmailValidator;
import bll.validators.StudentAgeValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

/**
 *  Foloseste metodele din clasa AbstractDao pe un obiect de tipul Client
 */

public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();

        productDAO = new ProductDAO();
    }

    /**
     * Gaseste un produs din tabelul de produse dupa id dat ca param
     * @param id
     * @return
     */

    public Product findProductById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Gaseste toate produsele din tabelul de produse
     * @return
     */

    public List<Product> findAllProducts() {
        List<Product> st = productDAO.findAll();
        if (st == null) {
            throw new NoSuchElementException("findAllProducts failed");
        }
        return st;
    }

    /**
     * Sterge un produs din tabel dupa id dat ca parametru
     * @param id
     * @return
     */
    public Product deleteProduct(int id) {
        productDAO.delete(id);
//        Product st = productDAO.delete(id);
//        if (st == null) {
//            throw new NoSuchElementException("deleteProduct failed ");
//        }
//        return st;
        return null;
    }

    /**
     * Insereaza un produs nou in tabel
     * @param product
     * @return
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Product insertProduct(Product product) throws IntrospectionException, InvocationTargetException, IllegalAccessException {


        productDAO.insert(product);

//		Product st = productDAO.insert(product);
//		if (st == null) {
//			throw new NoSuchElementException("Insert failed");
//		}
//		return st;
        return null;
    }

    /**
     * Editeaza un produs
     * @param product
     * @param id
     * @return
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Product updateProduct(Product product, int id) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        productDAO.update(product, id);

//        Product st = productDAO.update(product, id);
//        if (st == null) {
//            throw new NoSuchElementException("updateProduct failed");
//        }
//        return st;
        return null;
    }

}