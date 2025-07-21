# BrightMind

BrightMind is a Java-based application designed to manage student information efficiently, course subjects, user roles, and academic marks within an educational institution.

## 🛠️ Technologies Used

- Java
- Maven
- MySQL

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- MySQL Server installed and running

### Installation

1. Clone the repository:

```bash
git clone https://github.com/Poorna-Raj/BrightMind.git
cd BrightMind
```
2. Build the project with Maven:

```bash
mvn clean install
```
3. Set up the database:
- Import the SQL dump file located in resources/ folder into your MySQL server:
```bash
mysql -u your_username -p < "resources/DB/SQL EAD.sql"
```
4. Configure your application to connect to the ead database with your MySQL credentials.

## 📦 Database Schema Overview
- tbluser: Stores user login credentials and roles.
- students: Student personal details.
- subjects: Subject details including code, name, description, and credit hours.
- marks: Marks scored by students in different subjects and exam types.

## 🧪 Running the Tests
Run tests using Maven:
```bash
mvn test
```

## 🤝 Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## 📄 License
This project is licensed under the MIT License.
