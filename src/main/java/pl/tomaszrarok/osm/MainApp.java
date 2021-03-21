package pl.tomaszrarok.osm;

import lombok.extern.slf4j.Slf4j;
import pl.tomaszrarok.osm.model.Student;
import pl.tomaszrarok.osm.table.StudentsTableModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MainApp {
    private JPanel panel1;
    private JButton Students;
    private JButton Teachers;
    private JButton Courses;
    private JButton Terms;
    private JButton Payments;
    private JPanel StudentsPanel;
    private JPanel TeachersPanel;
    private JPanel CoursesPanel;
    private JPanel CardPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton newButton;
    private JButton saveButton;
    private JTable table1;

    public MainApp() {

        /**
         * We perform refresh data load for students here.
         */
        Students.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initializeStudents();
                CardLayout layout = (CardLayout)CardPanel.getLayout();
                layout.show(CardPanel, "Card1");

            }
        });
        Teachers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout layout = (CardLayout)CardPanel.getLayout();
                layout.show(CardPanel, "Card2");
                log.info("action performed card 2");
            }
        });
        Courses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout layout = (CardLayout)CardPanel.getLayout();
                layout.show(CardPanel, "Card3");
                log.info("action performed card 3");
            }
        });

        /**
         * Here we can initialize context of initial panel - students.
         */
        CardPanel.addHierarchyListener(new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent hierarchyEvent) {
                initializeStudents();
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable source = (JTable)e.getSource();
                int row = source.rowAtPoint( e.getPoint() );
                int column = source.columnAtPoint( e.getPoint() );
                log.info( source.getModel().getValueAt(row, column)+"" );
            }
        });
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                //initializeDatabase();
            }
        });
    }

    private void initializeStudents() {
        Map<String, Student> students = new HashMap<>();
        students.put("1", new Student("John","Smith","00001", "john@awesome.com", "+48 123 456 789"));
        students.put("2",new Student("Garry","Geek","00002", "garry@awesome.com", "+48 321 456 789"));
        students.put("3",new Student("Andrzej","Nowak","00003", "andrzej@awesome.com", "+132 123 456 789"));
        students.put("4",new Student("Misio","Uszatek","00004", "misio@awesome.com", "+48 231 456 789"));
        students.put("5",new Student("Juliusz","Ceaser","00005", "juliusz@awesome.com", "+48 312 456 789"));


        
        table1.setModel(new StudentsTableModel(students));
    }

    private static void initializeDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Test test = em.find(Test.class, 1);
        if (test == null) {
            test = new Test();
            test.id = 1;
            test.data = "a";

            tx.begin();
            em.persist(test);
            tx.commit();
        }

        System.out.format("Test{id=%s, data=%s}\n", test.id, test.data);

        em.close();
        emf.close();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Online School Manager");
        frame.setContentPane(new MainApp().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
