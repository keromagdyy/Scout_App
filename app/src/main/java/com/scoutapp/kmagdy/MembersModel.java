package com.scoutapp.kmagdy;

public class MembersModel {
    String name,rank,teamName;

    public MembersModel() {

    }

    public MembersModel(String name, String rank, String teamName) {
        this.name = name;
        this.rank = rank;
        this.teamName = teamName;
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
}
