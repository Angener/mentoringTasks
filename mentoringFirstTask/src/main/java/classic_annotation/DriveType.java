package classic_annotation;

public enum DriveType {
    AWD("AWD"),
    FRONT_WD("2WD");

    private String driveType;

    DriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getDriveType() {
        return driveType;
    }
}
