package bll;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.EmailValidator;
import bll.validators.StudentAgeValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

/**
 * Foloseste metodele din clasa AbstractDao pe un obiect de tipul Client
 */
public class ClientBLL {

	private List<Validator<Client>> validators;
	private ClientDAO clientDAO;

	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new EmailValidator());
		validators.add(new StudentAgeValidator());

		clientDAO = new ClientDAO();
	}

	/**
	 * Gaseste un client din tabelul de clienti dupa id dat ca param
	 * @param id
	 * @return
	 */
	public Client findStudentById(int id) {
		Client st = clientDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The student with id =" + id + " was not found!");
		}
		return st;
	}

	/**
	 * Gaseste toti clientii din tabelul de clienti
	 * @return
	 */
	public List<Client> findAllClient() {

		List<Client> st = clientDAO.findAll();
		if (st == null) {
			throw new NoSuchElementException("findAllStudents failed");
		}
		return st;
	}

	/**
	 * Sterge un client din tabelul de clienti
	 * @param id
	 * @return
	 */
	public Client deleteClient(int id) {

		clientDAO.delete(id);
//		Client st = clientDAO.delete(id);
//		if (st == null) {
//			throw new NoSuchElementException("deleteStudent failed");
//		}
//		return st;
		return null;
	}

	/**
	 * Insereaza un client nou in tabelul de clienti
	 * @param client
	 * @return
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Client insertClient(Client client) throws IntrospectionException, InvocationTargetException, IllegalAccessException {

		clientDAO.insert(client);
//		Client st = clientDAO.insert(client);
//		if (st == null) {
//			throw new NoSuchElementException("Insert failed");
//		}
//		return st;
		return null;
	}

	/**
	 * Da update la un client din tabelul de clienti
	 * @param client
	 * @param id
	 * @return
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Client updateClient(Client client, int id) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
		clientDAO.update(client, id);

		return null;
	}

}

