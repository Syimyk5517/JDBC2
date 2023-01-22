package org.example.model;

public class Job {
    private Long id;
    private String position;
    private String profession;
    private String description;
    private int experince;

    public Job(Long id, String position, String profession, String description, int experince) {
        this.id = id;
        this.position = position;
        this.profession = profession;
        this.description = description;
        this.experince = experince;
    }
    public Job(){

    }


    public Job(String position, String profession, String description, int experince) {
        this.position = position;
        this.profession = profession;
        this.description = description;
        this.experince = experince;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExperince() {
        return experince;
    }

    public void setExperince(int experince) {
        this.experince = experince;
    }

    @Override
    public String toString() {
        return """
                ~~~~~~~~~~~    Job    ~~~~~~~~~~
                """+  "\nid: " + id +
                "\nposition: " + position +
                "\nprofession:" + profession +
                "\ndescription: " + description +
                "\nexperince: " + experince ;

    }
}
