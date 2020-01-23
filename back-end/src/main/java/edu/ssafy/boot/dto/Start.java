package edu.ssafy.boot.dto;

public class Start {
    private String _date;

    public Start(){
        
    }

    public Start(String _date) {
        this.set_date(_date);
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }
}