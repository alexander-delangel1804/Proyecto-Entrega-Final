import java.sql.Connection
import java.sql.DriverManager

object conexion {

    private const val URL = "jdbc:sqlserver://localhost:1433;databaseName=DBVetGo;encrypt=false"
    private const val USER = "AdminVetGo"
    private const val PASSWORD = "a1234"

    fun getConnection(): Connection {
        return DriverManager.getConnection(URL, USER, PASSWORD)
    }
}