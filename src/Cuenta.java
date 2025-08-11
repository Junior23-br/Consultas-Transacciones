import java.sql.*;

public class Cuenta {
    private static final String SQL_TABLE_CREATE = "DROP TABLE IF EXISTS CUENTAS;" +
            "CREATE TABLE CUENTAS (" +
            "ID INT PRIMARY KEY," +
            "NUMERO_CUENTA INT NOT NULL," +
            "TITULAR VARCHAR(100)," +
            "SALDO NUMERIC(10,2) NOT NULL"+
            ")";

    private static final String SQL_INSERT ="INSERT INTO CUENTAS VALUES (?,?,?,?)";
    private static final String SQL_SELECT = "SELECT * FROM CUENTAS";
    private static final String SQL_UPDATE = "UPDATE CUENTAS SET SALDO=? WHERE ID=?";

    public static void main(String[] args) {
        //Crear una tabla cuenta con 4 columnas(ID,/#Cuenta/Titular/Saldo)

                //NUMERIC (10, 2) 12345678,91
        Connection connection = null;
        try {
            connection = getConnection();
            //Crear los valores estaticos de la conexion
            Statement statement = connection.createStatement();
            statement.execute(SQL_TABLE_CREATE);
            //Insertar valores
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1,1);
            preparedStatement.setInt(2,1234567);
            preparedStatement.setString(3,"Vanina");
            preparedStatement.setDouble(4,2197.88);
            //Se ejecuta la orden
            preparedStatement.execute();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                System.out.println("Los datos de la cuenta y el saldo inicial son:" +
                        " -ID: " +resultSet.getDouble(1) +
                        " -Numero cuenta: " + resultSet.getInt(2) +
                        " -Nombre: " + resultSet.getString(3) +
                        " -Saldo: " +resultSet.getDouble(4));
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                connection.close();
            } catch(Exception e) {
                e.printStackTrace();
            }

        }
    }
    //Metodo para generar la conexion
    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/ConsultasTransacciones", "sa", "sa");

    }



}
