package co.moonmonkeylabs.quantum.model;

/**
 * Created by root on 2016/01/30.
 */
public class Car extends AbstractDBEntity {

    private final String DB_KEY = "car";

    String registrationNumber;
    String rankNumber;
    String licenseExpiryDate;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRankNumber() {
        return rankNumber;
    }

    public void setRankNumber(String rankNumber) {
        this.rankNumber = rankNumber;
    }

    public String getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(String licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    @Override
    public String DBKey() {
        return DB_KEY;
    }
}
