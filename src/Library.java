import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Library extends JFrame implements ActionListener {
     private JTextField bookTitleText, bookAuthorText, bookIdText, bookQuantityText;
     private JTextField studentIdText, studentNameText, studentCollegeText;
     private JTextField borrowBookIdText, borrowStudentIdText;
     private JTextField returnStudentIdText, returnBookIdText;
     private JTable bookTable, studentTable;
     private JPanel searchBookPanel;
     private JPanel searchStudentPanel;

    private Library(String title){
         super(title);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(700, 623);
         setLayout(null);
         setResizable(false);
         addSearchBookArea();
         addSearchStudentArea();
         addBookBorrowPanel();
         addBookReturnPanel();
         setVisible(true);
     }

     private void addBookReturnPanel(){
         JLabel panelTitle, studentIdLabel, bookIdLabel;
         JButton returnBookButton;

         JPanel bookReturnPanel = new JPanel();
         bookReturnPanel.setLayout(null);

         Font panelTitleFont = new Font("Serif", Font.PLAIN, 25);
         FontMetrics fm = getFontMetrics(panelTitleFont);

         bookReturnPanel.setBounds(350, 396, 350, 198);
         bookReturnPanel.setBackground(new Color(255, 153, 102));

         panelTitle = new JLabel("Return a book");
         panelTitle.setFont(panelTitleFont);
         panelTitle.setBounds((bookReturnPanel.getWidth() - fm.stringWidth(panelTitle.getText())) / 2, 0, fm.stringWidth(panelTitle.getText()), fm.getHeight());
         bookReturnPanel.add(panelTitle);

         Font font = new Font("Serif", Font.PLAIN, 17);
         bookReturnPanel.setFont(font);
         fm = getFontMetrics(font);

         studentIdLabel = new JLabel("Student Id");
         bookIdLabel = new JLabel("Book Id");
         studentIdLabel.setBounds(10, 60, fm.stringWidth(studentIdLabel.getText()), fm.getHeight());
         bookIdLabel.setBounds(10, 60 + fm.getHeight(), fm.stringWidth(bookIdLabel.getText()), fm.getHeight());
         bookReturnPanel.add(studentIdLabel);
         bookReturnPanel.add(bookIdLabel);

         returnStudentIdText = new JTextField();
         returnBookIdText = new JTextField();
         returnStudentIdText.setBounds(10 + fm.stringWidth(studentIdLabel.getText()),60,100, fm.getHeight());
         returnBookIdText.setBounds(10 + fm.stringWidth(studentIdLabel.getText()), 60 + fm.getHeight(), 100, fm.getHeight());
         bookReturnPanel.add(returnStudentIdText);
         bookReturnPanel.add(returnBookIdText);

         returnBookButton = new JButton("Return");
         returnBookButton.setFocusPainted(false);
         returnBookButton.addActionListener(this);
         returnBookButton.setActionCommand("Return book");
         returnBookButton.setBounds(10 + fm.stringWidth(studentIdLabel.getText()) + 100, 60, 80, 2 * fm.getHeight());
         bookReturnPanel.add(returnBookButton);

         add(bookReturnPanel);
     }

     private void addBookBorrowPanel(){
         JLabel panelTitle, bookIdLabel, studentIdLabel;
         JButton borrowBookButton, seeBorrowedBooks;

         JPanel bookBorrowPanel = new JPanel();
         bookBorrowPanel.setLayout(null);

         Font panelTitleFont = new Font("Serif", Font.PLAIN, 25);
         FontMetrics fm = getFontMetrics(panelTitleFont);

         bookBorrowPanel.setBounds(0, 396, 350, 198);
         bookBorrowPanel.setBackground(new Color(153, 255, 102));

         panelTitle = new JLabel("Borrow a book");
         panelTitle.setFont(panelTitleFont);
         panelTitle.setBounds((bookBorrowPanel.getWidth() - fm.stringWidth(panelTitle.getText())) / 2, 0, fm.stringWidth(panelTitle.getText()), fm.getHeight());
         bookBorrowPanel.add(panelTitle);

         Font font = new Font("Serif", Font.PLAIN, 17);
         bookBorrowPanel.setFont(font);
         fm = getFontMetrics(font);

         bookIdLabel = new JLabel("Book Id");
         studentIdLabel = new JLabel("Student Id");
         bookIdLabel.setBounds(10, 60, fm.stringWidth(studentIdLabel.getText()), fm.getHeight());
         studentIdLabel.setBounds(10, 60 + fm.getHeight(), fm.stringWidth(studentIdLabel.getText()), fm.getHeight());
         bookBorrowPanel.add(bookIdLabel);
         bookBorrowPanel.add(studentIdLabel);

         borrowBookIdText = new JTextField();
         borrowStudentIdText = new JTextField();
         borrowBookIdText.setBounds(10 + fm.stringWidth(studentIdLabel.getText()), 60, 100, fm.getHeight());
         borrowStudentIdText.setBounds(10 + fm.stringWidth(studentIdLabel.getText()), 60 + fm.getHeight(), 100, fm.getHeight());
         bookBorrowPanel.add(borrowBookIdText);
         bookBorrowPanel.add(borrowStudentIdText);

         borrowBookButton = new JButton("Borrow");
         borrowBookButton.setFocusPainted(false);
         borrowBookButton.addActionListener(this);
         borrowBookButton.setActionCommand("Borrow book");
         borrowBookButton.setBounds(10 + fm.stringWidth(studentIdLabel.getText()) + 100, 60, 80, 2 * fm.getHeight());
         bookBorrowPanel.add(borrowBookButton);

         seeBorrowedBooks = new JButton("See borrowed books");
         seeBorrowedBooks.setFocusPainted(false);
         seeBorrowedBooks.addActionListener(this);
         seeBorrowedBooks.setBounds(10 + fm.stringWidth(studentIdLabel.getText()), 60 + 2 * fm.getHeight(), 180, fm.getHeight());
         bookBorrowPanel.add(seeBorrowedBooks);

         add(bookBorrowPanel);
     }

     private void addSearchStudentArea(){
         JLabel panelTitle, studentIdLabel, studentNameLabel, studentCollegeLabel;
         JButton searchStudentButton, showAllStudentsButton, editStudentButton, deleteStudentButton, addStudentButton;

         searchStudentPanel = new JPanel();
         searchStudentPanel.setLayout(null);

         Font panelTitleFont = new Font("Serif", Font.PLAIN, 25);
         FontMetrics fm = getFontMetrics(panelTitleFont);

         searchStudentPanel.setBounds(0, 198, 700, 198);
         searchStudentPanel.setBackground(new Color(102, 102, 255));

         panelTitle = new JLabel("Search for a student");
         panelTitle.setFont(panelTitleFont);
         panelTitle.setBounds((getWidth() - fm.stringWidth(panelTitle.getText())) / 2, 0, fm.stringWidth(panelTitle.getText()), fm.getHeight());
         searchStudentPanel.add(panelTitle);

         Font font = new Font("Serif", Font.PLAIN, 17);
         searchStudentPanel.setFont(font);
         fm = getFontMetrics(font);

         studentIdLabel = new JLabel("Id");
         studentNameLabel = new JLabel("Name");
         studentCollegeLabel = new JLabel("College");
         studentIdLabel.setBounds(10, 60, fm.stringWidth(studentIdLabel.getText()), fm.getHeight());
         studentNameLabel.setBounds(10, 60 + fm.getHeight(), fm.stringWidth(studentNameLabel.getText()), fm.getHeight());
         studentCollegeLabel.setBounds(10, 60 + 2 * fm.getHeight(), fm.stringWidth(studentCollegeLabel.getText()), fm.getHeight());
         searchStudentPanel.add(studentIdLabel);
         searchStudentPanel.add(studentNameLabel);
         searchStudentPanel.add(studentCollegeLabel);

         studentIdText = new JTextField();
         studentNameText = new JTextField();
         studentCollegeText = new JTextField();
         studentIdText.setBounds(10 + fm.stringWidth(studentCollegeLabel.getText()), 60,150, fm.getHeight());
         studentNameText.setBounds(10 + fm.stringWidth(studentCollegeLabel.getText()), 60 + fm.getHeight(), 150, fm.getHeight());
         studentCollegeText.setBounds(10 + fm.stringWidth(studentCollegeLabel.getText()), 60 + 2 * fm.getHeight(), 150, fm.getHeight());
         searchStudentPanel.add(studentIdText);
         searchStudentPanel.add(studentNameText);
         searchStudentPanel.add(studentCollegeText);

         searchStudentButton = new JButton("Search stud");
         showAllStudentsButton = new JButton("Show all stud");
         searchStudentButton.setFocusPainted(false);
         showAllStudentsButton.setFocusPainted(false);
         searchStudentButton.setBounds(10, 60 + 3 * fm.getHeight(), fm.stringWidth(showAllStudentsButton.getText()) + 20, fm.getHeight() + (fm.getHeight()) / 2);
         searchStudentPanel.add(searchStudentButton);

         searchStudentButton.setBackground(new Color(105, 255, 255));
         searchStudentButton.setForeground(Color.BLACK);
         showAllStudentsButton.setBackground(new Color(105, 255, 255));
         showAllStudentsButton.setForeground(Color.BLACK);
         searchStudentButton.addActionListener(this);
         showAllStudentsButton.addActionListener(this);
         searchStudentButton.setActionCommand("Search student");
         showAllStudentsButton.setActionCommand("Show all students");


         showAllStudentsButton.setBounds(10, 60 + 4 * fm.getHeight() + (fm.getHeight()) / 2, fm.stringWidth(showAllStudentsButton.getText()) + 20, fm.getHeight() + (fm.getHeight()) / 2);
         searchStudentPanel.add(showAllStudentsButton);

         editStudentButton = new JButton("Edit");
         deleteStudentButton = new JButton("Delete");
         addStudentButton = new JButton("Add");
         editStudentButton.setFocusPainted(false);
         deleteStudentButton.setFocusPainted(false);
         addStudentButton.setFocusPainted(false);
         editStudentButton.addActionListener(this);
         deleteStudentButton.addActionListener(this);
         addStudentButton.addActionListener(this);
         editStudentButton.setActionCommand("Edit student");
         deleteStudentButton.setActionCommand("Delete student");
         addStudentButton.setActionCommand("Add student");
         editStudentButton.setBackground(new Color(51, 204, 51));
         deleteStudentButton.setBackground(new Color(255, 0, 102));
         editStudentButton.setForeground(Color.BLACK);
         deleteStudentButton.setForeground(Color.WHITE);
         addStudentButton.setBackground(new Color(51, 204, 51));
         addStudentButton.setForeground(Color.BLACK);
         addStudentButton.setBounds(10 + fm.stringWidth(showAllStudentsButton.getText()) + 20, 60 + 3 * fm.getHeight(), fm.stringWidth(deleteStudentButton.getText()) + 47, fm.getHeight());
         deleteStudentButton.setBounds(10 + fm.stringWidth(showAllStudentsButton.getText()) + 20, 60 + 4 * fm.getHeight(), fm.stringWidth(deleteStudentButton.getText()) + 47, fm.getHeight());
         editStudentButton.setBounds(10 + fm.stringWidth(showAllStudentsButton.getText()) + 20, 60 + 5 * fm.getHeight(), fm.stringWidth(deleteStudentButton.getText()) + 47, fm.getHeight());
         searchStudentPanel.add(editStudentButton);
         searchStudentPanel.add(deleteStudentButton);
         searchStudentPanel.add(addStudentButton);

         addStudentsTable();

         add(searchStudentPanel);
     }

     private void addSearchBookArea(){
         JLabel bookTitleLabel, bookAuthorLabel, bookIdLabel, panelTitle, bookQuantityLabel;
         JButton searchBookButton, showAllBooksButton, editBookButton, deleteBookButton, addBookButton;
         Font panelTitleFont = new Font("Serif", Font.PLAIN, 25);
         FontMetrics fm = getFontMetrics(panelTitleFont);

         searchBookPanel = new JPanel();
         searchBookPanel.setLayout(null);
         searchBookPanel.setBounds(0,0, 700,198);
         searchBookPanel.setBackground(new Color(204, 102, 153));

         panelTitle = new JLabel("Search for a book");
         bookIdLabel = new JLabel("Id");
         bookTitleLabel = new JLabel("Title");
         bookAuthorLabel = new JLabel("Author");
         bookQuantityLabel = new JLabel("Total");
         bookIdText = new JTextField();
         bookTitleText = new JTextField();
         bookAuthorText = new JTextField();
         bookQuantityText = new JTextField();
         searchBookButton = new JButton("Search book");
         showAllBooksButton = new JButton("Show all books");
         editBookButton = new JButton("Edit");
         deleteBookButton = new JButton("Delete");
         addBookButton = new JButton("Add");
         searchBookButton.setBackground(new Color(105, 255, 255));
         searchBookButton.setForeground(Color.BLACK);
         showAllBooksButton.setBackground(new Color(105, 255, 255));
         showAllBooksButton.setForeground(Color.BLACK);
         editBookButton.setBackground(new Color(51, 204, 51));
         editBookButton.setForeground(Color.BLACK);
         deleteBookButton.setBackground(new Color(255, 0, 102));
         deleteBookButton.setForeground(Color.WHITE);
         addBookButton.setBackground(new Color(51, 204, 51));
         addBookButton.setForeground(Color.BLACK);
         searchBookButton.addActionListener(this);
         showAllBooksButton.addActionListener(this);
         editBookButton.addActionListener(this);
         deleteBookButton.addActionListener(this);
         addBookButton.addActionListener(this);
         searchBookButton.setFocusPainted(false);
         showAllBooksButton.setFocusPainted(false);
         editBookButton.setFocusPainted(false);
         deleteBookButton.setFocusPainted(false);
         addBookButton.setFocusPainted(false);
         editBookButton.setActionCommand("Edit book");
         deleteBookButton.setActionCommand("Delete book");
         addBookButton.setActionCommand("Add book");
         panelTitle.setFont(panelTitleFont);

         panelTitle.setBounds((getWidth() - fm.stringWidth(panelTitle.getText())) / 2, 0, fm.stringWidth(panelTitle.getText()), fm.getHeight());
         searchBookPanel.add(panelTitle);

         Font font = new Font("Serif", Font.PLAIN, 17);
         searchBookPanel.setFont(font);
         fm = getFontMetrics(font);

         bookIdLabel.setBounds(10, 60 , fm.stringWidth(bookIdLabel.getText()), fm.getHeight());
         bookIdText.setBounds(10 + fm.stringWidth(bookAuthorLabel.getText()), 60, 150, fm.getHeight());
         searchBookPanel.add(bookIdLabel);
         searchBookPanel.add(bookIdText);

         bookTitleLabel.setBounds(10, 60 + fm.getHeight(), fm.stringWidth(bookTitleLabel.getText()), fm.getHeight());
         bookTitleText.setBounds(10 + fm.stringWidth(bookAuthorLabel.getText()), 60 + fm.getHeight(), 150, fm.getHeight());
         searchBookPanel.add(bookTitleLabel);
         searchBookPanel.add(bookTitleText);

         bookAuthorLabel.setBounds(10, 60 + 2 * fm.getHeight(), fm.stringWidth(bookAuthorLabel.getText()), fm.getHeight());
         bookAuthorText.setBounds(10 + fm.stringWidth(bookAuthorLabel.getText()), 60 + 2 * fm.getHeight(),150, fm.getHeight());
         searchBookPanel.add(bookAuthorLabel);
         searchBookPanel.add(bookAuthorText);

         bookQuantityLabel.setBounds(10, 60 + 3 * fm.getHeight(), fm.stringWidth(bookQuantityLabel.getText()), fm.getHeight());
         searchBookPanel.add(bookQuantityLabel);

         bookQuantityText.setBounds(10 + fm.stringWidth(bookAuthorLabel.getText()), 60 + 3 * fm.getHeight(), 76, fm.getHeight());
         searchBookPanel.add(bookQuantityText);

         searchBookButton.setBounds(10, 60 + 4 * fm.getHeight(), fm.stringWidth(showAllBooksButton.getText()) + 20, fm.getHeight());
         searchBookPanel.add(searchBookButton);

         showAllBooksButton.setBounds(10, 60 + 5 * fm.getHeight(), fm.stringWidth(showAllBooksButton.getText()) + 20, fm.getHeight());
         searchBookPanel.add(showAllBooksButton);

         addBookButton.setBounds(10 + fm.stringWidth(showAllBooksButton.getText()) + 20, 60 + 3 * fm.getHeight(), fm.stringWidth(deleteBookButton.getText()) + 27, fm.getHeight());
         deleteBookButton.setBounds(10 + fm.stringWidth(showAllBooksButton.getText()) + 20, 60 + 4 * fm.getHeight(), fm.stringWidth(deleteBookButton.getText()) + 27, fm.getHeight());
         editBookButton.setBounds(10 + fm.stringWidth(showAllBooksButton.getText()) + 20, 60 + 5 * fm.getHeight(),fm.stringWidth(deleteBookButton.getText()) + 27, fm.getHeight());
         searchBookPanel.add(editBookButton);
         searchBookPanel.add(deleteBookButton);
         searchBookPanel.add(addBookButton);

         addBooksTable();

         add(searchBookPanel);
     }

     private void addBooksTable(){
         String[] columns = {"Id", "Title", "Author", "Available"};
         DefaultTableModel model = new DefaultTableModel(0, 4);
         model.setColumnIdentifiers(columns);
         bookTable = new JTable(model);
         bookTable.setDefaultEditor(Object.class, null);
         bookTable.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent mouseEvent) {
                 super.mouseClicked(mouseEvent);
                 bookIdText.setText(bookTable.getValueAt(bookTable.getSelectedRow(), 0).toString());
                 bookTitleText.setText(bookTable.getValueAt(bookTable.getSelectedRow(), 1).toString());
                 bookAuthorText.setText(bookTable.getValueAt(bookTable.getSelectedRow(), 2).toString());
                 bookQuantityText.setText(bookTable.getValueAt(bookTable.getSelectedRow(), 3).toString());
             }
         });
         JScrollPane pane = new JScrollPane(bookTable);
         pane.setBounds(250, 50, 430, 135);
         searchBookPanel.add(pane);
     }

     private void addStudentsTable(){
         String[] columns = {"Id", "Name", "College"};
         DefaultTableModel model = new DefaultTableModel(0, 3);
         model.setColumnIdentifiers(columns);
         studentTable = new JTable(model);
         studentTable.setDefaultEditor(Object.class, null);
         studentTable.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent mouseEvent) {
                 super.mouseClicked(mouseEvent);
                 studentIdText.setText(studentTable.getValueAt(studentTable.getSelectedRow(), 0).toString());
                 studentNameText.setText(studentTable.getValueAt(studentTable.getSelectedRow(), 1).toString());
                 studentCollegeText.setText(studentTable.getValueAt(studentTable.getSelectedRow(), 2).toString());
             }
         });
         JScrollPane pane = new JScrollPane((studentTable));
         pane.setBounds(250, 50, 430, 135);
         searchStudentPanel.add(pane);
     }

     private void showBookSearchResult() throws SQLException, ClassNotFoundException {
         boolean id = false, title = false, author = false;
         if(bookIdText.getText().length() > 0){
             if(bookIdText.getText().matches("[0-9]+")) {id = true;}
             else {showMessage("Only number format is accepted for Id field"); return;}
         }
         if(bookTitleText.getText().length() > 0) {title = true;}
         if(bookAuthorText.getText().length() > 0) {author = true;}
         if(!id && !title && !author) {showMessage("At least one field must be filled (except \"Total\" field)");}
         else {
             ArrayList<Book> books;
             LibraryADO ado = new LibraryADO();
             ado.connect();
             if(id){
                 books = ado.getBook(Integer.parseInt(bookIdText.getText()));
             }
             else if(title && author){
                 books = ado.getBooks(bookTitleText.getText(), bookAuthorText.getText());
             }
             else {
                 if(title) {books = ado.getBooksAfterTitle(bookTitleText.getText());}
                 else { books = ado.getBooksAfterAuthor(bookAuthorText.getText());}
             }
             ado.close();
             DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
             for(Book b : books)
                model.addRow(new Object[]{b.getId(), b.getTitle(), b.getAuthor(), b.getQuantity()});
             if(books.size() == 0) showMessage("No results");
         }
     }

     private void clearBooksTable(){
         DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
         model.setRowCount(0);
     }

     private void clearStudentsTable(){
         DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
         model.setRowCount(0);
     }

     private void showMessage(String m){
       JOptionPane.showMessageDialog(this, m);
    }

     private void showAllBooks() throws SQLException, ClassNotFoundException {
         LibraryADO ado = new LibraryADO();
         ado.connect();
         ArrayList<Book> books = ado.getBooks();
         ado.close();
         DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
         for(Book b : books)
             model.addRow(new Object[]{b.getId(), b.getTitle(), b.getAuthor(), b.getQuantity()});
        if(books.size() == 0) showMessage("Database is empty");
     }

     private void showAllStudents() throws SQLException, ClassNotFoundException {
         LibraryADO ado = new LibraryADO();
         ado.connect();
         ArrayList<Student> students = ado.getAllStudents();
         ado.close();
         DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
         for(Student s : students)
             model.addRow(new Object[]{s.getId(), s.getName(), s.getCollege()});
         if(students.size() == 0) showMessage("Database is empty");
     }

     private void showStudentSearchResult() throws SQLException, ClassNotFoundException {
         boolean id = false, name = false, college = false;
         if(studentIdText.getText().length() > 0){
             if(studentIdText.getText().matches("[0-9]+")) {id = true;}
             else {showMessage("Only number format is accepted for Id field"); return;}
         }
         if(studentNameText.getText().length() > 0) {name = true;}
         if(studentCollegeText.getText().length() > 0) {college = true;}
         if(!id && !name && !college) {showMessage("At least one field must be filled");}
         else {
             ArrayList<Student> students;
             LibraryADO ado = new LibraryADO();
             ado.connect();
             if(id){
                 students = ado.getStudent(Integer.parseInt(studentIdText.getText()));
             }
             else if(name && college){
                 students = ado.getStudents(studentNameText.getText(), studentCollegeText.getText());
             }
             else {
                 if(name) {students = ado.getStudentsAfterName(studentNameText.getText());}
                 else {students = ado.getStudentsAfterCollege(studentCollegeText.getText());}
             }
             ado.close();
             DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
             for(Student s : students)
                 model.addRow(new Object[]{s.getId(), s.getName(), s.getCollege()});
             if(students.size() == 0) showMessage("No results");
         }
     }

     private void deleteBook() throws SQLException, ClassNotFoundException {
         if(bookIdText.getText().length() > 0){
             if(bookIdText.getText().matches("[0-9]+")){
                 int answer = JOptionPane.showConfirmDialog(this, "Are you sure?","Warning", JOptionPane.YES_NO_OPTION);
                 if(answer == JOptionPane.YES_OPTION) {
                     LibraryADO ado = new LibraryADO();
                     ado.connect();
                     ado.deleteBook(Integer.parseInt(bookIdText.getText()));
                     ado.close();
                     showMessage("Book has been deleted");
                     clearBooksTable();
                     showAllBooks();
                 }
             } else showMessage("Only number format is accepted for Id field");
         } else showMessage("Id field must be filled for this operation");
     }

     private void addBook() throws SQLException, ClassNotFoundException {
         boolean title = false, author = false, quantity = false;
         if(bookTitleText.getText().length() > 0) {title = true;}
         if(bookAuthorText.getText().length() > 0) {author = true;}
         if(bookQuantityText.getText().length() > 0) {quantity = true;}
         if(!title && !author && !quantity) {showMessage("All fields except Id must be filled"); return;}
         LibraryADO ado = new LibraryADO();
         ado.connect();
         ado.addBook(new Book(bookTitleText.getText(), bookAuthorText.getText(), Integer.parseInt(bookQuantityText.getText())));
         ado.close();
         showMessage("Book has been added");
         clearBooksTable();
         showAllBooks();
     }

     private void editBook() throws SQLException, ClassNotFoundException {
         boolean title = false, author = false, quantity = false;
         if(bookIdText.getText().length() > 0) {
             if (bookIdText.getText().matches("[0-9]+")) {
                 LibraryADO ado = new LibraryADO();
                 ado.connect();
                 if(bookTitleText.getText().length() > 0){
                     ado.updateTitleBook(Integer.parseInt(bookIdText.getText()), bookTitleText.getText());
                     title = true;
                 }
                 if(bookAuthorText.getText().length() > 0){
                     ado.updateAuthorBook(Integer.parseInt(bookIdText.getText()), bookAuthorText.getText());
                     author = true;
                 }
                 if(bookQuantityText.getText().length() > 0){
                     if(bookQuantityText.getText().matches("[0-9]+")){
                         ado.updateQuantityBook(Integer.parseInt(bookIdText.getText()), Integer.parseInt(bookQuantityText.getText()));
                         quantity = true;
                     } else showMessage("Only number format is accepted for Total field");
                 }
                 ado.close();
                 if(title || author || quantity){
                     showMessage("Next fields has been edited successfully: " + (title ? "title" : "") + (author ? (title ? ", author" : "author") : ("")) + (quantity ? (title || author ? ", quantity" : "quantity") : ""));
                     clearBooksTable();
                     showAllBooks();
                 } else showMessage("There is nothing to edit");
             } else showMessage("Only number format is accepted for Id field");
         } else showMessage("Id field must be filled for this operation");
     }

     private void addStudent() throws SQLException, ClassNotFoundException {
         if(studentNameText.getText().length() > 0){
             if(studentCollegeText.getText().length() > 0){
                 LibraryADO ado = new LibraryADO();
                 ado.connect();
                 ado.addStudent(new Student(studentNameText.getText(), studentCollegeText.getText()));
                 ado.close();
                 showMessage("Student has been added");
                 clearStudentsTable();
                 showAllStudents();
             } else {showMessage("College field must be filled");}
         } else {showMessage("Name field must be filled");}
     }

     private void deleteStudent() throws SQLException, ClassNotFoundException {
         if(studentIdText.getText().length() > 0){
             if(studentIdText.getText().matches("[0-9]+")){
                 int answer = JOptionPane.showConfirmDialog(this, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
                 if(answer == JOptionPane.YES_OPTION){
                     LibraryADO ado = new LibraryADO();
                     ado.connect();
                     ado.deleteStudent(Integer.parseInt(studentIdText.getText()));
                     ado.close();
                     clearStudentsTable();
                     showAllStudents();
                     showMessage("Student has been deleted successfully");
                 } else showMessage("Operation has been canceled");
             } else {showMessage("Only number format is accepted for Id field");}
         } else {showMessage("Id field must be filled for this operation");}
     }

     private void editStudent() throws SQLException, ClassNotFoundException {
         if(studentIdText.getText().length() > 0){
             if(studentIdText.getText().matches("[0-9]+")){
                 LibraryADO ado = new LibraryADO();
                 ado.connect();
                 boolean name = false, college = false;
                 if(studentNameText.getText().length() > 0){
                     ado.updateStudentName(Integer.parseInt(studentIdText.getText()), studentNameText.getText());
                     name = true;
                 }
                 if(studentCollegeText.getText().length() > 0){
                     ado.updateStudentCollege(Integer.parseInt(studentIdText.getText()), studentCollegeText.getText());
                     college = true;
                 }
                 ado.close();
                 if(name || college){
                     showMessage("Student has been edited successfully");
                     clearStudentsTable();
                     showAllStudents();
                 } else {showMessage("There is nothing to edit");}
             } else {showMessage("Only number format is accepted for Id field");}
         } else {showMessage("Id field must be filled for this operation");}
     }

     private void borrowBook() throws SQLException, ClassNotFoundException {
         if(borrowBookIdText.getText().length() > 0){
             if(borrowBookIdText.getText().matches("[0-9]+")){
                 if(borrowStudentIdText.getText().length() > 0){
                     if(borrowStudentIdText.getText().matches("[0-9]+")){
                        LibraryADO ado = new LibraryADO();
                        ado.connect();
                        ado.borrowBook(Integer.parseInt(borrowBookIdText.getText()), Integer.parseInt(borrowStudentIdText.getText()));
                        showMessage("The book has been borrowed");
                        ado.close();
                     } else {showMessage("Only number format is accepted for Student Id field");}
                 } else {showMessage("Student Id field must be filled");}
             } else {showMessage("Only number format is accepted for Book Id field");}
         } else {showMessage("Book Id field must be filled");}
     }

     private void returnBook() throws SQLException, ClassNotFoundException {
        if(returnStudentIdText.getText().length() > 0){
            if(returnStudentIdText.getText().matches("[0-9]+")){
                if(returnBookIdText.getText().length() > 0){
                    if(returnBookIdText.getText().matches("[0-9]+")){
                        LibraryADO ado = new LibraryADO();
                        ado.connect();
                        ado.returnBook(Integer.parseInt(returnStudentIdText.getText()), Integer.parseInt(returnBookIdText.getText()));
                        ado.close();
                        showMessage("The book has been returned");
                    } else {showMessage("Only number format is accepted for Book Id field");}
                } else {showMessage("Book Id field must be filled");}
            } else {showMessage("Only number format is accepted for Student Id field");}
        } else {showMessage("Student Id field must be filled");}
    }

    private void seeBorrowedBooks() throws SQLException, ClassNotFoundException {
        LibraryADO ado = new LibraryADO();
        ado.connect();
        Hashtable<Student, Book> borrowedBooks = ado.getBorrowedBooks();
        ado.close();

        String[] columns = {"Student Id", "Name", "College", "Book Id", "Title", "Author"};
        DefaultTableModel model = new DefaultTableModel(0, 6);
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model);

        for(Map.Entry<Student, Book> e : borrowedBooks.entrySet()){
            model.addRow(new Object[]{e.getKey().getId(), e.getKey().getName(), e.getKey().getCollege(), e.getValue().getId(), e.getValue().getTitle(), e.getValue().getAuthor()});
        }

        table.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("Borrowed books");
        frame.add(scrollPane);
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if(command.equals("Search book")) {
            try {
                clearBooksTable();
                showBookSearchResult();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(command.equals("Show all books")){
            try {
                clearBooksTable();
                showAllBooks();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(command.equals("Search student")){
            try {
                clearStudentsTable();
                showStudentSearchResult();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(command.equals("Show all students")) {
            try {
                clearStudentsTable();
                showAllStudents();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(command.equals("Add book")) {
            try {
                addBook();
            } catch (SQLException | ClassNotFoundException e) {
                showMessage(e.getMessage());
            }
        }
        else if(command.equals("Edit book")) {
            try {
                editBook();
            } catch (SQLException | ClassNotFoundException e) {
                showMessage(e.getMessage());
            }
        }
        else if(command.equals("Delete book")) {
            try {
                deleteBook();
            } catch (SQLException | ClassNotFoundException e) {
               showMessage(e.getMessage());
            }
        }
        else if(command.equals("Add student")) {
            try {
                addStudent();
            } catch (SQLException | ClassNotFoundException e) {
                showMessage(e.getMessage());
            }
        }
        else if(command.equals("Edit student")) {
            try {
                editStudent();
            } catch (SQLException | ClassNotFoundException e) {
                showMessage(e.getMessage());
            }
        }
        else if(command.equals("Delete student")) {
            try {
                deleteStudent();
            } catch (SQLException | ClassNotFoundException e) {
                showMessage(e.getMessage());
            }
        }
        else if(command.equals("Borrow book")){
            try {
                borrowBook();
            } catch (SQLException | ClassNotFoundException e) {
                showMessage(e.getMessage());
            }
        }
        else if(command.equals("Return book")){
            try {
                returnBook();
            } catch (SQLException | ClassNotFoundException e) {
                showMessage(e.getMessage());
            }
        }
        else if(command.equals("See borrowed books")){
            try {
                seeBorrowedBooks();
            } catch (SQLException | ClassNotFoundException e) {
                showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Library("Library Management");
            }
        });
    }
}
