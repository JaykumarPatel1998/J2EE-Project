package mvcdemo.model;
 
public class Claim {
    private int claim_id;
    private int registration_id;
    private String date_of_claim;
    private String description;
    private String status;
 
    public Claim(int claim_id, int registration_id, String date_of_claim, String description, String status) {
        this.claim_id  = claim_id;
        this.registration_id = registration_id;
        this.date_of_claim = date_of_claim;
        this.description = description;
        this.status = status;
    }
 
    // Getters and setters for each attribute
    public int getClaim_id() {
        return claim_id;
    }
 
    public void setClaim_id(int claim_id) {
        this.claim_id = claim_id;
    }
 
    public int getRegistration_id() {
        return registration_id;
    }
 
    public void setRegistration_id(int registration_id) {
        this.registration_id = registration_id;
    }
 
    public String getDate_of_claim() {
        return date_of_claim;
    }
 
    public void setDate_of_claim(String date_of_claim) {
        this.date_of_claim = date_of_claim;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    @Override
    public String toString() {
        return "Claim{" +
                "claim_id=" + claim_id +
                ", registration_id=" + registration_id +
                ", date_of_claim='" + date_of_claim + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}