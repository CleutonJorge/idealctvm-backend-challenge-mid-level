import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
}

group = "com.api"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	//Postgres
	runtimeOnly("org.postgresql:postgresql")
	//Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	//swagger
	implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.0")
	//security
	implementation("org.springframework.boot:spring-boot-starter-security")
	//graphql
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation ("com.graphql-java:graphql-spring-boot-starter:5.0.2")
	implementation ("com.graphql-java:graphiql-spring-boot-starter:5.0.2")
	implementation ("com.graphql-java:voyager-spring-boot-starter:5.0.2")
	implementation ("com.graphql-java:graphql-java-tools:5.2.4")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("com.h2database:h2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
