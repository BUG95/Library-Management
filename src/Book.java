class Book {
    private int id;
    private String title;
    private String author;
    private int quantity;

    Book(int id, String title, String author, int quantity){
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }
    Book(String title, String author, int quantity){
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    Book(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
    }

    int getId(){return id;}

    String getTitle(){return title;}

    String getAuthor(){return author;}

    int getQuantity(){return quantity;}
}
