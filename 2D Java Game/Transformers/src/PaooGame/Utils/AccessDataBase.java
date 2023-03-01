package PaooGame.Utils;

import PaooGame.Game;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccessDataBase {

    Game gp;

    public AccessDataBase(Game gp){
        this.gp = gp;
    }

    public static void insertScore(String name, double time){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:res/dataBase/Transformers.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "INSERT INTO Scores (Nume, Scor) " +
                    "VALUES ('" + name + "' , " + time + ");";

            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public void insertSave(int lvl, String name, double time, int playerX, int playerY, int damage, int lives, int rELeft, int rERight){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:res/dataBase/Transformers.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime data = LocalDateTime.now();

            String dataS = dtf.format(data);

            String sql = "INSERT INTO Saves (Nivel, Nume, Timp, PlayerX, PlayerY, Damage, Lives, RELeft, RERight, Data) " +
                    "VALUES (" + lvl + " , '" + name + "' , " + time + " , " + playerX + " , " + playerY + " , "
                    + damage + " , " + lives + " , " + rELeft + " , " + rERight + ", '" + dataS + "');";

            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public void getScores(Graphics2D g){
        Connection c = null;
        Statement stmt = null;
        String[] sir = new String[4];

        final String QUERY = "SELECT Nume, Scor FROM Scores";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:res/dataBase/Transformers.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY + " ORDER BY Scor");
            int counter = 0;
            int y = 340;
            while ( rs.next() ) {
                String Nume = rs.getString("Nume");
                double Time = rs.getDouble("Scor");

                DecimalFormat df = new DecimalFormat("#0.00");

                sir[0] = Nume;
                sir[1] = String.valueOf(df.format(Time));

                if(counter < 5) {
                    counter++;
                    g.setColor(Color.BLACK);
                    g.drawString(sir[0], 273, y + 3);
                    g.setColor(Color.YELLOW);
                    g.drawString(sir[0], 270, y);
                    g.setColor(Color.BLACK);
                    g.drawString(sir[1], 903, y + 3);
                    g.setColor(Color.YELLOW);
                    g.drawString(sir[1], 900, y);
                    y += 60;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void getSave(Graphics2D g, int loadNumber){
        Connection c = null;
        Statement stmt = null;
        Setter setter = new Setter(gp);
        int counter = 0;
        int y = 260;
        String[][] sir = new String[5][3];

        final String QUERY = "SELECT * FROM Saves";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:res/dataBase/Transformers.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY + " ORDER BY Data DESC");
            while ( rs.next() && counter < 4) {
                String nume = rs.getString("Nume");
                double timp = rs.getDouble("Timp");
                int nivel = rs.getInt("Nivel");
                int playerX = rs.getInt("PlayerX");
                int playerY = rs.getInt("PlayerY");
                int damage = rs.getInt("Damage");
                int lives = rs.getInt("Lives");
                int rELeft = rs.getInt("RELeft");
                int rERight = rs.getInt("RERight");

                DecimalFormat df = new DecimalFormat("#0.00");

                sir[counter][0] = nume;
                sir[counter][2] = df.format(timp);
                df = new DecimalFormat("#0");
                sir[counter][1] = df.format(nivel);

                g.setColor(Color.BLACK);
                g.drawString(sir[counter][0], 173, y + 3);
                g.setColor(Color.YELLOW);
                g.drawString(sir[counter][0], 170, y);
                g.setColor(Color.BLACK);
                g.drawString(sir[counter][1], 453, y + 3);
                g.setColor(Color.YELLOW);
                g.drawString(sir[counter][1], 450, y);
                g.setColor(Color.BLACK);
                g.drawString(sir[counter][2], 603, y + 3);
                g.setColor(Color.YELLOW);
                g.drawString(sir[counter][2], 600, y);
                y += 110;

                counter ++;
                if(loadNumber != -1 && loadNumber == counter)
                switch (nivel) {
                    case 1:
                        setter.loadLvl1(playerX, playerY, damage, lives, rERight);
                        gp.name = nume;
                        gp.playTime = timp;
                        break;
                    case 2:
                        setter.loadLvl2(playerX, playerY, damage, lives);
                        gp.name = nume;
                        gp.playTime = timp;
                        break;
                    case 3:
                        setter.loadLvl3(playerX, playerY, damage, lives, rELeft, rERight);
                        gp.name = nume;
                        gp.playTime = timp;
                        break;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}


