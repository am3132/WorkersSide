package com.example.workersside;

public class Meal {
        private  String main;
        private  String drink;
        private  String side;
       // private final int imageResource;
        private boolean isFavorite = false;
      //  private final String imageUrl;

        public Meal(String main, String drink,String side) {
            this.main = main;
            this.drink = drink;
            this.side = side;
           // this.imageResource =imageResource;
           // this.imageUrl =imageURL;
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

   /* public int getImageResource() {
        return imageResource;
    }
*/
    public boolean getIsFavorite() {
        return isFavorite;
    }
    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }
   /* public String getImageUrl(){
        return imageUrl;
    }*/
}
