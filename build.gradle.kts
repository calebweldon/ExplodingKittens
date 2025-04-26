import com.github.spotbugs.snom.Confidence
import com.github.spotbugs.snom.Effort

plugins {
    application
    id("java")
    checkstyle
    id("com.github.spotbugs") version "6.0.25"
}

group = "nu.csse.sqe"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // https://mvnrepository.com/artifact/org.easymock/easymock
    testImplementation("org.easymock:easymock:5.4.0")

    // https://docs.gradle.org/current/userguide/checkstyle_plugin.html
    implementation("com.puppycrawl.tools:checkstyle:10.18.2")

    // https://github.com/spotbugs/spotbugs-gradle-plugin/blob/master/README.md
    spotbugs("com.github.spotbugs:spotbugs:4.8.6")
    spotbugsPlugins("com.h3xstream.findsecbugs:findsecbugs-plugin:1.13.0")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}

tasks.compileJava {
    options.release = 11
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "Code.Main" // this may need to change later - I'm basing off template
}

// Spotbugs README: https://github.com/spotbugs/spotbugs-gradle-plugin#readme
// SpotBugs Gradle Plugin: https://spotbugs.readthedocs.io/en/latest/gradle.html
spotbugs {
    ignoreFailures = false
    showStackTraces = true
    showProgress = true
    effort = Effort.DEFAULT
    reportLevel = Confidence.DEFAULT
    //omitVisitors = listOf("FindNonShortCircuit")
    reportsDir = file("spotbugs")
    //includeFilter = file("include.xml")
//    excludeFilter = file("config/spotbugs/exclude.xml")
    //onlyAnalyze = listOf("com.foobar.MyClass", "com.foobar.mypkg.*")
    maxHeapSize = "1g"
    extraArgs = listOf("-nested:false")
    //jvmArgs = listOf("-Duser.language=ja") // set user language to japanese
}

tasks.spotbugsMain {
    reports.create("html") {
        required = true
        outputLocation = layout.buildDirectory.file("reports/spotbugs/spotbugs.html")
        setStylesheet("fancy-hist.xsl")
    }
}

tasks.spotbugsTest {
    reports.create("html") {
        required = true
        outputLocation = file("build/reports/spotbugs/spotbugs_test.html")
        setStylesheet("fancy-hist.xsl")
    }
}

// CheckStyle Gradle Plugin: https://docs.gradle.org/current/userguide/checkstyle_plugin.html
tasks.withType<Checkstyle>().configureEach {
    reports {
        xml.required = false
        html.required = true
        html.stylesheet = resources.text.fromFile("config/xsl/checkstyle-noframes-severity-sorted.xsl")
    }
}

checkstyle{
    toolVersion = "10.18.2"
    isIgnoreFailures = false
}

