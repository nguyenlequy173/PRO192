
package model;

public class Relationship {

    private long relationshipId;
    private long memberId1;
    private long memberId2;
    private String relationshipType;

    public Relationship() {
    }

    public Relationship(long relationshipId, long memberId1,
                        long memberId2, String relationshipType) {
        this.relationshipId = relationshipId;
        this.memberId1 = memberId1;
        this.memberId2 = memberId2;
        this.relationshipType = relationshipType;
    }

    public long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public long getMemberId1() {
        return memberId1;
    }

    public void setMemberId1(long memberId1) {
        this.memberId1 = memberId1;
    }

    public long getMemberId2() {
        return memberId2;
    }

    public void setMemberId2(long memberId2) {
        this.memberId2 = memberId2;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    @Override
    public String toString() {
        return "Relationship{"
                + "relationshipId=" + relationshipId
                + ", memberId1=" + memberId1
                + ", memberId2=" + memberId2
                + ", relationshipType='" + relationshipType + '\''
                + '}';
    }
}
