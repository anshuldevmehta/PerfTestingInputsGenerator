package lada;

public class NodeEvent {
    private String eventType;
    private String nodeId;

    private String type;

    private String status;

    private String meshNetworkControllerName;

    private String meshNetworkPrefix;

    private String gatewayNodeId;

    private String deviceId;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMeshNetworkControllerName() {
        return meshNetworkControllerName;
    }

    public void setMeshNetworkControllerName(String meshNetworkControllerName) {
        this.meshNetworkControllerName = meshNetworkControllerName;
    }

    public String getMeshNetworkPrefix() {
        return meshNetworkPrefix;
    }

    public void setMeshNetworkPrefix(String meshNetworkPrefix) {
        this.meshNetworkPrefix = meshNetworkPrefix;
    }

    public String getGatewayNodeId() {
        return gatewayNodeId;
    }

    public void setGatewayNodeId(String gatewayNodeId) {
        this.gatewayNodeId = gatewayNodeId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public boolean isMeshNetworkPrefixUpdated(NodeEvent newNodeEvent) {
        return this.meshNetworkPrefix.equalsIgnoreCase(newNodeEvent.getMeshNetworkPrefix());
    }

}
