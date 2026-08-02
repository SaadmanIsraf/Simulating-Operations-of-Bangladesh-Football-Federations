module com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation;
    opens com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation to javafx.fxml;

    opens com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller to javafx.fxml;
    exports com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

    opens com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller
            to javafx.fxml;

    opens com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller to javafx.fxml;

    opens com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes
            to javafx.base, javafx.fxml;
}