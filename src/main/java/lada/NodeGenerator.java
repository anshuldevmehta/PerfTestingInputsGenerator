package lada;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lada.NodeEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
    public class NodeGenerator {

        public static final String DEVICE_ID_BASE_VALUE="METER_D1_0";
        public static final String MESH_NETWORK_BASE_VALUE="fd5b:2b5b:5bc4:";
        public static final String NODE_ID_BASE_VALUE="00-00-00-00-00-0";

        public static void main(String[] args) {
            generateNodes();
            generateMeshNetworks();
        }

        private static void generateMeshNetworks()
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("MeshNetworks.csv"))) {
                Map<String, String> keyValuePairs = new HashMap<>();

                // Populate the map with unique key-value pairs
                for (int nodeId = 1; nodeId <= 32000; nodeId++) {
                    String interimNodeId=doNodeIdDrama(nodeId);
                    keyValuePairs.put(interimNodeId,doMeshNetworkDrama(String.valueOf(nodeId),interimNodeId));
                    //keyValuePairs.put(String.valueOf(nodeId),doNodeIdDrama(nodeId));
                }

                // Write the key-value pairs to the CSV file
                for (Map.Entry<String, String> entry : keyValuePairs.entrySet()) {
                    writer.write(entry.getKey() + "," + entry.getValue());
                    writer.newLine(); // Move to the next line after writing each pair
                }

                System.out.println("Successfully wrote 32000 unique key-value pairs to output.csv");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }

        private static void generateNodes()
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("NodeEvents.csv"))) {
                Map<String, String> keyValuePairs = new HashMap<>();

                // Populate the map with unique key-value pairs
                for (int nodeId = 1; nodeId <= 32000; nodeId++) {
                    // keyValuePairs.put(nodeId, String.valueOf((char) ('A' + nodeId % 26)));
                    keyValuePairs.put(DEVICE_ID_BASE_VALUE+String.format("%05d", nodeId).trim(),doNodeIdDrama(nodeId));
                    //keyValuePairs.put(String.valueOf(nodeId),doNodeIdDrama(nodeId));
                }

                // Write the key-value pairs to the CSV file
                for (Map.Entry<String, String> entry : keyValuePairs.entrySet()) {
                    writer.write(entry.getKey() + "," + entry.getValue());
                    writer.newLine(); // Move to the next line after writing each pair
                }

                System.out.println("Successfully wrote 32000 unique key-value pairs to output.csv");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }
        private static String doNodeIdDrama(int nodeId)
        {
            int totalLength = 7; // Including hyphens
            int partLength = totalLength - 2; // Subtracting 2 for hyphens
            boolean appendHyphen=true;
            String nodeIdString =  String.format("%05d", nodeId);
            // Ensure each part has the required length by padding with zeros

            StringBuilder sb = new StringBuilder(NODE_ID_BASE_VALUE);
            sb.append(nodeIdString.charAt(0)); // Start with the first character

            for (int i = 1; i < nodeIdString.length(); i += 1) { // Iterate over every second character starting from the second character
                if(appendHyphen) {
                    sb.append('-');
                    appendHyphen = false;
                }
                else
                    appendHyphen=true;
                sb.append(nodeIdString.charAt(i));
               // Append a hyphen// Append the current character
            }

            return sb.toString().trim();

        }

        private static String doMeshNetworkDrama(String meshNetworkId,String nodeId) throws JsonProcessingException {
            // Ensure each part has the required length by padding with zeros

            StringBuilder sb = new StringBuilder(MESH_NETWORK_BASE_VALUE);
            sb.append(meshNetworkId); // Start with the first character
            NodeEvent nodeEvent=new NodeEvent();
            nodeEvent.setNodeId(nodeId);
            nodeEvent.setMeshNetworkPrefix(sb.toString().trim());
            ObjectMapper mapper=new ObjectMapper();
            String nodeEventJson=mapper.writeValueAsString(nodeEvent);
            //String nodeEventJson = mapper.writeValueAsString(nodeEvent);
            return nodeEventJson;

        }
    }

