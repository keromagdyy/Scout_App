package com.scoutapp.kmagdy;

public class MembersModel {
    String name,rank,teamName, stage, address, phoneNumber, father, hobby, church;

    public MembersModel() {
    }

    public MembersModel(String name, String rank, String teamName, String stage, String address, String phoneNumber, String father, String hobby, String church) {
        this.name = name;
        this.rank = rank;
        this.teamName = teamName;
        this.stage = stage;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.father = father;
        this.hobby = hobby;
        this.church = church;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getChurch() {
        return church;
    }

    public void setChurch(String church) {
        this.church = church;
    }
}
