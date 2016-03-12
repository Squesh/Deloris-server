package server.core;

public class Hero {
    private String token;
    private String name;
    private int x;
    private int y;

    public Hero(String token, String name, int x, int y) {
        this.token = token;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
