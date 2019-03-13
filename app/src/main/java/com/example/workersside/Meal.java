package com.example.workersside;

public class Meal {
        private final int main;
        private final int drink;
        private final int side;
      //  private final int imageResource;
       // private final String imageUrl;

        public Meal(int main, int drink,int side) {
            this.main = main;
            this.drink = drink;
            this.side = side;
           // this.imageResource = imageResource;
           // this.imageUrl = imageUrl;
        }

        public int getMain() {
            return main;
        }

        public int getDrink() {
            return drink;
        }
        public int getSide(){
            return side;
        }

       // public int getImageResource() {
          //  return imageResource;
       // }

       // public String getImageUrl() {
          //  return imageUrl;
       // }
    }
