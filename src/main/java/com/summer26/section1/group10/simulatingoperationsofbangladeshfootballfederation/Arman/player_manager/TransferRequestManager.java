package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_manager;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.TransferRequest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TransferRequestManager {

    private static final List<TransferRequest> transferRequestList = new ArrayList<>();
    private static final String FILE_NAME = "transfer-requests.bin";

    static {
        loadFromFile();
    }

    public static List<TransferRequest> getTransferRequestList() {
        return transferRequestList;
    }

    public static void addTransferRequest(TransferRequest transferRequest) {
        transferRequestList.add(transferRequest);
    }

    public static void removeTransferRequest(TransferRequest transferRequest) {
        transferRequestList.remove(transferRequest);
    }

    public static TransferRequest findByRequestId(int requestId) {
        for (TransferRequest transferRequest : transferRequestList) {
            if (transferRequest.getRequestId() == requestId) {
                return transferRequest;
            }
        }

        return null;
    }

    public static List<TransferRequest> findByPlayerId(int playerId) {
        List<TransferRequest> matchingRequests = new ArrayList<>();

        for (TransferRequest transferRequest : transferRequestList) {
            if (transferRequest.getPlayerId() == playerId) {
                matchingRequests.add(transferRequest);
            }
        }

        return matchingRequests;
    }

    public static boolean hasPendingRequest(
            int playerId,
            String requestedTeam) {

        for (TransferRequest transferRequest : transferRequestList) {
            boolean samePlayer =
                    transferRequest.getPlayerId() == playerId;

            boolean sameTeam =
                    transferRequest.getRequestedTeam() != null
                            && transferRequest.getRequestedTeam()
                            .equalsIgnoreCase(requestedTeam);

            boolean pending =
                    "Pending".equalsIgnoreCase(transferRequest.getStatus());

            if (samePlayer && sameTeam && pending) {
                return true;
            }
        }

        return false;
    }

    public static int generateRequestId() {
        int highestRequestId = 0;

        for (TransferRequest transferRequest : transferRequestList) {
            if (transferRequest.getRequestId() > highestRequestId) {
                highestRequestId = transferRequest.getRequestId();
            }
        }

        return highestRequestId + 1;
    }

    @SuppressWarnings("unchecked")
    private static void loadFromFile() {
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            transferRequestList.clear();

            transferRequestList.addAll(
                    (ArrayList<TransferRequest>) inputStream.readObject()
            );

            System.out.println(
                    "Transfer requests loaded successfully: "
                            + transferRequestList.size()
            );

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load transfer request data.");
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            ArrayList<TransferRequest> temporaryList =
                    new ArrayList<>(transferRequestList);

            outputStream.writeObject(temporaryList);
            outputStream.flush();

            System.out.println("Transfer requests saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not save transfer request data.");
        }
    }
}