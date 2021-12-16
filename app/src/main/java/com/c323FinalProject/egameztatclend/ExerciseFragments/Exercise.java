package com.c323FinalProject.egameztatclend.ExerciseFragments;

public class Exercise {
    private String _name;
    private String _bitmap;

    public Exercise() {
    }

    public Exercise(String _name, String _bitmap) {
        this._name = _name;
        this._bitmap = _bitmap;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_bitmap() {
        return _bitmap;
    }

    public void set_bitmap(String _bitmap) {
        this._bitmap = _bitmap;
    }
}
