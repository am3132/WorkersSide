package com.example.workersside;

public class Meal {
        private  String main;
        private  String drink;
        private  String side;
        private String CustomerUniId;
        private boolean readyForCollection;


        public Meal(String CustomerUniId, String main, String drink,String side) {
            this.CustomerUniId = CustomerUniId;
            //setUserId(userID);
            this.main = main;
            this.drink = drink;
            this.side = side;


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

    public String getCustomerUniId() {
        return CustomerUniId;
    }

    public void setCustomerUniId(String CustomerUniId) {
        this.CustomerUniId = CustomerUniId;
    }
}
