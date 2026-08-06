package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.LinkedHashMap;
import java.util.Map;

public class tournament_rules_controller {

    @FXML
    private TextArea tournament_rules_textarea;

    @FXML
    private TextArea match_regulations_textarea;

    @FXML
    private ComboBox<String> tournament_combobox;

    @FXML
    private TextArea code_of_conduct_textarea;

    @FXML
    private Label information_label;

    @FXML
    private TextArea disciplinary_guidelines_textarea;

    @FXML
    private TextArea eligibility_criteria_textarea;

    private final Map<String, String> tournamentRulesMap =
            new LinkedHashMap<>();

    private final Map<String, String> eligibilityCriteriaMap =
            new LinkedHashMap<>();

    private final Map<String, String> codeOfConductMap =
            new LinkedHashMap<>();

    private final Map<String, String> matchRegulationsMap =
            new LinkedHashMap<>();

    private final Map<String, String> disciplinaryGuidelinesMap =
            new LinkedHashMap<>();

    @FXML
    public void initialize() {

        initializeTournamentNames();
        initializeTournamentRules();
        initializeEligibilityCriteria();
        initializeCodeOfConduct();
        initializeMatchRegulations();
        initializeDisciplinaryGuidelines();

        tournament_rules_textarea.setWrapText(true);
        eligibility_criteria_textarea.setWrapText(true);
        code_of_conduct_textarea.setWrapText(true);
        match_regulations_textarea.setWrapText(true);
        disciplinary_guidelines_textarea.setWrapText(true);

        clearTournamentInformation();
    }

    private void initializeTournamentNames() {

        tournament_combobox.getItems().setAll(
                "Bangladesh Premier League",
                "Federation Cup",
                "Independence Cup",
                "Championship League",
                "National Football Championship"
        );
    }

    private void initializeTournamentRules() {

        tournamentRulesMap.put(
                "Bangladesh Premier League",
                "The tournament follows a league format where each team plays "
                        + "against the other participating teams. A team receives "
                        + "three points for a win, one point for a draw and zero "
                        + "points for a loss. The team with the highest number of "
                        + "points becomes the champion."
        );

        tournamentRulesMap.put(
                "Federation Cup",
                "The Federation Cup is played through group and knockout stages. "
                        + "Qualified teams advance to the knockout rounds. If a "
                        + "knockout match ends in a draw, extra time and penalty "
                        + "kicks may be used to determine the winner."
        );

        tournamentRulesMap.put(
                "Independence Cup",
                "The Independence Cup is organized as a short-format competition. "
                        + "Teams first compete in group matches before advancing "
                        + "to the semifinal and final rounds."
        );

        tournamentRulesMap.put(
                "Championship League",
                "The Championship League follows a league-based format. Teams "
                        + "compete for points throughout the season. Final standings "
                        + "are determined by points, goal difference, goals scored "
                        + "and head-to-head performance."
        );

        tournamentRulesMap.put(
                "National Football Championship",
                "The National Football Championship includes district and regional "
                        + "teams. Participating teams compete in qualifying rounds "
                        + "before advancing to the final stage."
        );
    }

    private void initializeEligibilityCriteria() {

        eligibilityCriteriaMap.put(
                "Bangladesh Premier League",
                "Players must be officially registered with a participating club. "
                        + "Every player must have a valid BFF registration, meet "
                        + "medical fitness requirements and have no active suspension."
        );

        eligibilityCriteriaMap.put(
                "Federation Cup",
                "Only players registered for the current football season may "
                        + "participate. A player cannot represent more than one club "
                        + "in the same tournament without official BFF approval."
        );

        eligibilityCriteriaMap.put(
                "Independence Cup",
                "Players must be included in the official squad submitted before "
                        + "the registration deadline. Each player must be medically "
                        + "fit and eligible under the tournament rules."
        );

        eligibilityCriteriaMap.put(
                "Championship League",
                "Players must belong to a registered Championship League club. "
                        + "Player registration, age requirements and transfer "
                        + "documents must comply with BFF regulations."
        );

        eligibilityCriteriaMap.put(
                "National Football Championship",
                "Players must represent the district or regional team for which "
                        + "they are officially registered. Identity and eligibility "
                        + "documents must be verified before participation."
        );
    }

    private void initializeCodeOfConduct() {

        codeOfConductMap.put(
                "Bangladesh Premier League",
                "Players must respect match officials, opponents, teammates and "
                        + "spectators. Abusive language, discrimination, violent "
                        + "conduct, match manipulation and unsporting behaviour "
                        + "are strictly prohibited."
        );

        codeOfConductMap.put(
                "Federation Cup",
                "All players and officials must maintain professional behaviour. "
                        + "Referee decisions must be respected, and participants "
                        + "must avoid actions that damage the reputation of the competition."
        );

        codeOfConductMap.put(
                "Independence Cup",
                "Players and team officials must demonstrate discipline, respect "
                        + "and fair play. Provocation, offensive behaviour and "
                        + "physical aggression may result in disciplinary action."
        );

        codeOfConductMap.put(
                "Championship League",
                "Participants must follow BFF sporting and ethical standards. "
                        + "Team officials are responsible for maintaining proper "
                        + "conduct before, during and after matches."
        );

        codeOfConductMap.put(
                "National Football Championship",
                "Players must represent their regions with respect and integrity. "
                        + "Misconduct against officials, opponents or spectators "
                        + "may result in suspension or removal from the competition."
        );
    }

    private void initializeMatchRegulations() {

        matchRegulationsMap.put(
                "Bangladesh Premier League",
                "Each match consists of two halves of 45 minutes with a halftime "
                        + "interval. Teams must submit their starting lineup before "
                        + "kickoff. Substitutions must follow official competition rules."
        );

        matchRegulationsMap.put(
                "Federation Cup",
                "Matches are played for 90 minutes. Knockout matches that remain "
                        + "level may proceed to extra time and penalty kicks. "
                        + "The referee's decisions during the match are final."
        );

        matchRegulationsMap.put(
                "Independence Cup",
                "Matches are played according to the official fixture schedule. "
                        + "Teams must arrive before kickoff and complete player "
                        + "registration and equipment checks."
        );

        matchRegulationsMap.put(
                "Championship League",
                "League matches consist of 90 minutes. Venue changes or match "
                        + "postponements require approval from the organizing authority. "
                        + "Teams must follow squad and substitution limits."
        );

        matchRegulationsMap.put(
                "National Football Championship",
                "Match duration and knockout procedures follow BFF guidelines. "
                        + "Teams must report to the venue on time and submit verified "
                        + "player lists before kickoff."
        );
    }

    private void initializeDisciplinaryGuidelines() {

        disciplinaryGuidelinesMap.put(
                "Bangladesh Premier League",
                "Yellow and red cards are recorded throughout the competition. "
                        + "Accumulated yellow cards may cause suspension. Serious "
                        + "misconduct may result in fines, extended suspension or bans."
        );

        disciplinaryGuidelinesMap.put(
                "Federation Cup",
                "A player receiving a red card is suspended according to tournament "
                        + "rules. Repeated offences, violent conduct or referee abuse "
                        + "may lead to additional disciplinary penalties."
        );

        disciplinaryGuidelinesMap.put(
                "Independence Cup",
                "Disciplinary decisions are made by the tournament committee. "
                        + "Players may receive warnings, suspension or removal "
                        + "depending on the seriousness of the offence."
        );

        disciplinaryGuidelinesMap.put(
                "Championship League",
                "Card accumulation, violent behaviour and serious rule violations "
                        + "are reviewed by the disciplinary authority. Penalties may "
                        + "include suspension, fines or team-point deductions."
        );

        disciplinaryGuidelinesMap.put(
                "National Football Championship",
                "Players and officials who violate competition rules may receive "
                        + "warnings, match suspensions or tournament bans. Appeals "
                        + "must follow the official process."
        );
    }

    @FXML
    public void tournament_combobox_on_action(
            ActionEvent actionEvent) {

        String selectedTournament =
                tournament_combobox.getValue();

        if (selectedTournament == null) {

            clearTournamentInformation();
            return;
        }

        displayTournamentInformation(
                selectedTournament
        );

        information_label.setText(
                selectedTournament
                        + " information loaded successfully."
        );
    }


    private void displayTournamentInformation(
            String tournamentName) {

        tournament_rules_textarea.setText(
                tournamentRulesMap.getOrDefault(
                        tournamentName,
                        "No tournament rules are available."
                )
        );

        eligibility_criteria_textarea.setText(
                eligibilityCriteriaMap.getOrDefault(
                        tournamentName,
                        "No eligibility criteria are available."
                )
        );

        code_of_conduct_textarea.setText(
                codeOfConductMap.getOrDefault(
                        tournamentName,
                        "No code of conduct is available."
                )
        );

        match_regulations_textarea.setText(
                matchRegulationsMap.getOrDefault(
                        tournamentName,
                        "No match regulations are available."
                )
        );

        disciplinary_guidelines_textarea.setText(
                disciplinaryGuidelinesMap.getOrDefault(
                        tournamentName,
                        "No disciplinary guidelines are available."
                )
        );

        moveTextAreasToTop();
    }

    private void clearTournamentInformation() {

        tournament_rules_textarea.clear();
        eligibility_criteria_textarea.clear();
        code_of_conduct_textarea.clear();
        match_regulations_textarea.clear();
        disciplinary_guidelines_textarea.clear();
        information_label.setText("");
    }

    private void moveTextAreasToTop() {

        tournament_rules_textarea.positionCaret(0);
        eligibility_criteria_textarea.positionCaret(0);
        code_of_conduct_textarea.positionCaret(0);
        match_regulations_textarea.positionCaret(0);
        disciplinary_guidelines_textarea.positionCaret(0);
    }

    @FXML
    public void back_button_on_action(
            ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "Arman/player/player_dashboard.fxml"
        );
    }

    private void showAlert(
            Alert.AlertType alertType,
            String title,
            String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}