package dejavu.appzonegroup.com.dejavuandroid.Models;

/**
 * Created by CrowdStar on 3/8/2015.
 */
public class UI_Model {

    private boolean isRequired;

    private boolean isUnique;
    private boolean isPrimaryIdentifier;
    private boolean isToSave;
    private boolean shouldSum;
    private boolean isEnum;
    private boolean isRandomlyGenerated;
    private boolean isEntityList;
    private boolean isMultipleFile;
    private int autoGenMode;
    private int parameterMode;
    private String failureMessage;
    private String name;
    private int id;
    private String successMessage;
    private String eventDescription;
    private String event;
    private String validationFields;
    private String attributeString;
    private String value;
    private String valueKey;
    private String[] controlItems;
    private int typeID;
    private int flowID;


    public void setValueKey(String valueKey) {
        this.valueKey = valueKey;
    }

    public String getValueKey() {
        return valueKey;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAttributeString(String attributeString) {
        this.attributeString = attributeString;
    }

    public String getAttributeString() {
        return attributeString;
    }

    public void setFlowID(int flowID) {
        this.flowID = flowID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getFlowID() {
        return flowID;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setAutoGenMode(int autoGenMode) {
        this.autoGenMode = autoGenMode;
    }

    public void setParameterMode(int parameterMode) {
        this.parameterMode = parameterMode;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public void setValidationFields(String validationFields) {
        this.validationFields = validationFields;
    }


    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setControlItems(String[] contolItems) {
        this.controlItems = contolItems;
    }

    public void setEntityList(boolean isEntityList) {
        this.isEntityList = isEntityList;
    }

    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public void setUnique(boolean isUnique) {
        this.isUnique = isUnique;
    }

    public void setRandomlyGenerated(boolean isRandomlyGenerated) {
        this.isRandomlyGenerated = isRandomlyGenerated;
    }

    public void setMultipleFile(boolean isMultipleFile) {
        this.isMultipleFile = isMultipleFile;
    }

    public void setPrimaryIdentifier(boolean isPrimaryIdentifier) {
        this.isPrimaryIdentifier = isPrimaryIdentifier;
    }

    public void setToSave(boolean isToSave) {
        this.isToSave = isToSave;
    }

    public void setShouldSum(boolean shouldSum) {
        this.shouldSum = shouldSum;
    }

    public void setEnum(boolean isEnum) {
        this.isEnum = isEnum;
    }


    public boolean isEnum() {
        return isEnum;
    }

    public boolean isShouldSum() {
        return shouldSum;
    }

    public boolean isToSave() {
        return isToSave;
    }

    public boolean isRandomlyGenerated() {
        return isRandomlyGenerated;
    }

    public boolean isPrimaryIdentifier() {
        return isPrimaryIdentifier;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public boolean isEntityList() {
        return isEntityList;
    }

    public int getAutoGenMode() {
        return autoGenMode;
    }

    public int getParameterMode() {
        return parameterMode;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getValidationFields() {
        return validationFields;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEvent() {
        return event;
    }

    public boolean isMultipleFile() {
        return isMultipleFile;
    }

    public String[] getControlItems() {
        return controlItems;
    }
}
