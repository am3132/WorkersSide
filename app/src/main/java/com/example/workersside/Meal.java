package com.example.workersside;

public class Meal {
        private  String main;
        private  String drink;
        private  String side;
        private String userId;


        public Meal(String main, String drink,String side,String userID) {
            this.main = main;
            this.drink = drink;
            this.side = side;
            setUserId(userID);

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
