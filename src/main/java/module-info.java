module com.example.javafxproject0 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires lombok;
    requires jakarta.transaction;

    opens com.example.javafxproject0 to javafx.fxml;
    exports com.example.javafxproject0;
    exports com.example.javafxproject0.Controllers;
    opens com.example.javafxproject0.Controllers to javafx.fxml;
}