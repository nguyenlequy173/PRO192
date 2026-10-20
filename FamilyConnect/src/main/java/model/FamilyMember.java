
package model;

public class FamilyMember {

    private long memberId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String dateOfBirth;

    public FamilyMember() {
    }

    public FamilyMember(long memberId, String firstName,
                        String lastName, String dateOfBirth) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        updateFullName();
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        updateFullName();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        updateFullName();
    }

    public String getFullName() {
        return fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    private void updateFullName() {
        String first = firstName == null ? "" : firstName.trim();
        String last = lastName == null ? "" : lastName.trim();

        this.fullName = (first + " " + last).trim();
    }

    @Override
    public String toString() {
        return "FamilyMember{"
                + "memberId=" + memberId
                + ", fullName='" + fullName + '\''
                + ", dateOfBirth='" + dateOfBirth + '\''
                + '}';
    }
}
