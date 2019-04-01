package com.example.workersside;

public class Meal {
    private String id;
        private  String main;
        private  String drink;
        private  String side;
        private String userName;


        public Meal(String id, String userName, String main, String drink,String side) {
            setId(id);
            this.userName = userName;
            this.main = main;
            this.drink = drink;
            this.side = side;


        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMain(String main){
            this.main =main;
        }
        public void setDrink(String drink){
            this.drink=drink;

        }
        public void setSide(String side){
            this.side = side;
        }

        public String getMain() {
            return main;
        }

        public String getDrink() {
            return drink;
        }
        public String getSide(){
            return side;
        }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
