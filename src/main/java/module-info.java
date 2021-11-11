module com.martaarjona.musicMaster {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.xml.bind;
	requires java.sql;
	requires javafx.base;
	requires javafx.graphics;
	

    opens com.martaarjona.musicMaster to javafx.fxml,javafx.base;
    opens com.martaarjona.model to javafx.base;
    opens com.martaarjona.MariaDB to javafx.base;
    opens com.martaarjona.utils to java.xml.bind;
    exports com.martaarjona.musicMaster;
    exports com.martaarjona.utils;
}
