import java.sql.*;
import java.util.Random;

public class UsersCRUD {

    public static Connection connect() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/social_network_ilia_gharibashvili","root","Sql123");
            System.out.println("+");
            return connection;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("-");
            return null;
        }
    }

    public static void read(){
        Connection connection = connect();

        if (connection != null){
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from social_network_ilia_gharibashvili.user");
                printResultSet(resultSet);
                connection.close();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }else{
            System.out.println("Could not connect");
        }
    }

    private static void printResultSet(ResultSet resultSet){
        try{
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String full_name = resultSet.getString("full_name");
                Date birth_date = resultSet.getDate("birth_date");
                int friends_count = resultSet.getInt("friends_count");
                System.out.printf("ID: %d; Name: %s; Birth date: %s; Number of friends: %s\n", id, full_name, birth_date.toString(), friends_count);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void add(){
        Connection connection = connect();

        if (connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("insert into social_network_ilia_gharibashvili.user(full_name,birth_date,friends_count) values(?,?,?)");
                preparedStatement.setString(1,"Mixeil Gochasvili");
                preparedStatement.setDate(2,new java.sql.Date(2001,9,4));
                preparedStatement.setInt(3,34);
                int updateRows = preparedStatement.executeUpdate();
                if (updateRows < 1){
                    System.out.println("Not added");
                }else{
                    System.out.println("Added");
                }
                preparedStatement.close();
                connection.close();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }else{
            System.out.println("Could not connect");
        }
    }

    public static void update(){
        Connection connection = connect();

        if (connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("update social_network_ilia_gharibashvili.user set friends_count=? where friends_count>?");
                preparedStatement.setInt(1, 200);
                preparedStatement.setInt(2, 20);
                int updateRows = preparedStatement.executeUpdate();
                if(updateRows < 1){
                    System.out.println("Not updated");
                }else{
                    System.out.println("Updated");
                }
                preparedStatement.close();
                connection.close();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }else{
            System.out.println("Could not connect");
        }
    }

    public static void delete(){
        Connection connection = connect();

        if (connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("delete from social_network_ilia_gharibashvili.user where id=?");
                preparedStatement.setInt(1, 2);
                int updateRows = preparedStatement.executeUpdate();
                if (updateRows < 1){
                    System.out.println("Not removed");
                }else{
                    System.out.println("Removed");
                }
                preparedStatement.close();
                connection.close();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }else{
            System.out.println("Could not connect");
        }
    }

}