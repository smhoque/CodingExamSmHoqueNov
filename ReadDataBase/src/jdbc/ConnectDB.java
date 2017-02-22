package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by riponctg on 12/28/2016.
 */
public class ConnectDB {
    Connection connect = null;
    Statement statement = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    public static Properties loadProperties() throws IOException {
        Properties prop = new Properties();
        InputStream ism = new FileInputStream("C:\\Users\\riponctg\\IdeaProjects\\ReadDataBase\\src\\MySql.Properties");
        prop.load(ism);

        ism.close();
        return prop;
    }
    public void ConnectToDataBase() throws IOException, ClassNotFoundException, SQLException{
        Properties prop = loadProperties();
        String url = prop.getProperty("MYSQLJDBC.url");
        String driverClass = prop.getProperty("MYSQLJDBC.driver");
        String UserName = prop.getProperty("MYSQLJDBC.UserName");
        String password = prop.getProperty("MYSQLJDBC.password");
        Class.forName(driverClass);
        connect = DriverManager.getConnection(url, UserName, password);
        System.out.println("DataBase is connected");
    }
    public List<String> readDataBase(String tableName, String columName) throws  Exception{
        List<String> data = new ArrayList<String>();
        try{
            ConnectToDataBase();
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select *from " + tableName);
            data = getResultSetData(resultSet, columName);

        }catch (ClassNotFoundException e){
            throw e;
        }finally {
            close();
        }
        return data;
    }

    private void close() {
        try{
            if(resultSet !=null){
                resultSet.close();
            }
            if(statement !=null){
                statement.close();
            }
            if(connect != null){
                connect.close();
            }
        }catch (Exception e){

        }
    }

    public void InsertDataFromStringToMySql(String DatabseName, String tableName, String columnName)


    //public void InsertDataFromArryToMySql()
    {

        try {
            ConnectToDataBase();

            //  connect.createStatement("INSERT into tbl_insertionSort set SortingNumbers=1000");


            ps = connect.prepareStatement("INSERT INTO "+tableName+" ( "+columnName+" ) VALUES(?)");
            ps.setString(1,"Nephal");
            ps.executeUpdate();
            System.out.println("Data is inserted");


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //connection = ConnectionConfiguration.getConnection();
    }

    private List<String> getResultSetData(ResultSet resultSet2, String columName) throws SQLException {
           List<String> datalist = new ArrayList<String>();
        while(resultSet.next()){
            String itemName = resultSet.getString(columName);
            datalist.add(itemName);

        }
        return datalist;
    }

}
