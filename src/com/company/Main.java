package com.company;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        /// if you have a error in this part, check jdbc driver(.jar file)

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/project_movie", "postgres", "cse3207");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        /// if you have a error in this part, check DB information (db_name, user name, password)

        if (connection != null) {
            System.out.println(connection);
            System.out.println("You made it, take control your database now!\n");
        } else {
            System.out.println("Failed to make connection!");
        }

        try{

            Statement smt = connection.createStatement();

            smt.executeUpdate("CREATE TABLE director (" +
                    "directorID " + "int primary key,"+
                    "directorName " +"varchar(30),"+
                    "dateOfBirth " + "date,"+
                    "dateOfDeath " + "date" +
                    ")");

            smt.executeUpdate("CREATE TABLE actor (" +
                    "actorID int primary key," +
                    "actorName varchar(30)," +
                    "dateOfBirth date," +
                    "dateOfDeath date," +
                    "gender varchar (10)" +
                    ")");

            smt.executeUpdate("CREATE TABLE movie (" +
                    "movieID int primary key," +
                    "movieName varchar (30)," +
                    "releaseYear int," +
                    "releaseMonth int," +
                    "releaseDate int," +
                    "publisherName varchar (30)," +
                    "avgRate numeric (2,1)" +
                    ")");

            smt.executeUpdate("CREATE TABLE award (" +
                    "awardID int primary key," +
                    "awardName varchar(30)" +
                    ")");

            smt.executeUpdate("CREATE TABLE genre (" +
                    "genreName varchar (30) primary key)");

            smt.executeUpdate("CREATE Table movieGenre (" +
                    "movieID int," +
                    "genreName varchar (30)," +
                    "foreign key(movieID) references movie " +
                    "on delete cascade," +
                    "foreign key(genreName) references genre " +
                    "on delete cascade)");

            smt.executeUpdate("CREATE table movieObtain (" +
                    "movieID int," +
                    "awardID int," +
                    "year int," +
                    "foreign key(movieID) references movie " +
                    "on delete cascade," +
                    "foreign key(awardID) references award " +
                    "on delete cascade )");

            smt.executeUpdate("CREATE TABLE actorObtain (" +
                    "actorID int," +
                    "awardID int," +
                    "year int," +
                    "foreign key(actorID) references actor " +
                    "on delete cascade," +
                    "foreign key(awardID) references award " +
                    "on delete cascade )");

            smt.executeUpdate("CREATE TABLE directorObtain (" +
                    "directorID int," +
                    "awardID int," +
                    "year int," +
                    "foreign key(directorID) references director " +
                    "on delete cascade," +
                    "foreign key(awardID) references award " +
                    "on delete cascade )");

            smt.executeUpdate("CREATE TABLE casting (" +
                    "movieID int," +
                    "actorID int," +
                    "role varchar(30)," +
                    "foreign key(movieID) references movie " +
                    "on delete cascade," +
                    "foreign key(actorID) references actor " +
                    "on delete cascade )");

            smt.executeUpdate("CREATE TABLE make (" +
                    "movieID int," +
                    "directorID int," +
                    "foreign key(movieID) references movie " +
                    "on delete cascade," +
                    "foreign key(directorID) references director " +
                    "on delete cascade )");

            smt.executeUpdate("CREATE TABLE customer (" +
                    "customerID int primary key, " +
                    "customerName varchar (30)," +
                    "dateOfBirth date, " +
                    "gender varchar (10))");

            smt.executeUpdate("CREATE TABLE customerRate (" +
                    "customerID int, " +
                    "movieID int, " +
                    "rate numeric(2,1)," +
                    "foreign key(customerID) references customer " +
                    "on delete cascade," +
                    "foreign key(movieID) references movie " +
                    "on delete cascade )");

            //----------------------------------------------

            //genre
            smt.executeUpdate("insert into genre values ('Drama')");
            smt.executeUpdate("insert into genre values ('Fantasy')");
            smt.executeUpdate("insert into genre values ('Romance')");
            smt.executeUpdate("insert into genre values ('Adventure')");
            smt.executeUpdate("insert into genre values ('Family')");
            smt.executeUpdate("insert into genre values ('Action')");
            smt.executeUpdate("insert into genre values ('Mystery')");
            smt.executeUpdate("insert into genre values ('Thriller')");

            //Director
            smt.executeUpdate("insert into director values (1,'Lee Isaac Chung','1978-10-19',null)");
            smt.executeUpdate("insert into director values (2,'Tim Burton','1958-08-25',null)");
            smt.executeUpdate("insert into director values (3,'David Fincher','1962-08-28',null)");
            smt.executeUpdate("insert into director values (4,'Christopher Nolan','1970-07-03',null)");

            //Actor
            smt.executeUpdate("insert into actor values (1,'Steven Yeun','1983-12-21',null,'Male')");
            smt.executeUpdate("insert into actor values (2,'Youn Yuhjung','1947-06-19',null,'Female')");
            smt.executeUpdate("insert into actor values (3,'Johnny Depp','1963-06-09',null,'Male')");
            smt.executeUpdate("insert into actor values (4,'Winona Ryder','1971-10-29',null,'Female')");
            smt.executeUpdate("insert into actor values (5,'Anne Hathaway','1982-11-12',null,'Female')");
            smt.executeUpdate("insert into actor values (6,'Christian Bale','1974-01-30',null,'Male')");
            smt.executeUpdate("insert into actor values (7,'Heath Ledger','1979-04-04','2008-01-22','Male')");
            smt.executeUpdate("insert into actor values (8,'Jesse Eisenberg','1983-10-05',null,'Male')");
            smt.executeUpdate("insert into actor values (9,'Andrew Garfield','1983-08-20',null,'Male')");

            //Customer
            smt.executeUpdate("insert into customer values (1,'Bob','1997-11-14','Male')");
            smt.executeUpdate("insert into customer values (2,'John','1978-01-23','Male')");
            smt.executeUpdate("insert into customer values (3,'Jack','1980-05-04','Male')");
            smt.executeUpdate("insert into customer values (4,'Jill','1981-04-17','Female')");
            smt.executeUpdate("insert into customer values (5,'Bell','1990-05-14','Female')");

            //movie
            smt.executeUpdate("insert into movie values (1,'Minari',2020,12,11,'A24',0)");
            smt.executeUpdate("insert into movieGenre values (1,'Drama')");
            smt.executeUpdate("insert into casting values (1,1,'Main actor')");
            smt.executeUpdate("insert into casting values (1,2,'Supporting Actor')");
            smt.executeUpdate("insert into make values (1,1)");

            smt.executeUpdate("insert into movie values (2,'Edward Scissorhands',1991,06,29,'20th Century Fox Presents',0)");
            smt.executeUpdate("insert into movieGenre values (2,'Fantasy')");
            smt.executeUpdate("insert into movieGenre values (2,'Romance')");
            smt.executeUpdate("insert into casting values (2,3,'Main actor')");
            smt.executeUpdate("insert into casting values (2,4,'Main actor')");
            smt.executeUpdate("insert into make values (2,2)");

            smt.executeUpdate("insert into movie values (3,'Alice In Wonderland',2010,03,04,'Korea Sony Pictures',0)");
            smt.executeUpdate("insert into movieGenre values (3,'Fantasy')");
            smt.executeUpdate("insert into movieGenre values (3,'Adventure')");
            smt.executeUpdate("insert into movieGenre values (3,'Family')");
            smt.executeUpdate("insert into casting values (3,3,'Main actor')");
            smt.executeUpdate("insert into casting values (3,5,'Main actor')");
            smt.executeUpdate("insert into make values (3,2)");

            smt.executeUpdate("insert into movie values (4,'The Social Network',2010,11,18,'Korea Sony Pictures',0)");
            smt.executeUpdate("insert into movieGenre values (4,'Drama')");
            smt.executeUpdate("insert into casting values (4,8,'Main actor')");
            smt.executeUpdate("insert into casting values (4,9,'Supporting Actor')");
            smt.executeUpdate("insert into make values (4,3)");

            smt.executeUpdate("insert into movie values (5,'The Dark Knight',2008,08,06,'Warner Brothers Korea',0)");
            smt.executeUpdate("insert into movieGenre values (5,'Action')");
            smt.executeUpdate("insert into movieGenre values (5,'Drama')");
            smt.executeUpdate("insert into movieGenre values (5,'Mystery')");
            smt.executeUpdate("insert into movieGenre values (5,'Thriller')");
            smt.executeUpdate("insert into casting values (5,6,'Main actor')");
            smt.executeUpdate("insert into casting values (5,7,'Main actor')");
            smt.executeUpdate("insert into make values (5,4)");

            //2.1. Winona Ryder won the “Best supporting actor” award in 1994
            System.out.println("Statement : Winona Ryder won the “Best supporting actor” award in 1994");
            System.out.println("Translated SQL : insert into award values (1,'Best supporting actor')");
            smt.executeUpdate("insert into award values (1,'Best supporting actor')");
            System.out.println("Update tables\naward table");
            {
                ResultSet rs = smt.executeQuery("Select * from award");
                System.out.println("|" + String.format("%-10s" , "awardID")+ "|" + String.format("%-20s" , "awardName"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1))+ "|" + String.format("%-20s" , rs.getString("awardname")));
                }
                System.out.println();
            }
            System.out.println("Translated SQL : insert into actorObtain values (4,1,1994)");
            smt.executeUpdate("insert into actorObtain values (4,1,1994)");
            System.out.println("Update tables\nactorObtain table");
            {
                ResultSet rs = smt.executeQuery("Select * from actorObtain");
                System.out.println("|" + String.format( "%-10s" , "actorID")+ "|" + String.format("%-10s" , "awardId") + "|" + String.format("%-5s", "year"));
                while(rs.next()){

                    System.out.println("|" + String.format("%-10d", rs.getInt(1)) + "|" + String.format("%-10d", rs.getInt(2)) + "|" + String.format("%-5d",rs.getInt(3)));
                }
                System.out.println();
            }

            //2.2. Andrew Garfield won the “Best supporting actor” award in 2011
            System.out.println("Statement : Andrew Garfield won the “Best supporting actor” award in 2011");
            System.out.println("Translated SQL : insert into actorObtain values (9,1,2011)");
            smt.executeUpdate("insert into actorObtain values (9,1,2011)");
            System.out.println("Update tables\nactorObtain table");
            {
                ResultSet rs = smt.executeQuery("Select * from actorObtain");
                System.out.println("|" + String.format( "%-10s" , "actorID")+ "|" + String.format("%-10s" , "awardId") + "|" + String.format("%-5s", "year"));
                while(rs.next()){

                    System.out.println("|" + String.format("%-10d", rs.getInt(1)) + "|" + String.format("%-10d", rs.getInt(2)) + "|" + String.format("%-5d",rs.getInt(3)));
                }
                System.out.println();
            }

            //2.3. Jesse Eisenberg won the “Best main actor” award in 2011
            System.out.println("Statement : Jesse Eisenberg won the “Best main actor” award in 2011");
            System.out.println("Translated SQL : insert into award values (2,'Best main actor')");
            smt.executeUpdate("insert into award values (2,'Best main actor')");
            System.out.println("Update tables\naward table");
            {
                ResultSet rs = smt.executeQuery("Select * from award");
                System.out.println("|" + String.format("%-10s" , "awardID")+ "|" + String.format("%-20s" , "awardName"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1))+ "|" + String.format("%-20s" , rs.getString("awardname")));
                }
                System.out.println();
            }
            System.out.println("Translated SQL : insert into actorObtain values (8,2,2011)");
            smt.executeUpdate("insert into actorObtain values (8,2,2011)");
            System.out.println("Update tables\nactorObtain table");
            {
                ResultSet rs = smt.executeQuery("Select * from actorObtain");
                System.out.println("|" + String.format( "%-10s" , "actorID")+ "|" + String.format("%-10s" , "awardId") + "|" + String.format("%-5s", "year"));
                while(rs.next()){

                    System.out.println("|" + String.format("%-10d", rs.getInt(1)) + "|" + String.format("%-10d", rs.getInt(2)) + "|" + String.format("%-5d",rs.getInt(3)));
                }
                System.out.println();
            }

            //2.4. Johnny Depp won the “Best villain actor” award in 2011
            System.out.println("Statement : Johnny Depp won the “Best villain actor” award in 2011");
            System.out.println("Translated SQL : insert into award values (3,'Best villain actor')");
            smt.executeUpdate("insert into award values (3,'Best villain actor')");
            System.out.println("Update tables\naward table");
            {
                ResultSet rs = smt.executeQuery("Select * from award");
                System.out.println("|" + String.format("%-10s" , "awardID")+ "|" + String.format("%-20s" , "awardName"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1))+ "|" + String.format("%-20s" , rs.getString("awardname")));
                }
                System.out.println();
            }
            System.out.println("Translated SQL : insert into actorObtain values (3,3,2011)");
            smt.executeUpdate("insert into actorObtain values (3,3,2011)");
            System.out.println("Update tables\nactorObtain table");
            {
                ResultSet rs = smt.executeQuery("Select * from actorObtain");
                System.out.println("|" + String.format( "%-10s" , "actorID")+ "|" + String.format("%-10s" , "awardId") + "|" + String.format("%-5s", "year"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d", rs.getInt(1)) + "|" + String.format("%-10d", rs.getInt(2)) + "|" + String.format("%-5d",rs.getInt(3)));
                }
                System.out.println();
            }

            //2.5. Edward Scissorhands won the “Best fantasy movie” award in 1991
            System.out.println("Statement : Edward Scissorhands won the “Best fantasy movie” award in 1991");
            System.out.println("Translated SQL : insert into award values (4,'Best fantasy movie')");
            smt.executeUpdate("insert into award values (4,'Best fantasy movie')");
            System.out.println("Update tables\naward table");
            {
                ResultSet rs = smt.executeQuery("Select * from award");
                System.out.println("|" + String.format("%-10s" , "awardID")+ "|" + String.format("%-20s" , "awardName"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1))+ "|" + String.format("%-20s" , rs.getString("awardname")));
                }
                System.out.println();
            }
            System.out.println("Translated SQL : insert into movieObtain values (2,4,1991)");
            smt.executeUpdate("insert into movieObtain values (2,4,1991)");
            System.out.println("Update tables\nmovieObtain table");
            {
                ResultSet rs = smt.executeQuery("Select * from movieObtain");
                System.out.println("|" + String.format("%-10s" , "movieID") + "|" + String.format("%-10s" , "awardID") + "|" + String.format("%-5s" , "year"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d", rs.getInt(1)) +"|"+ String.format("%-10d", rs.getInt(2)) + "|" + String.format("%-5d", (rs.getInt(3))));
                }
                System.out.println();
            }

            System.out.println("Translated SQL : insert into directorObtain values (2,4,1991)");
            smt.executeUpdate("insert into directorObtain values (2,4,1991)");
            System.out.println("Update tables\ndirectorObtain table");
            {
                ResultSet rs = smt.executeQuery("Select * from directorObtain");
                System.out.println("|" + String.format("%-10s" , "directorID") + "|" + String.format("%-10s" , "awardID") + "|" + String.format("%-5s" , "year"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d", rs.getInt(1)) +"|"+ String.format("%-10d", rs.getInt(2)) + "|" + String.format("%-5d", (rs.getInt(3))));
                }
                System.out.println();
            }

            //2.6. Alice In Wonderland won the “Best fantasy movie” award in 2011
            System.out.println("Statement : Alice In Wonderland won the “Best fantasy movie” award in 2011");
            System.out.println("Translated SQL : insert into movieObtain values (3,4,2011)");
            smt.executeUpdate("insert into movieObtain values (3,4,2011)");
            System.out.println("Update tables\nmovieObtain table");
            {
                ResultSet rs = smt.executeQuery("Select * from movieObtain");
                System.out.println("|" + String.format("%-10s" , "movieID") + "|" + String.format("%-10s" , "awardID") + "|" + String.format("%-5s" , "year"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d", rs.getInt(1)) +"|"+ String.format("%-10d", rs.getInt(2)) + "|" + String.format("%-5d", (rs.getInt(3))));
                }
                System.out.println();
            }

            System.out.println("Translated SQL : insert into directorObtain values (2,4,2011)");
            smt.executeUpdate("insert into directorObtain values (2,4,2011)");
            System.out.println("Update tables\ndirectorObtain table");
            {
                ResultSet rs = smt.executeQuery("Select * from directorObtain");
                System.out.println("|" + String.format("%-10s" , "directorID") + "|" + String.format("%-10s" , "awardID") + "|" + String.format("%-5s" , "year"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d", rs.getInt(1)) +"|"+ String.format("%-10d", rs.getInt(2)) + "|" + String.format("%-5d", (rs.getInt(3))));
                }
                System.out.println();
            }

            //2.7. Youn Yuhjung won the “Best supporting actor” award in 2021
            System.out.println("Statement : Youn Yuhjung won the “Best supporting actor” award in 2021");
            System.out.println("Translated SQL : insert into actorObtain values (2,1,2021)");
            smt.executeUpdate("insert into actorObtain values (2,1,2021)");
            System.out.println("Update tables\nactorObtain table");
            {
                ResultSet rs = smt.executeQuery("Select * from actorObtain");
                System.out.println("|" + String.format( "%-10s" , "actorID")+ "|" + String.format("%-10s" , "awardId") + "|" + String.format("%-5s", "year"));
                while(rs.next()){

                    System.out.println("|" + String.format("%-10d", rs.getInt(1)) + "|" + String.format("%-10d", rs.getInt(2)) + "|" + String.format("%-5d",rs.getInt(3)));
                }
                System.out.println();
            }

            //2.8. Minari won the “Best Foreign Language Film” award in 2021
            System.out.println("Statement : Minari won the “Best Foreign Language Film” award in 2021");
            System.out.println("Translated SQL : insert into award values (5,'Best Foreign Language Film");
            smt.executeUpdate("insert into award values (5,'Best Foreign Language Film')");
            System.out.println("Update tables\naward table");
            {
                ResultSet rs = smt.executeQuery("Select * from award");
                System.out.println("|" + String.format("%-10s" , "awardID")+ "|" + String.format("%-20s" , "awardName"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1))+ "|" + String.format("%-20s" , rs.getString("awardname")));
                }
                System.out.println();
            }
            System.out.println("Translated SQL : insert into movieObtain values (1,5,2021)");
            smt.executeUpdate("insert into movieObtain values (1,5,2021)");
            System.out.println("Update tables\nmovieObtain table");
            {
                ResultSet rs = smt.executeQuery("Select * from movieObtain");
                System.out.println("|" + String.format("%-10s" , "movieObtain") + "|" + String.format("%-10s" , "awardID") + "|" + String.format("%-5s" , "year"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d", rs.getInt(1)) +"|"+ String.format("%-10d", rs.getInt(2)) + "|" + String.format("%-5d", (rs.getInt(3))));
                }
                System.out.println();
            }

            System.out.println("Translated SQL : insert into directorObtain values (1,5,2021)");
            smt.executeUpdate("insert into directorObtain values (1,5,2021)");
            System.out.println("Update tables\ndirectorObtain table");
            {
                ResultSet rs = smt.executeQuery("Select * from directorObtain");
                System.out.println("|" + String.format("%-10s" , "directorID") + "|" + String.format("%-10s" , "awardID") + "|" + String.format("%-5s" , "year"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d", rs.getInt(1)) +"|"+ String.format("%-10d", rs.getInt(2)) + "|" + String.format("%-5d", (rs.getInt(3))));
                }
                System.out.println();
            }

            //------------------------------

            //3.1 Bob rates 3 to “The Dark Knight”.
            System.out.println("Statement : Bob rates 3 to “The Dark Knight");
            System.out.println("Translated SQL : insert into customerRate values (1,5,3)");
            smt.executeUpdate("insert into customerRate values (1,5,3)");
            System.out.println("Update tables\ncustomerRate table");
            {
                ResultSet rs = smt.executeQuery("Select * from customerRate");
                System.out.println("|" + String.format("%-10s", "customerID") + "|" + String.format("%-10s", "movieID") + "|" + String.format("%-10s", "rate"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1)) + "|" + String.format("%-10d",rs.getInt(2)) + "|" + String.format("%-10d", rs.getInt(3)));
                }
                System.out.println();
            }

            //3.2 Bell rates 5 to the movies whose director is “Tim Burton”.
            System.out.println("Statement : Bell rates 5 to the movies whose director is “Tim Burton");
            System.out.println("Translated SQL : insert into customerRate values (5,2,5)");
            smt.executeUpdate("insert into customerRate values (5,2,5)");
            System.out.println("Update tables\ncustomerRate table");
            {
                ResultSet rs = smt.executeQuery("Select * from customerRate");
                System.out.println("|" + String.format("%-10s", "customerID") + "|" + String.format("%-10s", "movieID") + "|" + String.format("%-10s", "rate"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1)) + "|" + String.format("%-10d",rs.getInt(2)) + "|" + String.format("%-10d", rs.getInt(3)));
                }
                System.out.println();
            }
            System.out.println("Translated SQL : insert into customerRate values (5,3,5)");
            smt.executeUpdate("insert into customerRate values (5,3,5)");
            System.out.println("Update tables\ncustomerRate table");
            {
                ResultSet rs = smt.executeQuery("Select * from customerRate");
                System.out.println("|" + String.format("%-10s", "customerID") + "|" + String.format("%-10s", "movieID") + "|" + String.format("%-10s", "rate"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1)) + "|" + String.format("%-10d",rs.getInt(2)) + "|" + String.format("%-10d", rs.getInt(3)));
                }
                System.out.println();
            }

            //3.3 Jill rates 4 to the movies whose main actor is female.
            System.out.println("Statement : Jill rates 4 to the movies whose main actor is female.");
            System.out.println("Translated SQL : insert into customerRate values (4,2,4)");
            smt.executeUpdate("insert into customerRate values (4,2,4)");
            System.out.println("Update tables\ncustomerRate table");
            {
                ResultSet rs = smt.executeQuery("Select * from customerRate");
                System.out.println("|" + String.format("%-10s", "customerID") + "|" + String.format("%-10s", "movieID") + "|" + String.format("%-10s", "rate"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1)) + "|" + String.format("%-10d",rs.getInt(2)) + "|" + String.format("%-10d", rs.getInt(3)));
                }
                System.out.println();
            }
            System.out.println("Translated SQL : insert into customerRate values (4,3,4)");
            smt.executeUpdate("insert into customerRate values (4,3,4)");
            System.out.println("Update tables\ncustomerRate table");
            {
                ResultSet rs = smt.executeQuery("Select * from customerRate");
                System.out.println("|" + String.format("%-10s", "customerID") + "|" + String.format("%-10s", "movieID") + "|" + String.format("%-10s", "rate"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1)) + "|" + String.format("%-10d",rs.getInt(2)) + "|" + String.format("%-10d", rs.getInt(3)));
                }
                System.out.println();
            }

            //3.4 Jack rates 4 to the fantasy movies.
            System.out.println("Statement : Jill rates 4 to the movies whose main actor is female.");
            System.out.println("Translated SQL : insert into customerRate values (3,2,4)");
            smt.executeUpdate("insert into customerRate values (3,2,4)");
            System.out.println("Update tables\ncustomerRate table");
            {
                ResultSet rs = smt.executeQuery("Select * from customerRate");
                System.out.println("|" + String.format("%-10s", "customerID") + "|" + String.format("%-10s", "movieID") + "|" + String.format("%-10s", "rate"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1)) + "|" + String.format("%-10d",rs.getInt(2)) + "|" + String.format("%-10d", rs.getInt(3)));
                }
                System.out.println();
            }
            System.out.println("Translated SQL : insert into customerRate values (3,3,4)");
            smt.executeUpdate("insert into customerRate values (3,3,4)");
            System.out.println("Update tables\ncustomerRate table");
            {
                ResultSet rs = smt.executeQuery("Select * from customerRate");
                System.out.println("|" + String.format("%-10s", "customerID") + "|" + String.format("%-10s", "movieID") + "|" + String.format("%-10s", "rate"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1)) + "|" + String.format("%-10d",rs.getInt(2)) + "|" + String.format("%-10d", rs.getInt(3)));
                }
                System.out.println();
            }

            //3.5. John rates 5 to the movies whose actor won the “Best supporting actor” award (Ryder,andrew,yun)
            System.out.println("Statement : John rates 5 to the movies whose actor won the “Best supporting actor” award (Ryder,andrew,yun)");
            System.out.println("Translated SQL : insert into customerRate values (2,1,5)");
            smt.executeUpdate("insert into customerRate values (2,1,5)");
            System.out.println("Update tables\ncustomerRate table");
            {
                ResultSet rs = smt.executeQuery("Select * from customerRate");
                System.out.println("|" + String.format("%-10s", "customerID") + "|" + String.format("%-10s", "movieID") + "|" + String.format("%-10s", "rate"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1)) + "|" + String.format("%-10d",rs.getInt(2)) + "|" + String.format("%-10d", rs.getInt(3)));
                }
                System.out.println();
            }
            System.out.println("Translated SQL : insert into customerRate values (2,2,5)");
            smt.executeUpdate("insert into customerRate values (2,2,5)");
            System.out.println("Update tables\ncustomerRate table");
            {
                ResultSet rs = smt.executeQuery("Select * from customerRate");
                System.out.println("|" + String.format("%-10s", "customerID") + "|" + String.format("%-10s", "movieID") + "|" + String.format("%-10s", "rate"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1)) + "|" + String.format("%-10d",rs.getInt(2)) + "|" + String.format("%-10d", rs.getInt(3)));
                }
                System.out.println();
            }
            System.out.println("Translated SQL : insert into customerRate values (2,4,5)");
            smt.executeUpdate("insert into customerRate values (2,4,5)");
            System.out.println("Update tables\ncustomerRate table");
            {
                ResultSet rs = smt.executeQuery("Select * from customerRate");
                System.out.println("|" + String.format("%-10s", "customerID") + "|" + String.format("%-10s", "movieID") + "|" + String.format("%-10s", "rate"));
                while(rs.next()){
                    System.out.println("|" + String.format("%-10d" , rs.getInt(1)) + "|" + String.format("%-10d",rs.getInt(2)) + "|" + String.format("%-10d", rs.getInt(3)));
                }
                System.out.println();
            }

            //-------------------------------------------------------
            //4. Select the names of the movies whose actor are dead.
            System.out.println("Statement : Select the names of the movies whose actor are dead.");
            System.out.println("Translated SQL : select actorName from actor where dateOfDeath is not null");
            {
                ResultSet rs = smt.executeQuery("select actorName from actor where dateOfDeath is not null");
                System.out.println("|" + String.format("%-10s", "actorName"));
                while(rs.next()){
                    System.out.println( String.format("%-10s", rs.getString(1)));
                }
                System.out.println();
            }
            //5. Select the names of the directors who cast the same actor more than once.
            System.out.println("Statement : Select the names of the directors who cast the same actor more than once.");
            System.out.println("Translated SQL : with result1 as (\n" +
                    "                        with K as (select make.movieID,directorID,actorID from make,casting where make.movieID = casting.movieID) +\n" +
                    "                        select A.directorID\n" +
                    "                        from K as A, K as B\n" +
                    "                        where A.movieID != B.movieID and A.directorID = B.directorID and A.actorID=B.actorID)\n" +
                    "                 select distinct directorName from result1,director where result1.directorID = director.directorID");
            {

                ResultSet rs = smt.executeQuery(
                        "with result1 as (" +
                        "with K as (select make.movieID,directorID,actorID from make,casting where make.movieID = casting.movieID)" +
                                "select A.directorID " + "" +
                                "from K as A, K as B " +
                                "where A.movieID != B.movieID and A.directorID = B.directorID and A.actorID=B.actorID) " +
                        "select distinct directorName from result1,director where result1.directorID = director.directorID"
                );
                System.out.println("|" + String.format("%-10s","directorName"));
                while(rs.next()){
                    System.out.println(String.format("%-10s",rs.getString(1)) );
                }
                System.out.println();
            }

            //6. Select the names of the movies and the genres, where movies have the common genre
            String[] genre = {"Drama","Fantasy","Romance","Adventure","Family","Action","Mystery","Thriller"};
            System.out.println("Statement : Select the names of the movies and the genres, where movies have the common genre");
            System.out.println("Translated SQL : select genreName, movieName from movieGenre,movie where movieGenre.movieID = movie.movieID");
            {

                for(int i=0; i<8;i++) {
                    ResultSet rs = smt.executeQuery(
                            "select genreName, movieName from movieGenre,movie where movieGenre.movieID = movie.movieID "
                    );
                    System.out.print(genre[i] + " : ");
                    while (rs.next()) {
                        if (genre[i].compareTo(rs.getString(1)) == 0) {
                            System.out.print(String.format("%s", rs.getString(2)) + ", ");
                        }
                    }

                    System.out.println();
                }
                System.out.println();
            }

            //7. Delete the movies whose director or actor did not get any award and delete data from related tables.
            System.out.println("Statement : Delete the movies whose director or actor did not get any award and delete data from related tables.");
            System.out.println("Translated SQL : delete from director where directorName not in (select directorName from director,directorObtain where director.directorID = directorObtain.directorID)");
            {
                smt.executeUpdate("delete from director where directorName not in (select directorName from director,directorObtain where director.directorID = directorObtain.directorID)");
                System.out.println("Update tables\ndirector table");
                ResultSet rs1 = smt.executeQuery("select * from director");
                System.out.println("|" + String.format("%-15s","directorID") + "|" + String.format("%-20s","directorName") + "|" + String.format("%-15s","dateOfBirth") + "|" + String.format("%-15s", "dateOfDeath"));
                while (rs1.next()) {
                    System.out.println(String.format("%-16d", rs1.getInt(1)) + String.format("%-21s", rs1.getString(2)) + String.format("%-16s", rs1.getString(3)) + String.format("%-16s", rs1.getString(4)));
                }
                System.out.println();
            }
            System.out.println("Translated SQL : delete from actor where actorName not in (select actorName from actor,actorObtain where actor.actorID = actorObtain.actorID)");
            {
                smt.executeUpdate("delete from actor where actorName not in (select actorName from actor,actorObtain where actor.actorID = actorObtain.actorID)");
                System.out.println("Update tables\nactor table");
                ResultSet rs2 = smt.executeQuery("select * from actor");
                System.out.println("|" + String.format("%-10s","actorID") + "|" + String.format("%-20s","actorName") + "|" + String.format("%-15s","dateOfBirth") + "|" + String.format("%-15s","dateOfDeath") + "|" + "gender");
                while(rs2.next()){
                    System.out.println(String.format("%-11d", rs2.getInt(1)) + String.format("%-21s", rs2.getString(2)) + String.format("%-16s", rs2.getString(3)) + String.format("%-16s", rs2.getString(4)) + rs2.getString(5));
                }
                System.out.println();
            }

            //8. Delete all customers and delete data from related tables.
            System.out.println("Statement : Delete all customers and delete data from related tables.");
            System.out.println("Translated SQL : delete from customer");
            {
                smt.executeUpdate("delete from customer ");
                ResultSet rs = smt.executeQuery("select * from customer ");
                System.out.println();
            }
            //9. Delete all tables and data (make the database empty).
            System.out.println("Statement : Delete all customers and delete data from related tables.");
            {
                System.out.println("Translated SQL : ");
                System.out.println("drop table director CASCADE\n" +
                        "drop table actor CASCADE\n" +
                        "drop table movie CASCADE\n" +
                        "drop table award CASCADE\n" +
                        "drop table genre CASCADE\n" +
                        "drop table customer CASCADE\n" +
                        "drop table movieGenre\n" +
                        "drop table movieObtain\n" +
                        "drop table actorObtain\n" +
                        "drop table directorObtain\n" +
                        "drop table casting\n" +
                        "drop table make\n" +
                        "drop table customerRate");

                smt.executeUpdate("drop table director CASCADE");
                smt.executeUpdate("drop table actor CASCADE");
                smt.executeUpdate("drop table movie CASCADE");
                smt.executeUpdate("drop table award CASCADE");
                smt.executeUpdate("drop table genre CASCADE");
                smt.executeUpdate("drop table customer CASCADE");
                smt.executeUpdate("drop table movieGenre ");
                smt.executeUpdate("drop table movieObtain ");
                smt.executeUpdate("drop table actorObtain ");
                smt.executeUpdate("drop table directorObtain ");
                smt.executeUpdate("drop table casting ");
                smt.executeUpdate("drop table make ");
                smt.executeUpdate("drop table customerRate ");
            }

            connection.close();
        }
        catch(SQLException e){
            System.out.println("쿼리 시행 안됨!");
            e.printStackTrace();
            return;
        }


    }
}
