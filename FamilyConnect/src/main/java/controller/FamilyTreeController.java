/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author admin
 */
import java.util.List;
import model.FamilyMember;
import model.Relationship;
import service.FamilyTreeService;
public class FamilyTreeController {
    private final FamilyTreeService familyTreeService;
    public FamilyTreeController(){
        familyTreeService= new FamilyTreeService();
    }
    public List<FamilyMember> getAllMembers(){
        return familyTreeService.getAllMembers();
    }
    public FamilyMember  findMemberId(long memberId){
        return familyTreeService.findMemberById(memberId);
    }
    public boolean addMember(FamilyMember familyMember){
        return familyTreeService.addMember(familyMember);
    }
    public List<Relationship>  getARelationships(){
        return familyTreeService.getAllRelationships();
    }
    public boolean addRelationship(Relationship relationship){
        return familyTreeService.addRelationship(relationship);
    }
    
}
