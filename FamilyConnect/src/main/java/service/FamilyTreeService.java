
package service;

import model.FamilyMember;
import model.Relationship;
import util.FileService;
import java.util.ArrayList;
import java.util.List;

public class FamilyTreeService {

    private final FileService fileService;

    private final String MEMBER_FILE = "data/family_members.txt";
    private final String RELATIONSHIP_FILE = "data/relationships.txt";

    public FamilyTreeService() {
        fileService = new FileService();
    }

    // Get all family members
    public List<FamilyMember> getAllMembers() {
        List<FamilyMember> members = new ArrayList<>();

        List<String> lines = fileService.readFile(MEMBER_FILE);

        for (String line : lines) {
            try {
                String[] parts = line.split("\\|", -1);

                if (parts.length != 5) {
                    continue;
                }

                long memberId = Long.parseLong(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                String fullName = parts[3];
                String dateOfBirth = parts[4];

                FamilyMember member = new FamilyMember(
                        memberId,
                        firstName,
                        lastName,
                        dateOfBirth
                );

                members.add(member);

            } catch (NumberFormatException e) {
                System.out.println("Invalid member data: " + line);
            }
        }

        return members;
    }

    // Find a family member by ID
    public FamilyMember findMemberById(long memberId) {
        for (FamilyMember member : getAllMembers()) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }

        return null;
    }

    // Add a new family member
    public boolean addMember(FamilyMember member) {
        if (member == null) {
            return false;
        }

        if (member.getFirstName() == null
                || member.getFirstName().trim().isEmpty()) {
            System.out.println("First name cannot be empty.");
            return false;
        }

        List<FamilyMember> members = getAllMembers();

        // Generate a new member ID
        long newId = 1;

        for (FamilyMember existingMember : members) {
            if (existingMember.getMemberId() >= newId) {
                newId = existingMember.getMemberId() + 1;
            }
        }

        member.setMemberId(newId);

        String line = member.getMemberId() + "|"
                + safe(member.getFirstName()) + "|"
                + safe(member.getLastName()) + "|"
                + safe(member.getFullName()) + "|"
                + safe(member.getDateOfBirth());

        List<String> lines = fileService.readFile(MEMBER_FILE);
        lines.add(line);

        return fileService.writeFile(MEMBER_FILE, lines);
    }

    // Get all relationships
    public List<Relationship> getAllRelationships() {
        List<Relationship> relationships = new ArrayList<>();

        List<String> lines = fileService.readFile(RELATIONSHIP_FILE);

        for (String line : lines) {
            try {
                String[] parts = line.split("\\|", -1);

                if (parts.length != 4) {
                    continue;
                }

                long relationshipId = Long.parseLong(parts[0]);
                long memberId1 = Long.parseLong(parts[1]);
                long memberId2 = Long.parseLong(parts[2]);
                String relationshipType = parts[3];

                Relationship relationship = new Relationship(
                        relationshipId,
                        memberId1,
                        memberId2,
                        relationshipType
                );

                relationships.add(relationship);

            } catch (NumberFormatException e) {
                System.out.println("Invalid relationship data: " + line);
            }
        }

        return relationships;
    }

    // Add a relationship between two family members
    public boolean addRelationship(Relationship relationship) {
        if (relationship == null) {
            return false;
        }

        if (relationship.getMemberId1() == relationship.getMemberId2()) {
            System.out.println("A member cannot have a relationship with themselves.");
            return false;
        }

        // Check whether both members exist
        if (findMemberById(relationship.getMemberId1()) == null
                || findMemberById(relationship.getMemberId2()) == null) {
            System.out.println("One or both family members do not exist.");
            return false;
        }

        String type = relationship.getRelationshipType();

        if (type == null
                || !(type.equalsIgnoreCase("PARENT")
                || type.equalsIgnoreCase("CHILD")
                || type.equalsIgnoreCase("SPOUSE"))) {
            System.out.println("Relationship must be PARENT, CHILD, or SPOUSE.");
            return false;
        }

        List<Relationship> relationships = getAllRelationships();

        // Generate a new relationship ID
        long newId = 1;

        for (Relationship existing : relationships) {
            if (existing.getRelationshipId() >= newId) {
                newId = existing.getRelationshipId() + 1;
            }
        }

        relationship.setRelationshipId(newId);
        relationship.setRelationshipType(type.toUpperCase());

        String line = relationship.getRelationshipId() + "|"
                + relationship.getMemberId1() + "|"
                + relationship.getMemberId2() + "|"
                + relationship.getRelationshipType();

        List<String> lines = fileService.readFile(RELATIONSHIP_FILE);
        lines.add(line);

        return fileService.writeFile(RELATIONSHIP_FILE, lines);
    }

    // Prevent the "|" character from breaking the file format
    private String safe(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("|", "/");
    }
}
