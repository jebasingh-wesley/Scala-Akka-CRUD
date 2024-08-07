Here's a `README.md` file for your GitHub repository that includes instructions on setting up, building, running, and testing the Akka HTTP CRUD API:

```markdown
# Akka HTTP CRUD API

This project is a simple CRUD (Create, Read, Update, Delete) API built with Akka HTTP and Scala. It reads from and writes to a text file (`data.txt`).

## Project Structure

```
untitled
├── build.sbt
└── src
    └── main
        └── scala
            └── com
                └── example
                    ├── AkkaHttpApi.scala
                    └── FileUtil.scala
└── data.txt
```

## Prerequisites

- JDK 8 or higher
- Scala 2.13.12
- SBT (Simple Build Tool)

## Setup Instructions

### 1. Install JDK

Download and install the JDK from the [Oracle JDK download page](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or [AdoptOpenJDK](https://adoptium.net/). Set the `JAVA_HOME` environment variable to point to the JDK installation directory.

### 2. Install Scala

Download and install Scala from the [Scala download page](https://www.scala-lang.org/download/). Set the `SCALA_HOME` environment variable to point to the Scala installation directory and add the `bin` directory to your `PATH`.

### 3. Install SBT

Download and install SBT from the [SBT download page](https://www.scala-sbt.org/download.html).

### 4. Clone the Repository

```sh
git clone https://github.com/yourusername/akka-http-crud-api.git
cd akka-http-crud-api
```

### 5. Create `data.txt` File

Create a `data.txt` file in the project root directory with the following content:

```
Alice,30
Bob,25
Charlie,35
```

## Building the Project

Navigate to the project directory and run:

```sh
sbt compile
```

## Running the Project

To run the project, use:

```sh
sbt run
```

The server will start and listen on `http://localhost:8080`.

## Testing the API

### Retrieve all persons

```sh
curl http://localhost:8080/persons
```

Expected response:

```json
[
  {"name":"Alice","age":30},
  {"name":"Bob","age":25},
  {"name":"Charlie","age":35}
]
```

### Add a new person

```sh
curl -X POST http://localhost:8080/persons -H "Content-Type: application/json" -d '{"name":"David","age":40}'
```

Expected response:

```json
{"name":"David","age":40}
```

### Update an existing person

```sh
curl -X PUT http://localhost:8080/persons/Alice -H "Content-Type: application/json" -d '{"name":"Alice","age":31}'
```

Expected response:

```json
{"name":"Alice","age":31}
```

### Delete a person

```sh
curl -X DELETE http://localhost:8080/persons/Alice
```

Expected response: No content (status code 204).

## Project Structure

- `build.sbt`: SBT configuration file.
- `src/main/scala/com/example/AkkaHttpApi.scala`: Main API implementation.
- `src/main/scala/com/example/FileUtil.scala`: Utility functions for file operations.
- `data.txt`: Data file containing person information.

## License

This project is licensed under the MIT License.
```

### Adding the `README.md` to Your Repository

1. **Create the `README.md` file:**

```sh
touch README.md
```

2. **Copy the content into `README.md`:**

Copy the provided `README.md` content into the file.

3. **Commit and push to GitHub:**

```sh
git add README.md
git commit -m "Add README file"
git push origin main
```

This will add the `README.md` file to your GitHub repository, providing clear instructions for anyone who wants to use or contribute to your project.
