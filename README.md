![Gradle Build](https://github.com/nu-cs-sqe/course-project-20242510-team-01-20242503/actions/workflows/main.yml/badge.svg)



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#dependencies">Installation</a></li>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#contributors">Contributors</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

<div align="center">
  <img src="https://github.com/user-attachments/assets/b62f6c94-bbaf-46ae-b0f8-20dd5111f260" alt="Logo" width="500" height="500">
  
  <p>
    A digital recreation of the popular card game <strong>Exploding Kittens</strong>, enhanced with cards from the <strong>Imploding Kittens</strong> expansion and custom developer-designed cards.
    The project was developed using industry-standard software engineering practices, including:
  </p>
  
  <p><strong>Test-Driven Development (TDD)</strong></p>
  <p><strong>Behavior-Driven Development (BDD)</strong> using Cucumber</p>
  <p>Comprehensive testing strategy including <strong>unit testing</strong>, <strong>integration testing</strong>, and <strong>regression testing</strong></p>
  <p>Full <strong>Boundary Value Analysis (BVA)</strong> and documentation</p>
  <p><strong>Code quality tools</strong>: SpotBugs, Checkstyle, Jacoco (code coverage), and Pitest (mutation testing)</p>
  <p><strong>CI</strong> via GitHub Actions and enforced <strong>branch protection</strong> with required reviewers</p>
  <p>Use of <strong>software design principles</strong> and patterns</p>
  <p>Project management through reports and GitHub Projects</p>
  <p><strong>internationalization (i18n)</strong> support</p>
</div>



<!-- BUILT WITH -->
### Built With
* [![Java][Java-badge]][Java-url]
* [![Gradle][Gradle-badge]][Gradle-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

Follow the steps below to set up and run the project locally on your machine.

### Prerequisites

* IntelliJ  
  > While IntelliJ is not required, it is the recommended IDE for building and running this project.  
  >  
  > ⚠️ **Note:** All instructions will assume use of IntelliJ.

* JDK 11  
  > This project requires Java 11.  
  >  
  > You can download it from the [Oracle JDK 11 page](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)  
  >  
  > Or install via a package manager:  
  > - **macOS**:  
  >   ```sh
  >   brew install openjdk@11
  >   ```
  > - **Ubuntu/Linux**:  
  >   ```sh
  >   sudo apt update
  >   sudo apt install openjdk-11-jdk
  >   ```

### Installation

1. Clone the repo
   
   ```sh
   git clone https://github.com/calebweldon/ExplodingKittens
   ```
   
3. Configure module settings
   > - Press `⌘ + ↓` (Mac) or `Ctrl + Alt + Shift + S` (Windows/Linux) to open **Project Structure**  
   > - Go to the **Project** tab  
   > - Under **Project SDK**, select **Java 11** (or add it if not listed)  
   > - Click **Apply** and **OK** to close  
  
4. Build the project
   
   ```sh
   ./gradlew build
   ```
   
5. Run the game
   > In your file explorer, navigate to:
   ```sh
   src/main/java/ui
   ```
   > Select ▶ Run 'Main.main()' from the context menu

### Dependencies
* JDK 11
* JUnit 5.10
* Gradle 8.10
* EasyMock 5.4
* CheckStyle 10.18
* Cucumber 7.20

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributors
* Caleb Weldon
* Benjamin Ye
* Eiko Reisz
* Samarth Arul



<!-- CONTACT -->
## Contact

Caleb Weldon | [Website](https://calebweldon.com/) | calebweldon2026@u.northwestern.edu

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
[Java-badge]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white
[Java-url]: https://www.oracle.com/java/

[Gradle-badge]: https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white
[Gradle-url]: https://gradle.org/
