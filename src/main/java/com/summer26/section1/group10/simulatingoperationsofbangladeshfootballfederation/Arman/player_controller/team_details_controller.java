package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class team_details_controller {

    @FXML
    private ComboBox<String> team_name_combobox;

    @FXML
    private Label team_name_label;

    @FXML
    private Label captain_name_label;

    @FXML
    private Label coach_name_label;

    @FXML
    private Label total_members_label;

    @FXML
    private Label team_members_information_label;

    @FXML
    private ListView<String> team_members_listview;

    @FXML
    private TextArea team_description_textarea;

    private final List<Player> playerList =
            new ArrayList<>();

    private final Map<String, String> coachMap =
            new LinkedHashMap<>();

    private final Map<String, String> defaultCaptainMap =
            new LinkedHashMap<>();

    private final Map<String, String> descriptionMap =
            new LinkedHashMap<>();

    private final Map<String, List<String>> defaultMembersMap =
            new LinkedHashMap<>();

    private static final String PLAYER_FILE_NAME =
            "players.bin";

    @FXML
    public void initialize() {

        initializeTeamNames();
        initializeDefaultCoaches();
        initializeDefaultCaptains();
        initializeTeamDescriptions();
        initializeDefaultMembers();

        team_description_textarea.setWrapText(true);

        clearDisplayedInformation();
    }

    private void initializeTeamNames() {

        team_name_combobox.getItems().setAll(
                "Abahani Limited Dhaka",
                "Bashundhara Kings",
                "Mohammedan Sporting Club",
                "Sheikh Russel KC",
                "Sheikh Jamal Dhanmondi Club",
                "Bangladesh Police FC",
                "Brothers Union",
                "Rahmatganj MFS",
                "Fortis FC",
                "Chittagong Abahani",
                "No Current Team"
        );
    }

    private void initializeDefaultCoaches() {

        coachMap.put(
                "Abahani Limited Dhaka",
                "Coach Mario Lemos"
        );

        coachMap.put(
                "Bashundhara Kings",
                "Coach Valeriu Tita"
        );

        coachMap.put(
                "Mohammedan Sporting Club",
                "Coach Alfaz Ahmed"
        );

        coachMap.put(
                "Sheikh Russel KC",
                "Coach Zulfiker Mahmud Mintu"
        );

        coachMap.put(
                "Sheikh Jamal Dhanmondi Club",
                "Coach Maruful Haque"
        );

        coachMap.put(
                "Bangladesh Police FC",
                "Coach Aristică Cioabă"
        );

        coachMap.put(
                "Brothers Union",
                "Coach Rezaul Karim"
        );

        coachMap.put(
                "Rahmatganj MFS",
                "Coach Kamal Babu"
        );

        coachMap.put(
                "Fortis FC",
                "Coach Masud Parvez Kaiser"
        );

        coachMap.put(
                "Chittagong Abahani",
                "Coach Saiful Bari Titu"
        );

        coachMap.put(
                "No Current Team",
                "Not assigned"
        );
    }

    private void initializeDefaultCaptains() {

        defaultCaptainMap.put(
                "Abahani Limited Dhaka",
                "Default Captain - Rahim"
        );

        defaultCaptainMap.put(
                "Bashundhara Kings",
                "Default Captain - Tariq"
        );

        defaultCaptainMap.put(
                "Mohammedan Sporting Club",
                "Default Captain - Jamal"
        );

        defaultCaptainMap.put(
                "Sheikh Russel KC",
                "Default Captain - Kabir"
        );

        defaultCaptainMap.put(
                "Sheikh Jamal Dhanmondi Club",
                "Default Captain - Hasan"
        );

        defaultCaptainMap.put(
                "Bangladesh Police FC",
                "Default Captain - Arif"
        );

        defaultCaptainMap.put(
                "Brothers Union",
                "Default Captain - Rafi"
        );

        defaultCaptainMap.put(
                "Rahmatganj MFS",
                "Default Captain - Sakib"
        );

        defaultCaptainMap.put(
                "Fortis FC",
                "Default Captain - Nayeem"
        );

        defaultCaptainMap.put(
                "Chittagong Abahani",
                "Default Captain - Fahim"
        );

        defaultCaptainMap.put(
                "No Current Team",
                "Not available"
        );
    }

    private void initializeTeamDescriptions() {

        descriptionMap.put(
                "Abahani Limited Dhaka",
                "Abahani Limited Dhaka is one of the established football "
                        + "clubs in Bangladesh. The team emphasizes technical "
                        + "football, discipline, teamwork and competitive performance."
        );

        descriptionMap.put(
                "Bashundhara Kings",
                "Bashundhara Kings is a professional football club known for "
                        + "strong squad development, modern training facilities "
                        + "and competitive domestic football."
        );

        descriptionMap.put(
                "Mohammedan Sporting Club",
                "Mohammedan Sporting Club is a historic football club with a "
                        + "large supporter base. The club values tradition, "
                        + "team spirit and high-level competition."
        );

        descriptionMap.put(
                "Sheikh Russel KC",
                "Sheikh Russel Krira Chakra focuses on developing professional "
                        + "players and maintaining a balanced, competitive squad."
        );

        descriptionMap.put(
                "Sheikh Jamal Dhanmondi Club",
                "Sheikh Jamal Dhanmondi Club participates in national football "
                        + "competitions and emphasizes player development, fitness "
                        + "and tactical discipline."
        );

        descriptionMap.put(
                "Bangladesh Police FC",
                "Bangladesh Police FC represents discipline, physical fitness "
                        + "and organized teamwork while competing in national "
                        + "football competitions."
        );

        descriptionMap.put(
                "Brothers Union",
                "Brothers Union is a traditional football club focused on "
                        + "teamwork, youth development and competitive football."
        );

        descriptionMap.put(
                "Rahmatganj MFS",
                "Rahmatganj Muslim Friends Society is a historic football team "
                        + "known for local talent development and spirited competition."
        );

        descriptionMap.put(
                "Fortis FC",
                "Fortis FC is a developing professional football club that "
                        + "focuses on structured training, young players and "
                        + "long-term team growth."
        );

        descriptionMap.put(
                "Chittagong Abahani",
                "Chittagong Abahani represents Chattogram in professional "
                        + "football and focuses on competitive performance, "
                        + "regional talent and team development."
        );

        descriptionMap.put(
                "No Current Team",
                "This option represents players who are not currently registered "
                        + "with any team. No coach, captain or official team roster "
                        + "is assigned."
        );
    }

    private void initializeDefaultMembers() {

        defaultMembersMap.put(
                "Abahani Limited Dhaka",
                new ArrayList<>(List.of(
                        "Rahim Ahmed - Goalkeeper",
                        "Sabbir Hossain - Defender",
                        "Tanvir Islam - Midfielder"
                ))
        );

        defaultMembersMap.put(
                "Bashundhara Kings",
                new ArrayList<>(List.of(
                        "Tariq Hasan - Goalkeeper",
                        "Mahin Rahman - Defender",
                        "Nabil Ahmed - Forward"
                ))
        );

        defaultMembersMap.put(
                "Mohammedan Sporting Club",
                new ArrayList<>(List.of(
                        "Jamal Khan - Midfielder",
                        "Rafiul Islam - Defender",
                        "Shanto Ahmed - Forward"
                ))
        );

        defaultMembersMap.put(
                "Sheikh Russel KC",
                new ArrayList<>(List.of(
                        "Kabir Hossain - Goalkeeper",
                        "Maruf Ahmed - Defender",
                        "Rasel Mia - Midfielder"
                ))
        );

        defaultMembersMap.put(
                "Sheikh Jamal Dhanmondi Club",
                new ArrayList<>(List.of(
                        "Hasan Ali - Forward",
                        "Imran Ahmed - Midfielder",
                        "Fardin Rahman - Defender"
                ))
        );

        defaultMembersMap.put(
                "Bangladesh Police FC",
                new ArrayList<>(List.of(
                        "Arif Hossain - Defender",
                        "Siam Ahmed - Goalkeeper",
                        "Nafis Hasan - Midfielder"
                ))
        );

        defaultMembersMap.put(
                "Brothers Union",
                new ArrayList<>(List.of(
                        "Rafi Ahmed - Forward",
                        "Noman Islam - Defender",
                        "Jubayer Hasan - Midfielder"
                ))
        );

        defaultMembersMap.put(
                "Rahmatganj MFS",
                new ArrayList<>(List.of(
                        "Sakib Rahman - Midfielder",
                        "Shafin Ahmed - Forward",
                        "Riad Hossain - Defender"
                ))
        );

        defaultMembersMap.put(
                "Fortis FC",
                new ArrayList<>(List.of(
                        "Nayeem Hasan - Goalkeeper",
                        "Tamim Islam - Defender",
                        "Sajid Rahman - Forward"
                ))
        );

        defaultMembersMap.put(
                "Chittagong Abahani",
                new ArrayList<>(List.of(
                        "Fahim Ahmed - Forward",
                        "Rahat Hasan - Midfielder",
                        "Arafat Islam - Defender"
                ))
        );

        defaultMembersMap.put(
                "No Current Team",
                new ArrayList<>()
        );
    }

    @FXML
    public void search_button_on_action(
            ActionEvent actionEvent) {

        String selectedTeam =
                team_name_combobox.getValue();

        if (selectedTeam == null
                || selectedTeam.isBlank()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Team Not Selected",
                    "Please select a team before searching."
            );

            return;
        }

        /*
         * Reload players.bin each time so changes made from
         * Player Profile immediately appear in Team Details.
         */
        loadPlayersFromFile();

        displayTeamInformation(selectedTeam);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Team Found",
                "Team information loaded successfully."
        );
    }

    private void displayTeamInformation(
            String selectedTeam) {

        team_name_label.setText(selectedTeam);

        coach_name_label.setText(
                coachMap.getOrDefault(
                        selectedTeam,
                        "Not available"
                )
        );

        team_description_textarea.setText(
                descriptionMap.getOrDefault(
                        selectedTeam,
                        "No team description is available."
                )
        );

        /*
         * LinkedHashSet keeps insertion order and prevents
         * duplicate member entries.
         */
        Set<String> displayedMembers =
                new LinkedHashSet<>();

        List<String> defaultMembers =
                defaultMembersMap.get(selectedTeam);

        if (defaultMembers != null) {
            displayedMembers.addAll(defaultMembers);
        }

        String captainName =
                defaultCaptainMap.getOrDefault(
                        selectedTeam,
                        "Not available"
                );

        for (Player player : playerList) {

            if (player.getTeamName() == null) {
                continue;
            }

            if (!player.getTeamName()
                    .equalsIgnoreCase(selectedTeam)) {

                continue;
            }

            String memberInformation =
                    player.getName()
                            + " - "
                            + player.getPlayingPosition()
                            + " - "
                            + safePlayerType(player);

            displayedMembers.add(
                    memberInformation
            );

            if ("Captain".equalsIgnoreCase(
                    player.getPlayerType())) {

                captainName =
                        player.getName()
                                + " (Saved Player)";
            }
        }

        captain_name_label.setText(captainName);

        team_members_listview.getItems().setAll(
                displayedMembers
        );

        total_members_label.setText(
                String.valueOf(
                        displayedMembers.size()
                )
        );

        if (displayedMembers.isEmpty()) {

            team_members_information_label.setText(
                    "No team members are available for this team."
            );

        } else {

            team_members_information_label.setText(
                    "Showing "
                            + displayedMembers.size()
                            + " team member(s)."
            );
        }
    }

    private String safePlayerType(Player player) {

        String playerType =
                player.getPlayerType();

        if (playerType == null
                || playerType.isBlank()) {

            return "Regular Player";
        }

        return playerType;
    }

    @SuppressWarnings("unchecked")
    private boolean loadPlayersFromFile() {

        playerList.clear();

        File playerFile =
                new File(PLAYER_FILE_NAME);

        System.out.println(
                "Loading players from: "
                        + playerFile.getAbsolutePath()
        );

        if (!playerFile.exists()) {

            System.out.println(
                    "players.bin does not exist yet."
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
                        "players.bin does not contain valid Player data."
                );

                return false;
            }

            for (Object item : loadedList) {

                if (item instanceof Player player) {
                    playerList.add(player);
                }
            }

            System.out.println(
                    "Players loaded successfully: "
                            + playerList.size()
            );

            return true;

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

    private void clearDisplayedInformation() {

        team_name_label.setText(
                "Not selected"
        );

        captain_name_label.setText(
                "Not available"
        );

        coach_name_label.setText(
                "Not available"
        );

        total_members_label.setText(
                "0"
        );

        team_members_listview
                .getItems()
                .clear();

        team_description_textarea
                .clear();

        team_members_information_label.setText(
                "Select a team to view its members."
        );
    }

    @FXML
    public void team_name_combobox_on_action(
            ActionEvent actionEvent) {

        /*
         * Details are intentionally loaded only after
         * the Search button is clicked.
         */
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

        Alert alert =
                new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}