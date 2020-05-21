package com.example.cook2;

public class Recipes {
    private Integer id;
    private Integer id_type;
    private String name;
    private String image;
    private String description;

    public Integer getId() { return id;}
    public void setId(Integer id) { this.id = id;}

    public Integer getId_type() { return id_type;}
    public void setId_type(Integer id_type) { this.id_type = id_type;}

    public String getName() { return name;}
    public void setName (String name) { this.name = name;}

    public String getImage() { return image;}
    public void setImage (String image) { this.image = image;}

    public String getDesciption() { return description;}
    public void setDesciption (String description) { this.description = description;}
}
