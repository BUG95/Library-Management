import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

class LibraryADO {
    private Connection con;
    void connect() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String pass = "";
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
    }
    void close() throws SQLException {
        con.close();
    }

    ArrayList<Book> getBooks() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books" +
                       " JOIN book_available_quantities USING (book_id);";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            if(rs.wasNull()) break;
            books.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }
        rs.close();
        st.close();
        return books;
    }

    ArrayList<Book> getBook(int id) throws SQLException {
        ArrayList<Book> book = new ArrayList<>();
        String query = "SELECT * FROM books JOIN book_available_quantities USING (book_id) WHERE book_id = " + id + ";";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            if(rs.wasNull()) break;
            book.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }
        rs.close();
        st.close();
        return book;
    }

    ArrayList<Book> getBooksAfterTitle(String title) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books JOIN book_available_quantities USING (book_id) WHERE title LIKE '%" + title + "%';";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            if(rs.wasNull()) break;
            books.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }
        rs.close();
        st.close();
        return books;
    }

    ArrayList<Book> getBooksAfterAuthor(String author) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books JOIN book_available_quantities USING (book_id) WHERE author LIKE '%" + author + "%';";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            if(rs.wasNull()) break;
            books.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }
        rs.close();
        st.close();
        return books;
    }

    ArrayList<Book> getBooks(String title, String author) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books JOIN book_available_quantities USING (book_id) WHERE title LIKE '%" + title + "%' AND author LIKE '%"+ author +"%';";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            if(rs.wasNull()) break;
            books.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }
        rs.close();
        st.close();
        return books;
    }

    void addBook(Book book) throws SQLException {
        String query = "SELECT COUNT(*) AS rowCount FROM books WHERE title = \"" + book.getTitle() + "\";";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        if(rs.getInt("rowCount") > 0) throw new SQLException("Creating book failed, book already exists.");
        statement.close();

        String query1 = "INSERT INTO books (title, author) VALUES(?,?);";
        PreparedStatement st = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, book.getTitle());
        st.setString(2, book.getAuthor());
        int rowsAffected1 = st.executeUpdate();
        if(rowsAffected1 == 0) {
            throw new SQLException("Creating book failed, no rows affected.");
        }

        int book_id;
        ResultSet generatedKeys = st.getGeneratedKeys();
        if(generatedKeys.next()){
            book_id = generatedKeys.getInt(1);
        } else throw new SQLException("Creating book failed, no ID obtained.");
        generatedKeys.close();

        String query2 = "INSERT INTO book_available_quantities (book_id, quantity) VALUES (?,?);";
        st = con.prepareStatement(query2);
        st.setInt(1, book_id);
        st.setInt(2, book.getQuantity());
        int rowsAffected2 = st.executeUpdate();
        if(rowsAffected2 == 0) {
            throw new SQLException("Setting book quantity failed, no rows affected.");
        }
        st.close();
    }

    void deleteBook(int id) throws SQLException {
        Statement st = con.createStatement();
        String query1 = "DELETE FROM book_available_quantities WHERE book_id = " + id + ";";
        int rowsAffected1 = st.executeUpdate(query1);
        if(rowsAffected1 == 0){
            throw new SQLException("Deleting book failed, no rows affected.");
        }

        String query2 = "DELETE FROM books WHERE book_id = " + id + ";";
        int rowsAffected2 = st.executeUpdate(query2);
        if(rowsAffected2 == 0) {
            throw new SQLException("Deleting book failed, no rows affected.");
        }

        st.close();
    }

    void updateTitleBook(int id, String title) throws SQLException {
        String query = "UPDATE books SET title = \"" + title + "\" WHERE book_id = " + id + ";";
        Statement st = con.createStatement();
        int rowsAffected = st.executeUpdate(query);
        st.close();
        if(rowsAffected == 0) throw new SQLException("This book id doesn't exist");
    }

    void updateAuthorBook(int id, String author) throws SQLException {
        String query = "UPDATE books SET author = \"" + author + "\" WHERE book_id = " + id + ";";
        Statement st = con.createStatement();
        int rowsAffected = st.executeUpdate(query);
        st.close();
        if(rowsAffected == 0) throw new SQLException("This book id doesn't exist");
    }

    void updateQuantityBook(int id, int quantity) throws SQLException {
        String query = "UPDATE book_available_quantities SET quantity = " + quantity + " WHERE book_id = " + id + ";";
        Statement st = con.createStatement();
        int rowsAffected = st.executeUpdate(query);
        st.close();
        if(rowsAffected == 0) throw new SQLException("This book id doesn't exist");
    }


    ArrayList<Student> getAllStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students;";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            if(rs.wasNull()) break;
            students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        st.close();
        return students;
    }

    ArrayList<Student> getStudent(int id) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE student_id = " + id + ";";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()) {
            if (rs.wasNull()) break;
            students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        st.close();
        return students;
    }

    ArrayList<Student> getStudents(String name, String college) throws SQLException{
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE name LIKE '%" + name +"%' AND college LIKE '%" + college + "%';";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            if(rs.wasNull()) break;
            students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        st.close();
        return students;
    }

    ArrayList<Student> getStudentsAfterName(String name) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE name LIKE '%" + name + "%';";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            if(rs.wasNull()) break;
            students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        st.close();
        return students;
    }

    ArrayList<Student> getStudentsAfterCollege(String college) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE college LIKE '%" + college + "%';";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            if(rs.wasNull()) break;
            students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        st.close();
        return students;
    }

    void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (name, college) VALUES(?,?);";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, student.getName());
        pst.setString(2, student.getCollege());
        int rowsAffected = pst.executeUpdate();
        pst.close();
        if(rowsAffected == 0){
            throw new SQLException("Creating student failed, no rows affected");
        }
    }

    void deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM students WHERE student_id = " + id + ";";
        Statement st = con.createStatement();
        int rowAffected = st.executeUpdate(query);
        st.close();
        if(rowAffected == 0){
            throw new SQLException("Deleting student failed, no rows affected");
        }
    }

    void updateStudentName(int id, String name) throws SQLException {
        String query = "UPDATE students SET name = \"" + name + "\" WHERE student_id = " + id + ";";
        Statement st = con.createStatement();
        int rowsAffected = st.executeUpdate(query);
        st.close();
        if(rowsAffected == 0){
            throw new SQLException("Updating student name failed, no rows affected");
        }
    }

    void updateStudentCollege(int id, String college) throws SQLException {
        String query = "UPDATE students SET college = \"" + college + "\" WHERE student_id = " + id + ";";
        Statement st = con.createStatement();
        int rowsAffected = st.executeUpdate(query);
        st.close();
        if(rowsAffected == 0){
            throw new SQLException("Updating student college failed, no rows affected");
        }
    }

    private boolean studentHasBorrowedBook(int student_id, int book_id) throws SQLException {
        String query = "SELECT COUNT(*) FROM student_books WHERE student_id = " + student_id + " AND book_id = " + book_id + ";";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        return count > 0;
    }

    private int getBookQuantity(int id) throws SQLException {
        String query = "SELECT quantity FROM books JOIN book_available_quantities USING (book_id) WHERE book_id = " + id + ";";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        if(!rs.next()){
            throw new SQLException("This book doesn't exist in the database");
        }
        int quantity = rs.getInt("quantity");
        rs.next();
        rs.close();
        st.close();
        return quantity;
    }

    void borrowBook(int book_id, int student_id) throws SQLException {
        String query = "SELECT * FROM books WHERE book_id = " + book_id + ";";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        if(!rs.next()){
            throw new SQLException("Borrow book failed, this book doesn't exist in the database");
        }

        query = "SELECT * FROM students WHERE student_id = " + student_id + ";";
        rs = st.executeQuery(query);
        if(!rs.next()){
            throw new SQLException("Borrow book failed, this student doesn't exist in the database");
        }
        rs.close();

        if(studentHasBorrowedBook(student_id, book_id)){
            throw new SQLException("This student has already borrowed this book\n You can not borrow this book again");
        }

        int book_available_quantity = getBookQuantity(book_id);
        if(book_available_quantity <= 0){
            throw new SQLException("This book is not available");
        }

        query = "INSERT INTO student_books VALUES (" + student_id + ", " + book_id  + ");";
        st = con.createStatement();
        int rowsAffected = st.executeUpdate(query);
        st.close();
        if(rowsAffected == 0){
            throw new SQLException("Borrow book failed, no rows affected");
        }

        updateQuantityBook(book_id, book_available_quantity - 1);
    }

    void returnBook(int student_id, int book_id) throws SQLException {
        String query = "SELECT * FROM students WHERE student_id = " + student_id + ";";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        if(!rs.next()){
            throw new SQLException("Return book failed, this student doesn't exist in the database");
        }

        query = "SELECT * FROM books WHERE book_id = " + book_id + ";";
        rs = st.executeQuery(query);
        if(!rs.next()){
            throw new SQLException("Return book failed, this book doesn't exist in the database");
        }
        rs.close();

        query = "DELETE FROM student_books WHERE student_id = " + student_id + " AND book_id = " + book_id + ";";
        int rowsAffected = st.executeUpdate(query);
        if(rowsAffected == 0){
            throw new SQLException("Returning book failed, this student has not borrowed this book");
        }

        int book_quantity = getBookQuantity(book_id);
        updateQuantityBook(book_id, book_quantity + 1);
    }

    Hashtable<Student, Book> getBorrowedBooks() throws SQLException {
        Hashtable<Student, Book> student_books = new Hashtable<>();
        String query = "SELECT * FROM student_books JOIN students USING (student_id) JOIN books USING (book_id);";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            Student student = new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("college"));
            Book book = new Book(rs.getInt("book_id"), rs.getString("title"), rs.getString("author"));
            student_books.put(student, book);
        }

        if(student_books.size() == 0){
            throw new SQLException("No books were borrowed");
        }

        return student_books;
    }

}
