package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.InjuryReport;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MatchDayMedicalIncident;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MedicalDeclaration;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.PersonalStats;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.RehabilitationProgress;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.TreatmentPlan;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.InjuryReportManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalDeclarationManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalIncidentManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.RehabilitationProgressManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.TreatmentPlanManager;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_manager.PersonalStatsManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class personal_stats_controller {

    @FXML
    private TextField player_id_textfield;

    @FXML
    private TextField goals_textfield;

    @FXML
    private TextField assists_textfield;

    @FXML
    private TextField matches_played_textfield;

    @FXML
    private Label player_information_label;

    @FXML
    private TextField search_player_id_textfield;

    @FXML
    private TableView<PersonalStats> personal_stats_table;

    @FXML
    private TableColumn<PersonalStats, Integer> player_id_column;

    @FXML
    private TableColumn<PersonalStats, String> player_name_column;

    @FXML
    private TableColumn<PersonalStats, Integer> goals_column;

    @FXML
    private TableColumn<PersonalStats, Integer> assists_column;

    @FXML
    private TableColumn<PersonalStats, Integer> matches_played_column;

    @FXML
    private TableColumn<PersonalStats, String> medical_condition_column;

    @FXML
    private TextArea complete_report_textarea;

    private final List<Player> playerList = new ArrayList<>();

    private static final String PLAYER_FILE_NAME = "players.bin";

    @FXML
    public void initialize() {

        initializeTableColumns();

        complete_report_textarea.setEditable(false);
        complete_report_textarea.setWrapText(false);

        loadPlayersFromFile();
        refreshPersonalStatsTable();
        clearStatisticsInputFields();

        player_information_label.setText("Not Loaded");

        personal_stats_table
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, selectedStats) -> {

                            if (selectedStats != null) {

                                displaySelectedStatistics(
                                        selectedStats
                                );
                            }
                        }
                );
    }

    private void initializeTableColumns() {

        player_id_column.setCellValueFactory(
                new PropertyValueFactory<>(
                        "playerId"
                )
        );

        player_name_column.setCellValueFactory(
                new PropertyValueFactory<>(
                        "playerName"
                )
        );

        goals_column.setCellValueFactory(
                new PropertyValueFactory<>(
                        "goals"
                )
        );

        assists_column.setCellValueFactory(
                new PropertyValueFactory<>(
                        "assists"
                )
        );

        matches_played_column.setCellValueFactory(
                new PropertyValueFactory<>(
                        "matchesPlayed"
                )
        );

        medical_condition_column.setCellValueFactory(
                new PropertyValueFactory<>(
                        "medicalCondition"
                )
        );
    }

    private void refreshPersonalStatsTable() {

        personal_stats_table.getItems().setAll(
                PersonalStatsManager
                        .getPersonalStatsList()
        );

        personal_stats_table.refresh();
    }

    private void displaySelectedStatistics(
            PersonalStats selectedStats) {

        player_id_textfield.setText(
                String.valueOf(
                        selectedStats.getPlayerId()
                )
        );

        player_information_label.setText(
                selectedStats.getPlayerName()
                        + " | Current totals: "
                        + selectedStats.getGoals()
                        + " goals, "
                        + selectedStats.getAssists()
                        + " assists, "
                        + selectedStats.getMatchesPlayed()
                        + " matches"
        );
    }

    private void clearStatisticsInputFields() {

        player_id_textfield.clear();
        goals_textfield.clear();
        assists_textfield.clear();
        matches_played_textfield.clear();

        player_information_label.setText(
                "Not Loaded"
        );
    }

    private void clearReportSearchField() {

        search_player_id_textfield.clear();
        complete_report_textarea.clear();
    }

    @FXML
    public void back_button_on_action(
            ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "Arman/player/player_dashboard.fxml"
        );
    }

    @FXML
    public void save_stats_button_on_action(
            ActionEvent actionEvent) {

        String playerIdText =
                player_id_textfield
                        .getText()
                        .trim();

        String goalsText =
                goals_textfield
                        .getText()
                        .trim();

        String assistsText =
                assists_textfield
                        .getText()
                        .trim();

        String matchesPlayedText =
                matches_played_textfield
                        .getText()
                        .trim();

        if (playerIdText.isEmpty()
                || goalsText.isEmpty()
                || assistsText.isEmpty()
                || matchesPlayedText.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please fill in Player ID, goals, assists and matches played."
            );

            return;
        }

        int playerId;
        int addedGoals;
        int addedAssists;
        int addedMatchesPlayed;

        try {

            playerId =
                    Integer.parseInt(
                            playerIdText
                    );

            addedGoals =
                    Integer.parseInt(
                            goalsText
                    );

            addedAssists =
                    Integer.parseInt(
                            assistsText
                    );

            addedMatchesPlayed =
                    Integer.parseInt(
                            matchesPlayedText
                    );

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Player ID, goals, assists and matches played must be whole numbers."
            );

            return;
        }

        if (playerId <= 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Player ID",
                    "Player ID must be greater than zero."
            );

            return;
        }

        if (addedGoals < 0
                || addedAssists < 0
                || addedMatchesPlayed < 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Statistics",
                    "Goals, assists and matches played cannot be negative."
            );

            return;
        }

        if (addedGoals == 0
                && addedAssists == 0
                && addedMatchesPlayed == 0) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "No Statistics Entered",
                    "At least one statistics value must be greater than zero."
            );

            return;
        }

        /*
         * Reload players.bin before every save so the
         * latest Player Profile information is used.
         */
        if (!loadPlayersFromFile()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Data Error",
                    "Player information could not be loaded from players.bin."
            );

            return;
        }

        Player foundPlayer =
                findPlayerById(playerId);

        if (foundPlayer == null) {

            player_information_label.setText(
                    "Player not found"
            );

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "No player exists with Player ID: "
                            + playerId
            );

            return;
        }

        if (addedMatchesPlayed == 0
                && (addedGoals > 0
                || addedAssists > 0)) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Match Statistics",
                    "Goals or assists cannot be added when matches played is zero."
            );

            return;
        }

        /*
         * This validation permits multiple goals and assists
         * in one match, but prevents extremely unrealistic
         * accidental input.
         */
        if (addedMatchesPlayed > 0
                && addedGoals > addedMatchesPlayed * 10) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Goals",
                    "The entered goals value is unusually high for the number of matches."
            );

            return;
        }

        if (addedMatchesPlayed > 0
                && addedAssists > addedMatchesPlayed * 10) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Assists",
                    "The entered assists value is unusually high for the number of matches."
            );

            return;
        }

        String medicalCondition =
                foundPlayer.getFitnessStatus();

        if (medicalCondition == null
                || medicalCondition.isBlank()) {

            medicalCondition =
                    "Not available";
        }

        PersonalStats existingStats =
                PersonalStatsManager
                        .findByPlayerId(playerId);

        if (existingStats == null) {

            PersonalStats newStats =
                    new PersonalStats(
                            playerId,
                            foundPlayer.getName(),
                            addedGoals,
                            addedAssists,
                            addedMatchesPlayed,
                            medicalCondition,
                            LocalDate.now()
                    );

            PersonalStatsManager
                    .addPersonalStats(newStats);

            PersonalStatsManager.saveToFile();

            refreshPersonalStatsTable();

            personal_stats_table
                    .getSelectionModel()
                    .select(newStats);

            personal_stats_table
                    .scrollTo(newStats);

            player_information_label.setText(
                    foundPlayer.getName()
                            + " | Statistics record created"
            );

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Statistics Saved",
                    "Personal statistics saved successfully.\n\n"
                            + "Player: "
                            + foundPlayer.getName()
                            + "\n"
                            + "Goals: "
                            + newStats.getGoals()
                            + "\n"
                            + "Assists: "
                            + newStats.getAssists()
                            + "\n"
                            + "Matches Played: "
                            + newStats.getMatchesPlayed()
            );

        } else {

            existingStats.addStatistics(
                    addedGoals,
                    addedAssists,
                    addedMatchesPlayed
            );

            existingStats.setPlayerName(
                    foundPlayer.getName()
            );

            existingStats.setMedicalCondition(
                    medicalCondition
            );

            existingStats.setLastUpdated(
                    LocalDate.now()
            );

            PersonalStatsManager.saveToFile();

            refreshPersonalStatsTable();

            personal_stats_table
                    .getSelectionModel()
                    .select(existingStats);

            personal_stats_table
                    .scrollTo(existingStats);

            player_information_label.setText(
                    foundPlayer.getName()
                            + " | Updated totals: "
                            + existingStats.getGoals()
                            + " goals, "
                            + existingStats.getAssists()
                            + " assists, "
                            + existingStats.getMatchesPlayed()
                            + " matches"
            );

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Statistics Updated",
                    "The new values were added to the existing totals.\n\n"
                            + "Player: "
                            + foundPlayer.getName()
                            + "\n"
                            + "Added Goals: "
                            + addedGoals
                            + "\n"
                            + "Added Assists: "
                            + addedAssists
                            + "\n"
                            + "Added Matches: "
                            + addedMatchesPlayed
                            + "\n\n"
                            + "Total Goals: "
                            + existingStats.getGoals()
                            + "\n"
                            + "Total Assists: "
                            + existingStats.getAssists()
                            + "\n"
                            + "Total Matches: "
                            + existingStats.getMatchesPlayed()
            );
        }

        clearStatisticsInputFields();
    }

    private Player findPlayerById(
            int playerId) {

        for (Player player : playerList) {

            if (player.getId() == playerId) {
                return player;
            }
        }

        return null;
    }

    @FXML
    public void search_report_button_on_action(
            ActionEvent actionEvent) {

        String playerIdText =
                search_player_id_textfield
                        .getText()
                        .trim();

        complete_report_textarea.clear();

        if (playerIdText.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Player ID",
                    "Please enter a Player ID."
            );

            return;
        }

        int playerId;

        try {

            playerId =
                    Integer.parseInt(
                            playerIdText
                    );

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Player ID",
                    "Player ID must be a whole number."
            );

            return;
        }

        if (playerId <= 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Player ID",
                    "Player ID must be greater than zero."
            );

            return;
        }

        if (!loadPlayersFromFile()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Data Error",
                    "Could not load player data from players.bin."
            );

            return;
        }

        Player foundPlayer =
                findPlayerById(playerId);

        if (foundPlayer == null) {

            complete_report_textarea.setText(
                    "No player exists with Player ID: "
                            + playerId
            );

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "No player exists with Player ID: "
                            + playerId
            );

            return;
        }

        String report =
                generateCompletePlayerReport(
                        foundPlayer
                );

        complete_report_textarea.setText(
                report
        );

        complete_report_textarea.positionCaret(0);

        PersonalStats foundStats =
                PersonalStatsManager
                        .findByPlayerId(playerId);

        if (foundStats != null) {

            personal_stats_table
                    .getSelectionModel()
                    .select(foundStats);

            personal_stats_table
                    .scrollTo(foundStats);
        }

        showAlert(
                Alert.AlertType.INFORMATION,
                "Report Loaded",
                "Complete player report loaded successfully."
        );
    }

    private String generateCompletePlayerReport(
            Player player) {

        StringBuilder report =
                new StringBuilder();

        int playerId =
                player.getId();

        report.append(
                "============================================================\n"
        );

        report.append(
                "                    COMPLETE PLAYER REPORT\n"
        );

        report.append(
                "============================================================\n\n"
        );

        appendPlayerProfile(
                report,
                player
        );

        appendPersonalStatistics(
                report,
                playerId
        );

        appendMedicalDeclaration(
                report,
                playerId
        );

        appendInjuryReports(
                report,
                playerId
        );

        appendTreatmentPlans(
                report,
                playerId
        );

        appendRehabilitationProgress(
                report,
                playerId
        );

        appendMedicalIncidents(
                report,
                playerId
        );

        appendOverallStatus(
                report,
                player
        );

        report.append(
                "\n============================================================\n"
        );

        report.append(
                "                      END OF REPORT\n"
        );

        report.append(
                "============================================================\n"
        );

        return report.toString();
    }

    private void appendPlayerProfile(
            StringBuilder report,
            Player player) {

        report.append(
                "PLAYER PROFILE\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        report.append("Player ID          : ")
                .append(player.getId())
                .append("\n");

        report.append("Player Name        : ")
                .append(safeText(player.getName()))
                .append("\n");

        report.append("Age                : ")
                .append(player.getAge())
                .append("\n");

        report.append("Team Name          : ")
                .append(
                        safeText(
                                player.getTeamName()
                        )
                )
                .append("\n");

        report.append("Playing Position   : ")
                .append(
                        safeText(
                                player.getPlayingPosition()
                        )
                )
                .append("\n");

        report.append("Player Type        : ")
                .append(
                        safeText(
                                player.getPlayerType()
                        )
                )
                .append("\n");

        report.append("Contact Number     : ")
                .append(
                        safeText(
                                player.getContactNumber()
                        )
                )
                .append("\n");

        report.append("Fitness Status     : ")
                .append(
                        safeText(
                                player.getFitnessStatus()
                        )
                )
                .append("\n");

        report.append("Match Eligibility  : ")
                .append(
                        safeText(
                                player.getMatchEligibilityStatus()
                        )
                )
                .append("\n\n");
    }

    private void appendPersonalStatistics(
            StringBuilder report,
            int playerId) {

        report.append(
                "PERSONAL STATISTICS\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        PersonalStats personalStats =
                PersonalStatsManager
                        .findByPlayerId(playerId);

        if (personalStats == null) {

            report.append(
                    "No personal statistics have been saved for this player.\n\n"
            );

            return;
        }

        report.append("Goals              : ")
                .append(personalStats.getGoals())
                .append("\n");

        report.append("Assists            : ")
                .append(personalStats.getAssists())
                .append("\n");

        report.append("Matches Played     : ")
                .append(
                        personalStats
                                .getMatchesPlayed()
                )
                .append("\n");

        report.append("Medical Condition  : ")
                .append(
                        safeText(
                                personalStats
                                        .getMedicalCondition()
                        )
                )
                .append("\n");

        report.append("Last Updated       : ")
                .append(
                        personalStats.getLastUpdated() == null
                                ? "Not available"
                                : personalStats.getLastUpdated()
                )
                .append("\n\n");
    }

    private void appendMedicalDeclaration(
            StringBuilder report,
            int playerId) {

        report.append(
                "MEDICAL DECLARATION\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        List<MedicalDeclaration> declarations =
                new ArrayList<>();

        for (MedicalDeclaration declaration :
                MedicalDeclarationManager
                        .getDeclarationList()) {

            if (declaration.getPlayerId()
                    == playerId) {

                declarations.add(declaration);
            }
        }

        if (declarations.isEmpty()) {

            report.append(
                    "No medical declaration was found.\n\n"
            );

            return;
        }

        declarations.sort(
                Comparator.comparing(
                        MedicalDeclaration::getDeclarationDate,
                        Comparator.nullsLast(
                                Comparator.naturalOrder()
                        )
                ).reversed()
        );

        MedicalDeclaration latestDeclaration =
                declarations.get(0);

        report.append("Declaration ID     : ")
                .append(
                        latestDeclaration
                                .getDeclarationId()
                )
                .append("\n");

        report.append("Medical Officer ID : ")
                .append(
                        latestDeclaration
                                .getMedicalOfficerId()
                )
                .append("\n");

        report.append("Declaration Date   : ")
                .append(
                        latestDeclaration
                                .getDeclarationDate() == null
                                ? "Not available"
                                : latestDeclaration
                                .getDeclarationDate()
                )
                .append("\n");

        report.append("Declaration Status : ")
                .append(
                        safeText(
                                latestDeclaration
                                        .getDeclarationStatus()
                        )
                )
                .append("\n");

        report.append("Remarks            : ")
                .append(
                        safeText(
                                latestDeclaration
                                        .getRemarks()
                        )
                )
                .append("\n\n");
    }

    private void appendInjuryReports(
            StringBuilder report,
            int playerId) {

        report.append(
                "INJURY REPORTS\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        List<InjuryReport> matchingReports =
                new ArrayList<>();

        for (InjuryReport injuryReport :
                InjuryReportManager
                        .getInjuryReportList()) {

            if (injuryReport.getPlayerId()
                    == playerId) {

                matchingReports.add(
                        injuryReport
                );
            }
        }

        if (matchingReports.isEmpty()) {

            report.append(
                    "No injury report was found.\n\n"
            );

            return;
        }

        matchingReports.sort(
                Comparator.comparing(
                        InjuryReport::getInjuryDate,
                        Comparator.nullsLast(
                                Comparator.naturalOrder()
                        )
                ).reversed()
        );

        for (int index = 0;
             index < matchingReports.size();
             index++) {

            InjuryReport injuryReport =
                    matchingReports.get(index);

            report.append("Injury #")
                    .append(index + 1)
                    .append("\n");

            report.append("Report ID          : ")
                    .append(
                            injuryReport
                                    .getReportId()
                    )
                    .append("\n");

            report.append("Injury Type        : ")
                    .append(
                            safeText(
                                    injuryReport
                                            .getInjuryType()
                            )
                    )
                    .append("\n");

            report.append("Injury Date        : ")
                    .append(
                            injuryReport
                                    .getInjuryDate() == null
                                    ? "Not available"
                                    : injuryReport
                                    .getInjuryDate()
                    )
                    .append("\n");

            report.append("Affected Body Part : ")
                    .append(
                            safeText(
                                    injuryReport
                                            .getAffectedBodyPart()
                            )
                    )
                    .append("\n");

            report.append("Severity           : ")
                    .append(
                            safeText(
                                    injuryReport
                                            .getSeverity()
                            )
                    )
                    .append("\n");

            report.append("Fitness Status     : ")
                    .append(
                            safeText(
                                    injuryReport
                                            .getFitnessStatus()
                            )
                    )
                    .append("\n");

            report.append("Active Injury      : ")
                    .append(
                            injuryReport.isActive()
                                    ? "Yes"
                                    : "No"
                    )
                    .append("\n");

            report.append("Additional Notes   : ")
                    .append(
                            safeText(
                                    injuryReport
                                            .getAdditionalNotes()
                            )
                    )
                    .append("\n");

            report.append(
                    "------------------------------------------------------------\n"
            );
        }

        report.append("\n");
    }

    private void appendTreatmentPlans(
            StringBuilder report,
            int playerId) {

        report.append(
                "TREATMENT PLANS\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        List<TreatmentPlan> matchingPlans =
                new ArrayList<>();

        for (TreatmentPlan treatmentPlan :
                TreatmentPlanManager
                        .getTreatmentPlanList()) {

            if (treatmentPlan.getPlayerId()
                    == playerId) {

                matchingPlans.add(
                        treatmentPlan
                );
            }
        }

        if (matchingPlans.isEmpty()) {

            report.append(
                    "No treatment plan was found.\n\n"
            );

            return;
        }

        matchingPlans.sort(
                Comparator.comparing(
                        TreatmentPlan::getFollowUpDate,
                        Comparator.nullsLast(
                                Comparator.naturalOrder()
                        )
                ).reversed()
        );

        for (int index = 0;
             index < matchingPlans.size();
             index++) {

            TreatmentPlan treatmentPlan =
                    matchingPlans.get(index);

            report.append("Treatment Plan #")
                    .append(index + 1)
                    .append("\n");

            report.append("Treatment ID       : ")
                    .append(
                            treatmentPlan
                                    .getTreatmentId()
                    )
                    .append("\n");

            report.append("Medical Officer ID : ")
                    .append(
                            treatmentPlan
                                    .getMedicalOfficerId()
                    )
                    .append("\n");

            report.append("Medicines          : ")
                    .append(
                            safeText(
                                    treatmentPlan
                                            .getPrescribedMedicines()
                            )
                    )
                    .append("\n");

            report.append("Physio Sessions    : ")
                    .append(
                            treatmentPlan
                                    .getPhysiotherapySessionsRequired()
                    )
                    .append("\n");

            report.append("Rest Duration      : ")
                    .append(
                            treatmentPlan
                                    .getRestDurationDays()
                    )
                    .append(" day(s)\n");

            report.append("Dietary Instruction: ")
                    .append(
                            safeText(
                                    treatmentPlan
                                            .getDietaryInstruction()
                            )
                    )
                    .append("\n");

            report.append("Follow-up Date     : ")
                    .append(
                            treatmentPlan
                                    .getFollowUpDate() == null
                                    ? "Not available"
                                    : treatmentPlan
                                    .getFollowUpDate()
                    )
                    .append("\n");

            report.append("Active Plan        : ")
                    .append(
                            treatmentPlan.isActive()
                                    ? "Yes"
                                    : "No"
                    )
                    .append("\n");

            report.append(
                    "------------------------------------------------------------\n"
            );
        }

        report.append("\n");
    }

    private void appendRehabilitationProgress(
            StringBuilder report,
            int playerId) {

        report.append(
                "REHABILITATION PROGRESS\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        List<RehabilitationProgress> progressRecords =
                new ArrayList<>();

        for (RehabilitationProgress progress :
                RehabilitationProgressManager
                        .getProgressList()) {

            if (progress.getPlayerId()
                    == playerId) {

                progressRecords.add(
                        progress
                );
            }
        }

        if (progressRecords.isEmpty()) {

            report.append(
                    "No rehabilitation progress was found.\n\n"
            );

            return;
        }

        progressRecords.sort(
                Comparator.comparing(
                        RehabilitationProgress::getProgressDate,
                        Comparator.nullsLast(
                                Comparator.naturalOrder()
                        )
                ).reversed()
        );

        for (int index = 0;
             index < progressRecords.size();
             index++) {

            RehabilitationProgress progress =
                    progressRecords.get(index);

            report.append("Progress Record #")
                    .append(index + 1)
                    .append("\n");

            report.append("Progress ID        : ")
                    .append(
                            progress.getProgressId()
                    )
                    .append("\n");

            report.append("Progress Date      : ")
                    .append(
                            progress.getProgressDate() == null
                                    ? "Not available"
                                    : progress.getProgressDate()
                    )
                    .append("\n");

            report.append("Recovery Percentage: ")
                    .append(
                            progress
                                    .getRecoveryPercentage()
                    )
                    .append("%\n");

            report.append("Sessions Completed : ")
                    .append(
                            progress
                                    .getPhysiotherapySessionsCompleted()
                    )
                    .append("\n");

            report.append("Physical Condition : ")
                    .append(
                            safeText(
                                    progress
                                            .getPhysicalConditionNotes()
                            )
                    )
                    .append("\n");

            report.append("Updated Fitness    : ")
                    .append(
                            safeText(
                                    progress
                                            .getUpdatedFitnessStatus()
                            )
                    )
                    .append("\n");

            report.append(
                    "------------------------------------------------------------\n"
            );
        }

        report.append("\n");
    }

    private void appendMedicalIncidents(
            StringBuilder report,
            int playerId) {

        report.append(
                "MATCH-DAY MEDICAL INCIDENTS\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        List<MatchDayMedicalIncident> incidents =
                new ArrayList<>();

        for (MatchDayMedicalIncident incident :
                MedicalIncidentManager
                        .getIncidentList()) {

            if (incident.getPlayerId()
                    == playerId) {

                incidents.add(
                        incident
                );
            }
        }

        if (incidents.isEmpty()) {

            report.append(
                    "No match-day medical incident was found.\n\n"
            );

            return;
        }

        incidents.sort(
                Comparator.comparing(
                        MatchDayMedicalIncident::getMatchDate,
                        Comparator.nullsLast(
                                Comparator.naturalOrder()
                        )
                ).reversed()
        );

        for (int index = 0;
             index < incidents.size();
             index++) {

            MatchDayMedicalIncident incident =
                    incidents.get(index);

            report.append("Medical Incident #")
                    .append(index + 1)
                    .append("\n");

            report.append("Incident ID        : ")
                    .append(
                            incident.getIncidentId()
                    )
                    .append("\n");

            report.append("Match Date         : ")
                    .append(
                            incident.getMatchDate() == null
                                    ? "Not available"
                                    : incident.getMatchDate()
                    )
                    .append("\n");

            report.append("Match Minute       : ")
                    .append(
                            incident.getMatchMinute()
                    )
                    .append("\n");

            report.append("Incident Type      : ")
                    .append(
                            safeText(
                                    incident.getIncidentType()
                            )
                    )
                    .append("\n");

            report.append("Severity           : ")
                    .append(
                            safeText(
                                    incident.getSeverity()
                            )
                    )
                    .append("\n");

            report.append("Action Taken       : ")
                    .append(
                            safeText(
                                    incident.getActionTaken()
                            )
                    )
                    .append("\n");

            report.append(
                    "------------------------------------------------------------\n"
            );
        }

        report.append("\n");
    }

    private void appendOverallStatus(
            StringBuilder report,
            Player player) {

        report.append(
                "OVERALL STATUS\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        boolean activeInjuryFound =
                false;

        for (InjuryReport injuryReport :
                InjuryReportManager
                        .getInjuryReportList()) {

            if (injuryReport.getPlayerId()
                    == player.getId()
                    && injuryReport.isActive()) {

                activeInjuryFound = true;
                break;
            }
        }

        boolean activeTreatmentFound =
                false;

        for (TreatmentPlan treatmentPlan :
                TreatmentPlanManager
                        .getTreatmentPlanList()) {

            if (treatmentPlan.getPlayerId()
                    == player.getId()
                    && treatmentPlan.isActive()) {

                activeTreatmentFound = true;
                break;
            }
        }

        report.append("Fitness Status      : ")
                .append(
                        safeText(
                                player.getFitnessStatus()
                        )
                )
                .append("\n");

        report.append("Match Eligibility   : ")
                .append(
                        safeText(
                                player
                                        .getMatchEligibilityStatus()
                        )
                )
                .append("\n");

        report.append("Active Injury       : ")
                .append(
                        activeInjuryFound
                                ? "Yes"
                                : "No"
                )
                .append("\n");

        report.append("Active Treatment    : ")
                .append(
                        activeTreatmentFound
                                ? "Yes"
                                : "No"
                )
                .append("\n");

        String finalStatus;

        if (activeInjuryFound
                || activeTreatmentFound) {

            finalStatus =
                    "Medical review may be required.";

        } else if ("Fit".equalsIgnoreCase(
                player.getFitnessStatus())
                && "Eligible".equalsIgnoreCase(
                player.getMatchEligibilityStatus())) {

            finalStatus =
                    "Available for match selection.";

        } else {

            finalStatus =
                    "Check fitness and eligibility status.";
        }

        report.append("Final Assessment    : ")
                .append(finalStatus)
                .append("\n");
    }

    @SuppressWarnings("unchecked")
    private boolean loadPlayersFromFile() {

        playerList.clear();

        File playerFile =
                new File(
                        PLAYER_FILE_NAME
                );

        if (!playerFile.exists()) {

            System.out.println(
                    "players.bin does not exist at: "
                            + playerFile.getAbsolutePath()
            );

            return false;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new FileInputStream(
                                     playerFile
                             ))) {

            Object savedObject =
                    inputStream.readObject();

            if (!(savedObject
                    instanceof ArrayList<?> loadedList)) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Invalid Player File",
                        "players.bin does not contain an ArrayList."
                );

                return false;
            }

            for (Object item : loadedList) {

                if (item instanceof Player player) {

                    playerList.add(
                            player
                    );
                }
            }

            return !playerList.isEmpty();

        } catch (InvalidClassException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Incompatible Player File",
                    "players.bin was created using an older Player or User class.\n\n"
                            + "Delete players.bin and save the Player profile again."
            );

        } catch (FileNotFoundException e) {

            System.out.println(
                    "players.bin could not be found."
            );

        } catch (IOException |
                 ClassNotFoundException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player File Error",
                    "Could not load player data.\n\n"
                            + e.getMessage()
            );
        }

        return false;
    }

    private String safeText(
            String value) {

        if (value == null
                || value.isBlank()) {

            return "Not available";
        }

        return value;
    }

    private void showAlert(
            Alert.AlertType alertType,
            String title,
            String message) {

        Alert alert =
                new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
