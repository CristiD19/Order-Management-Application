package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * Aceasta clasa realizaza sql query-uri
 * @param <T>
 */

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Creeaza un select query
     * @param field
     * @return
     */

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Creeza un select all query
     * @return
     */

    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Uitilizeaza queryul din metoda de createSelectAllQuery si returneaza obiecte dupa rezultatele obtinute din tabelele din mysql
     * @return
     */

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return this.createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Uitilizeaza queryul din metoda de createSelectQuery in functie de id dat ca parametru si returneaza obiectul dupa rezultatul obtinut din tabelele din mysql
     * @param id
     * @return
     */

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *
     * @param resultSet
     * @return
     */

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Creeaza un delete query in limbajul sql
     * @param field
     * @return
     */

    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " = ?");
        return sb.toString();
    }

    /**
     * Creeaza un insert query in limbajul sql
     * @param t
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     */

    private String createInsertQuery(T t) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        int k = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ");
        sb.append(" INTO ");
        sb.append(type.getSimpleName());
        sb.append(" ( ");
        for (Field field : type.getDeclaredFields()) {
            k++;
            String fieldName = field.getName();
            if(k != 1)
            sb.append(fieldName);

            if(k != type.getDeclaredFields().length)
                if(k != 1)
            sb.append(" , ");
        }
        k = 0;
        sb.append(" ) ");
        sb.append("VALUES (");
        for (Field field : type.getDeclaredFields()) {
            k++;
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
            Method method = propertyDescriptor.getReadMethod();
            Object value = method.invoke(t);
            System.out.println(k);
            if(k != 1)
            sb.append("'" + (value.toString() + "'"));
            if(k != type.getDeclaredFields().length)
                if(k != 1)
                    sb.append(",");

        }
        sb.append(")");

        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * Executa delet queryul creat in functia de createDeleteQuery
     * @param id
     */

    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultSet ;
        String query = createDeleteQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Executa Insert Queryul creat in functia de creatInsertQuery
     * @param t
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */

    public void insert(T t) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Connection connection = null;
        PreparedStatement statement = null;

        String insertQuery = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

           int result = statement.executeUpdate();
           // return createObjects(result).get(0);

           // createObjects(result).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }

//    public Client insert() {
//
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        int ioana;
//        String query = createInsertQuery();
//        try {
//            connection = ConnectionFactory.getConnection();
//            statement = connection.prepareStatement(query);
//           // statement.setInt(1, id);
//            ioana = statement.executeUpdate(query);
//            System.out.println(ioana);
//
//        } catch (SQLException e) {
//            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
//        } finally {
//            ConnectionFactory.close(resultSet);
//            ConnectionFactory.close(statement);
//            ConnectionFactory.close(connection);
//        }
//
//        return null;
//    }

    /**
     * creeaza un update query in limbajul sql
     * @param t
     * @return
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */

    private String createUpdateQuery(T t) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        int k = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for (Field field : type.getDeclaredFields()) {


            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
            Method method = propertyDescriptor.getReadMethod();
            Object value = method.invoke(t);
            if(k != 0)
            if (!Objects.equals(value.toString(), "")) {
                sb.append(field.getName());
                sb.append(" = ");
                sb.append("'" + value.toString() + "'");
                sb.append(",");
                sb.append(" ");
            }
            k++;

        }
        sb.deleteCharAt(sb.length()-2);
        sb.append(" WHERE" + " id " + "= ?");
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * Executa queryul de update creat in metoda de createUpdateQuery
     * @param t
     * @param id
     * @return
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public T update(T t, int id) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            int resultSet = statement.executeUpdate();


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
