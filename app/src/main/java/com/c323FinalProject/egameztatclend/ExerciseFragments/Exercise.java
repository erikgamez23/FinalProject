package com.c323FinalProject.egameztatclend.ExerciseFragments;

public class Exercise {
    private String _name;
    private String _imageLink;

    public Exercise() {
    }

    public Exercise(String _name, String _imageLink) {
        this._name = _name;
        this._imageLink = _imageLink;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_imageLink() {
        return _imageLink;
    }

    public void set_imageLink(String _imageLink) {
        this._imageLink = _imageLink;
    }
}
