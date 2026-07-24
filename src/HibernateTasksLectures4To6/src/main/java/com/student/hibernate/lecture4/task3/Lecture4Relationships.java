package com.student.hibernate.lecture4.task3;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

public class Lecture4Relationships {

    @Entity(name = "L4Student")
    @Table(name = "l4_students")
    public static class Student {
        @Id
        private Long id;
        @OneToOne
        private Passport passport;
        @ManyToMany(mappedBy = "students")
        private List<Course> courses = new ArrayList<>();

        public Student() {
        }
    }

    @Entity(name = "L4Passport")
    @Table(name = "l4_passports")
    public static class Passport {
        @Id
        private Long id;
        @OneToOne(mappedBy = "passport")
        private Student student;

        public Passport() {
        }
    }

    @Entity(name = "L4Employee")
    @Table(name = "l4_employees")
    public static class Employee {
        @Id
        private Long id;
        @ManyToOne
        private Department department;
        @ManyToMany(mappedBy = "employees")
        private List<Project> projects = new ArrayList<>();

        public Employee() {
        }
    }

    @Entity(name = "L4Department")
    @Table(name = "l4_departments")
    public static class Department {
        @Id
        private Long id;
        @OneToMany(mappedBy = "department")
        private List<Employee> employees = new ArrayList<>();

        public Department() {
        }
    }

    @Entity(name = "L4Customer")
    @Table(name = "l4_customers")
    public static class Customer {
        @Id
        private Long id;
        @OneToMany(mappedBy = "customer")
        private List<Order> orders = new ArrayList<>();

        public Customer() {
        }
    }

    @Entity(name = "L4Order")
    @Table(name = "l4_orders")
    public static class Order {
        @Id
        private Long id;
        @ManyToOne
        private Customer customer;

        public Order() {
        }
    }

    @Entity(name = "L4Book")
    @Table(name = "l4_books")
    public static class Book {
        @Id
        private Long id;
        @ManyToMany
        @JoinTable(name = "l4_book_authors")
        private List<Author> authors = new ArrayList<>();
        @ManyToOne
        private Library library;

        public Book() {
        }
    }

    @Entity(name = "L4Author")
    @Table(name = "l4_authors")
    public static class Author {
        @Id
        private Long id;
        @ManyToMany(mappedBy = "authors")
        private List<Book> books = new ArrayList<>();

        public Author() {
        }
    }

    @Entity(name = "L4Teacher")
    @Table(name = "l4_teachers")
    public static class Teacher {
        @Id
        private Long id;
        @ManyToMany
        @JoinTable(name = "l4_teacher_subjects")
        private List<Subject> subjects = new ArrayList<>();

        public Teacher() {
        }
    }

    @Entity(name = "L4Subject")
    @Table(name = "l4_subjects")
    public static class Subject {
        @Id
        private Long id;
        @ManyToMany(mappedBy = "subjects")
        private List<Teacher> teachers = new ArrayList<>();

        public Subject() {
        }
    }

    @Entity(name = "L4User")
    @Table(name = "l4_users")
    public static class User {
        @Id
        private Long id;
        @ManyToMany
        @JoinTable(name = "l4_user_roles")
        private List<Role> roles = new ArrayList<>();

        public User() {
        }
    }

    @Entity(name = "L4Role")
    @Table(name = "l4_roles")
    public static class Role {
        @Id
        private Long id;
        @ManyToMany(mappedBy = "roles")
        private List<User> users = new ArrayList<>();

        public Role() {
        }
    }

    @Entity(name = "L4Course")
    @Table(name = "l4_courses")
    public static class Course {
        @Id
        private Long id;
        @ManyToMany
        @JoinTable(name = "l4_course_students")
        private List<Student> students = new ArrayList<>();

        public Course() {
        }
    }

    @Entity(name = "L4Library")
    @Table(name = "l4_libraries")
    public static class Library {
        @Id
        private Long id;
        @OneToMany(mappedBy = "library")
        private List<Book> books = new ArrayList<>();

        public Library() {
        }
    }

    @Entity(name = "L4Doctor")
    @Table(name = "l4_relation_doctors")
    public static class Doctor {
        @Id
        private Long id;
        @ManyToMany
        @JoinTable(name = "l4_doctor_patients")
        private List<Patient> patients = new ArrayList<>();

        public Doctor() {
        }
    }

    @Entity(name = "L4Patient")
    @Table(name = "l4_relation_patients")
    public static class Patient {
        @Id
        private Long id;
        @ManyToMany(mappedBy = "patients")
        private List<Doctor> doctors = new ArrayList<>();

        public Patient() {
        }
    }

    @Entity(name = "L4Product")
    @Table(name = "l4_products")
    public static class Product {
        @Id
        private Long id;
        @ManyToOne
        private Category category;

        public Product() {
        }
    }

    @Entity(name = "L4Category")
    @Table(name = "l4_categories")
    public static class Category {
        @Id
        private Long id;
        @OneToMany(mappedBy = "category")
        private List<Product> products = new ArrayList<>();

        public Category() {
        }
    }

    @Entity(name = "L4Project")
    @Table(name = "l4_projects")
    public static class Project {
        @Id
        private Long id;
        @ManyToMany
        @JoinTable(name = "l4_project_employees")
        private List<Employee> employees = new ArrayList<>();

        public Project() {
        }
    }

    @Entity(name = "L4Invoice")
    @Table(name = "l4_invoices")
    public static class Invoice {
        @Id
        private Long id;
        @OneToOne
        private Payment payment;

        public Invoice() {
        }
    }

    @Entity(name = "L4Payment")
    @Table(name = "l4_payments")
    public static class Payment {
        @Id
        private Long id;
        @OneToOne(mappedBy = "payment")
        private Invoice invoice;

        public Payment() {
        }
    }

    @Entity(name = "L4Flight")
    @Table(name = "l4_flights")
    public static class Flight {
        @Id
        private Long id;
        @OneToMany(mappedBy = "flight")
        private List<Ticket> tickets = new ArrayList<>();

        public Flight() {
        }
    }

    @Entity(name = "L4Ticket")
    @Table(name = "l4_tickets")
    public static class Ticket {
        @Id
        private Long id;
        @ManyToOne
        private Flight flight;

        public Ticket() {
        }
    }

    @Entity(name = "L4Movie")
    @Table(name = "l4_movies")
    public static class Movie {
        @Id
        private Long id;
        @ManyToMany
        @JoinTable(name = "l4_movie_actors")
        private List<Actor> actors = new ArrayList<>();

        public Movie() {
        }
    }

    @Entity(name = "L4Actor")
    @Table(name = "l4_actors")
    public static class Actor {
        @Id
        private Long id;
        @ManyToMany(mappedBy = "actors")
        private List<Movie> movies = new ArrayList<>();

        public Actor() {
        }
    }

    @Entity(name = "L4Parent")
    @Table(name = "l4_parents")
    public static class Parent {
        @Id
        private Long id;
        @OneToMany(mappedBy = "parent")
        private List<Child> children = new ArrayList<>();

        public Parent() {
        }
    }

    @Entity(name = "L4Child")
    @Table(name = "l4_children")
    public static class Child {
        @Id
        private Long id;
        @ManyToOne
        private Parent parent;

        public Child() {
        }
    }

    @Entity(name = "L4School")
    @Table(name = "l4_schools")
    public static class School {
        @Id
        private Long id;
        @OneToMany(mappedBy = "school")
        private List<Classroom> classrooms = new ArrayList<>();

        public School() {
        }
    }

    @Entity(name = "L4Classroom")
    @Table(name = "l4_classrooms")
    public static class Classroom {
        @Id
        private Long id;
        @ManyToOne
        private School school;

        public Classroom() {
        }
    }

    @Entity(name = "L4Driver")
    @Table(name = "l4_drivers")
    public static class Driver {
        @Id
        private Long id;
        @OneToOne
        private Car car;

        public Driver() {
        }
    }

    @Entity(name = "L4Car")
    @Table(name = "l4_cars")
    public static class Car {
        @Id
        private Long id;
        @OneToOne(mappedBy = "car")
        private Driver driver;

        public Car() {
        }
    }

    @Entity(name = "L4Hotel")
    @Table(name = "l4_hotels")
    public static class Hotel {
        @Id
        private Long id;
        @OneToMany(mappedBy = "hotel")
        private List<Room> rooms = new ArrayList<>();

        public Hotel() {
        }
    }

    @Entity(name = "L4Room")
    @Table(name = "l4_rooms")
    public static class Room {
        @Id
        private Long id;
        @ManyToOne
        private Hotel hotel;

        public Room() {
        }
    }

    @Entity(name = "L4Account")
    @Table(name = "l4_accounts")
    public static class Account {
        @Id
        private Long id;
        @OneToMany(mappedBy = "account")
        private List<AccountTransaction> transactions = new ArrayList<>();

        public Account() {
        }
    }

    @Entity(name = "L4AccountTransaction")
    @Table(name = "l4_account_transactions")
    public static class AccountTransaction {
        @Id
        private Long id;
        @ManyToOne
        private Account account;

        public AccountTransaction() {
        }
    }

    @Entity(name = "L4Country")
    @Table(name = "l4_countries")
    public static class Country {
        @Id
        private Long id;
        @OneToMany(mappedBy = "country")
        private List<City> cities = new ArrayList<>();

        public Country() {
        }
    }

    @Entity(name = "L4City")
    @Table(name = "l4_cities")
    public static class City {
        @Id
        private Long id;
        @ManyToOne
        private Country country;

        public City() {
        }
    }
}
