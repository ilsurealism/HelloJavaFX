module com.example.hellojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.sql.rowset;


    opens com.example.hellojavafx to javafx.fxml;
    exports com.example.hellojavafx;
}