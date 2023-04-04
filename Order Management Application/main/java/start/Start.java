package start;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import com.google.protobuf.NullValue;
import model.Client;
import model.Product;
import presentation.*;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {

		MenuView menuView = new MenuView();
		ClientView clientView = new ClientView();
		OrderView orderView = new OrderView();
		ProductView productView = new ProductView();
		ClientBLL clientBLL = new ClientBLL();
		ProductBLL productBLL = new ProductBLL();
		OrderBLL orderBLL = new OrderBLL();
		Controller controller = new Controller(menuView, clientView, orderView, productView, clientBLL, productBLL, orderBLL);
		menuView.setVisible(true);

//		ClientBLL clientBLL = new ClientBLL();
//		ProductBLL productBLL = new ProductBLL();
//
//		Client client1 = null;
//		Product product1 = null;
//
//		try {
//
//

//			List<Client> listClients = clientBLL.findAllClient();
//			for(Client client : listClients){
//				ReflectionExample.retrieveProperties(client);
//			}

//			client1 = clientBll.findStudentById(1);
//			client1 = clientBLL.updateClient(new Client("cfdfdsfds", "dsdsds", "fdsfdsfs", 50), 23333);
//

//
//		} catch (Exception ex) {
//			LOGGER.log(Level.INFO, ex.getMessage());
//		}
//
//		 obtain field-value pairs for object through reflection
//		assert client1 != null;


	}

}

