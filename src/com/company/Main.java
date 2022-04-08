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
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

        try{

            Statement smt = connection.createStatement();


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
            smt.executeUpdate("insert into make values (5,3)");

            //2.1. Winona Ryder won the “Best supporting actor” award in 1994
            smt.executeUpdate("insert into award values (1,'Best supporting actor')");
            smt.executeUpdate("insert into actorObtain values (4,1,1994)");

            //2.2. Andrew Garfield won the “Best supporting actor” award in 2011
            smt.executeUpdate("insert into actorObtain values (9,1,2011)");

            //2.3. Jesse Eisenberg won the “Best main actor” award in 2011
            smt.executeUpdate("insert into award values (2,'Best main actor')");
            smt.executeUpdate("insert into actorObtain values (8,2,2011)");

            //2.4. Johnny Depp won the “Best villain actor” award in 2011
            smt.executeUpdate("insert into award values (3,'Best villain actor')");
            smt.executeUpdate("insert into actorObtain values (3,3,2011)");

            //2.5. Edward Scissorhands won the “Best fantasy movie” award in 1991
            smt.executeUpdate("insert into award values (4,'Best fantasy movie')");
            smt.executeUpdate("insert into movieObtain values (2,4,1991)");

            //2.6. Alice In Wonderland won the “Best fantasy movie” award in 2011
            smt.executeUpdate("insert into movieObtain values (3,4,2011)");

            //2.7. Youn Yuhjung won the “Best supporting actor” award in 2021
            smt.executeUpdate("insert into actorObtain values (2,1,2021)");

            //2.8. Minari won the “Best Foreign Language Film” award in 2021
            smt.executeUpdate("insert into award values (5,'Best Foreign Language Film')");
            smt.executeUpdate("insert into movieObtain values (1,5,2021)");

            //------------------------------

            //3.1 Bob rates 3 to “The Dark Knight”.
            smt.executeUpdate("insert into customerRate values (1,5,3)");

            //3.2 Bell rates 5 to the movies whose director is “Tim Burton”.
            smt.executeUpdate("insert into customerRate values (5,2,5)");
            smt.executeUpdate("insert into customerRate values (5,3,5)");

            //3.3 Jill rates 4 to the movies whose main actor is female.
            smt.executeUpdate("insert into customerRate values (4,2,4)");
            smt.executeUpdate("insert into customerRate values (4,3,4)");

            //3.4 Jack rates 4 to the fantasy movies.
            smt.executeUpdate("insert into customerRate values (3,2,4)");
            smt.executeUpdate("insert into customerRate values (3,3,4)");

            //3.5. John rates 5 to the movies whose actor won the “Best supporting actor” award (Ryder,andrew,yun)
            smt.executeUpdate("insert into customerRate values (2,1,5)");
            smt.executeUpdate("insert into customerRate values (2,2,5)");
            smt.executeUpdate("insert into customerRate values (2,1,5)");

            connection.close();
        }
        catch(SQLException e){
            System.out.println("쿼리 시행 안됨!");
            e.printStackTrace();
            return;
        }


    }
}
