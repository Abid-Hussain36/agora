package io.github.abid_hussain36.agora.utils;

public enum Interest {
    GAMING("Gaming"),
    MUSIC("Music"),
    COOKING("Cooking"),
    NETWORKING("Networking"),
    ENTREPRENEURSHIP("Entrepreneurship"),
    BOARD_GAMES("Board Games");

    private final String label;

    Interest(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }
}
