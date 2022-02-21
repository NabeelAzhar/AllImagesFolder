package com.naturewallpaper.allimagesfolder.activites;

public class ModelImage {

    String imagePath, folderName,fstPicture;
    int noPicturs;
    public  ModelImage(){

    }

    public ModelImage(String imagePath, String folderName) {
        this.imagePath = imagePath;
        this.folderName = folderName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFstPicture() {
        return fstPicture;
    }

    public void setFstPicture(String fstPicture) {
        this.fstPicture = fstPicture;
    }

    public int getNoPicturs() {
        return noPicturs;
    }

    public void setNoPicturs(int noPicturs) {
        this.noPicturs = noPicturs;
    }
    public void addpics(){
        this.noPicturs++;
    }
}
